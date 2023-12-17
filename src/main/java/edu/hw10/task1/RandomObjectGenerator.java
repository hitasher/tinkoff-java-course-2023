package edu.hw10.task1;

import edu.hw10.task1.generators.BooleanFieldGenerator;
import edu.hw10.task1.generators.DoubleFieldGenerator;
import edu.hw10.task1.generators.FieldGenerator;
import edu.hw10.task1.generators.IntFieldGenerator;
import edu.hw10.task1.generators.StringFieldGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public final class RandomObjectGenerator {

    private static final Map<Class<?>, FieldGenerator<?>> GENERATORS = Map.of(
        int.class, new IntFieldGenerator(),
        Integer.class, new IntFieldGenerator(),
        String.class, new StringFieldGenerator(),
        double.class, new DoubleFieldGenerator(),
        Double.class, new DoubleFieldGenerator(),
        boolean.class, new BooleanFieldGenerator(),
        Boolean.class, new BooleanFieldGenerator()
    );

    public <T> T nextObject(Class<T> klass) {
        Constructor<?> constructor = getConstructorWithMaxParameterCount(klass);
        return nextObject(klass, constructor);
    }

    public <T> T nextObject(Class<T> klass, String factoryMethod) {
        Method method = getMethodByName(klass, factoryMethod);
        Object[] params = generateParameters(method.getParameters());
        try {
            return klass.cast(method.invoke(null, params));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public <T> T nextObject(Class<T> klass, Constructor<?> constructor) {
        Object[] parameters = generateParameters(constructor.getParameters());
        try {
            return klass.cast(constructor.newInstance(parameters));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private Object[] generateParameters(Parameter[] parameters) {
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; ++i) {
            arguments[i] = GENERATORS.get(parameters[i].getType()).generate(parameters[i].getAnnotations());
        }
        return arguments;
    }

    private Constructor<?> getConstructorWithMaxParameterCount(Class<?> klass) {
        Constructor<?>[] constructors = klass.getDeclaredConstructors();
        return Arrays.stream(constructors)
            .max(Comparator.comparing(Constructor::getParameterCount))
            .orElseThrow();
    }

    private Method getMethodByName(Class<?> klass, String methodName) {
        for (Method method : klass.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new RuntimeException("Provided method not found");
    }


}
