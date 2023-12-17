package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class IntFieldGenerator implements FieldGenerator<Integer> {
    @Override
    public Integer generate(Annotation[] annotations) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = (int) minAnnotation.value();
            }
            if (annotation instanceof Max maxAnnotation) {
                max = (int) maxAnnotation.value();
            }
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
