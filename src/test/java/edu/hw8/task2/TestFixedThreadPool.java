package edu.hw8.task2;

import lombok.SneakyThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.IntStream;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestFixedThreadPool {
    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 16})
    public void computeList_shouldReturnCorrectResult(int numberOfThreads) {
        List<Integer> list = IntStream.range(0, 10).boxed().toList();
        List<Integer> result = FibonacciCalculator.calculateListOfFibonacci(list, numberOfThreads);
        Thread.sleep(100);
        assertThat(result).containsExactlyInAnyOrder(0, 1, 1, 2, 3, 5, 8, 13, 21, 34);
    }
}
