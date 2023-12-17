package edu.hw10.task2;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestCacheProxy {

    private static Stream<Arguments> testFib() {
        return Stream.of(
            Arguments.of(1, 1),
            Arguments.of(2, 1),
            Arguments.of(3, 2),
            Arguments.of(4, 3),
            Arguments.of(5, 5),
            Arguments.of(6, 8),
            Arguments.of(10, 55),
            Arguments.of(20, 6765)
        );
    }

    @ParameterizedTest
    @MethodSource("testFib")
    public void testWithoutPersist(long number, long expectedCacheNumber, @TempDir File tempDir) {
        FibonacciCalculator proxy = new FibonacciCalculatorWithoutPersist();
        proxy = CacheProxy.create(proxy, FibonacciCalculator.class, tempDir.toPath());
        proxy.fib(number);
        long actualCacheNumber = proxy.fib(number);
        Assertions.assertThat(actualCacheNumber).isEqualTo(expectedCacheNumber);
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("testFib")
    public void testPersist(long number, long expectedCacheNumber, @TempDir File tempDir) {
        FibCalculator proxy = new FibonacciCalculatorWithPersist();
        proxy = CacheProxy.create(proxy, FibCalculator.class, tempDir.toPath());

        long num = proxy.fib(number);
        long actualCacheNumber = proxy.fib(number);
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(tempDir.toPath().resolve("fib-long")));

        Assertions.assertThat(actualCacheNumber).isEqualTo(expectedCacheNumber);
        Assertions.assertThat(properties.getProperty(number + ";")).isEqualTo(String.valueOf(num));
    }

    static class FibonacciCalculatorWithPersist implements FibCalculator {
        @Override
        @SneakyThrows
        public long fib(long number) {
            if (number < 2) {
                return number;
            }
            return fib(number - 1) + fib(number - 2);
        }
    }

    interface FibonacciCalculator {
        @Cache
        long fib(long number);
    }

    static class FibonacciCalculatorWithoutPersist implements FibonacciCalculator {
        @Override
        @SneakyThrows
        public long fib(long number) {
            if (number < 2) {
                return number;
            }
            return fib(number - 1) + fib(number - 2);
        }
    }
}
