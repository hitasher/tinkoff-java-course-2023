package edu.hw7;

import java.math.BigInteger;
import java.util.stream.Stream;

public final class Task2 {
    private Task2() {
    }

    public static BigInteger calculateFactorialOf(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Provided number must be positive");
        }
        if (number == 0) {
            return BigInteger.valueOf(1);
        }
        BigInteger end = BigInteger.valueOf(number);
        return Stream.iterate(BigInteger.ONE, i -> i.compareTo(end) <= 0, i -> i.add(BigInteger.ONE))
            .parallel()
            .reduce(BigInteger::multiply)
            .orElseThrow();
    }
}
