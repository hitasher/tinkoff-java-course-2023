package edu.hw10.task2.serializers;

public interface Serializer<T> {
    String serialize(Object object);

    T deserialize(String string);
}
