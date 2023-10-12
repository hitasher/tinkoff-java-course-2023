package edu.hw1;

import java.util.Arrays;
import java.util.Objects;
import java.util.OptionalInt;
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
        OptionalInt arr1Min = Arrays.stream(arr1).min();
        OptionalInt arr2Min = Arrays.stream(arr2).min();
        OptionalInt arr1Max = Arrays.stream(arr1).max();
        OptionalInt arr2Max = Arrays.stream(arr2).max();

        return arr1Min.getAsInt() > arr2Min.getAsInt() && arr1Max.getAsInt() < arr2Max.getAsInt();
    }
}
