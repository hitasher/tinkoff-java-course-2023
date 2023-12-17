package edu.hw10.task2;

import edu.hw10.task2.serializers.LongSerializer;
import edu.hw10.task2.serializers.Serializer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public final class CacheProxy {

    private final static Map<Class<?>, Serializer<?>> SERIALIZERS = Map.of(
        long.class, new LongSerializer(),
        Long.class, new LongSerializer()
    );

    private CacheProxy() {
    }

    public static <T> T create(T object, Class<T> klass, Path persistPath) {
        return klass.cast(Proxy.newProxyInstance(
            object.getClass().getClassLoader(),
            new Class[] {klass},
            new CacheInvocationHandler<>(object, persistPath)
        ));
    }

    private final static class CacheInvocationHandler<T> implements InvocationHandler {
        private final Map<Method, Map<List<Object>, Object>> cachedValues = new HashMap<>();
        private final T cachingObject;
        private final Path persistPath;

        @SneakyThrows
        private CacheInvocationHandler(T cachingObject, Path persistPath) {
            this.cachingObject = cachingObject;
            this.persistPath = persistPath;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cache.class)) {
                if (method.getAnnotation(Cache.class).persist()) {
                    return computePersistCache(method, args);
                }
                return computeCache(method, args);
            }
            return method.invoke(cachingObject, args);
        }

        public Object computeCache(Method method, Object[] args) throws Throwable {
            List<Object> argsList = args == null ? null : Arrays.asList(args);
            if (!cachedValues.containsKey(method)) {
                cachedValues.put(method, new HashMap<>());
            }
            if (!cachedValues.get(method).containsKey(argsList)) {
                Object result = method.invoke(cachingObject, args);
                cachedValues.get(method).put(argsList, result);
                return result;
            }
            return cachedValues.get(method).get(argsList);
        }

        public Object computePersistCache(Method method, Object[] args) {
            Serializer<?> serializer = SERIALIZERS.get(method.getReturnType());
            if (args == null || args.length == 0) {
                return computerCacheWithoutArguments(method, args, serializer);
            } else {
                return computePersistCacheWithManyArguments(method, args, serializer);
            }
        }

        @SneakyThrows
        public Object computerCacheWithoutArguments(Method method, Object[] args, Serializer<?> resultSerializer) {
            if (Files.exists(persistPath.resolve(method.getName()))) {
                return resultSerializer.deserialize(Files.readString(persistPath.resolve(createFileName(method))));
            } else {
                Object result = method.invoke(cachingObject, args);
                Files.writeString(persistPath.resolve(method.getName()), resultSerializer.serialize(result));
                return result;
            }
        }

        @SneakyThrows
        public Object computePersistCacheWithManyArguments(
            Method method,
            Object[] args,
            Serializer<?> resultSerializer
        ) {
            StringBuilder serializedArgumentsBuilder = new StringBuilder();
            for (Object arg : args) {
                Serializer<?> serializer = SERIALIZERS.get(arg.getClass());
                serializedArgumentsBuilder.append(serializer.serialize(arg)).append(";");
            }
            String serializedArguments = serializedArgumentsBuilder.toString();
            String fileName = createFileName(method);
            if (Files.exists(persistPath.resolve(fileName))) {
                Properties properties = new Properties();
                properties.load(Files.newBufferedReader(persistPath.resolve(fileName)));
                if (properties.containsKey(serializedArguments)) {
                    return resultSerializer.deserialize(properties.getProperty(serializedArguments));
                }
                return computeAndSaveCache(method, args, serializedArguments, resultSerializer, properties);
            } else {
                Properties properties = new Properties();
                return computeAndSaveCache(method, args, serializedArguments, resultSerializer, properties);
            }
        }

        @SneakyThrows
        private Object computeAndSaveCache(
            Method method,
            Object[] args,
            String serializedArguments,
            Serializer<?> resultSerializer,
            Properties properties
        ) {
            Object result = method.invoke(cachingObject, args);
            properties.put(serializedArguments, resultSerializer.serialize(result));
            properties.store(Files.newBufferedWriter(persistPath.resolve(createFileName(method))), null);
            return result;
        }

        private String createFileName(Method method) {
            return method.getName() + Arrays.stream(method.getParameters()).map(
                parameter -> "-" + parameter.getType().getName()
            ).collect(Collectors.joining());
        }
    }
}
