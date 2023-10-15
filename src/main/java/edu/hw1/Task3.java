package edu.hw1;

import java.util.Arrays;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static boolean isFirstArrayNestedInSecond(int[] arr1, int[] arr2) {
        Objects.requireNonNull(arr1);
        Objects.requireNonNull(arr2);
        if (arr1.length == 0 || arr2.length == 0) {
            throw new IllegalArgumentException("Arrays can't be empty");
        }
        LOGGER.trace("Processing arrays {} and {}", arr1, arr2);
        return minInArray(arr1) > minInArray(arr2) && maxInArray(arr1) < maxInArray(arr2);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static int minInArray(int[] array) {
        return Arrays.stream(array).min().getAsInt();
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private static int maxInArray(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }

}
