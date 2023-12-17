package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanFieldGenerator implements FieldGenerator<Boolean> {
    @Override
    public Boolean generate(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
