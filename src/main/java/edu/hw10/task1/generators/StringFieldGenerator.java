package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class StringFieldGenerator implements FieldGenerator<String> {
    private final static String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final int MIN_STRING_LENGTH = 1;
    private static final int MAX_STRING_LENGTH = 5;

    @Override
    public String generate(Annotation[] annotations) {
        boolean isNotNull = false;
        int minStringLength = MIN_STRING_LENGTH;
        int maxStringLength = MAX_STRING_LENGTH;
        for (Annotation annotation : annotations) {
            if (annotation instanceof NotNull) {
                isNotNull = true;
            } else if (annotation instanceof Min minAnnotation) {
                minStringLength = (int) minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                maxStringLength = (int) maxAnnotation.value();
            }
        }
        if (!isNotNull) {
            return null;
        }
        return randomString(ThreadLocalRandom.current().nextInt(minStringLength, maxStringLength));
    }

    private String randomString(int size) {
        StringBuilder stringBuilder = new StringBuilder(size);
        for (int i = 0; i < size; ++i) {
            stringBuilder.append(ALPHABET.charAt(ThreadLocalRandom.current().nextInt(ALPHABET.length())));
        }
        return stringBuilder.toString();
    }
}
