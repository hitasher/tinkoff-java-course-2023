package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;

public interface FieldGenerator<T> {
    T generate(Annotation[] annotations);
}
