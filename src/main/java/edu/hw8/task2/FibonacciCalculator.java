package edu.hw8.task2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;

public final class FibonacciCalculator {
    private FibonacciCalculator() {
    }

    public static int calculateFibonacciOf(int number) {
        if (number == 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        return calculateFibonacciOf(number - 1) + calculateFibonacciOf(number - 2);
    }

    @SneakyThrows
    public static List<Integer> calculateListOfFibonacci(List<Integer> list, int numberOfThreads) {
        ThreadPool threadPool = FixedThreadPool.create(numberOfThreads);
        threadPool.start();
        List<Integer> result = new CopyOnWriteArrayList<>();
        for (Integer number : list) {
            threadPool.execute(() -> result.add(calculateFibonacciOf(number)));
        }
        threadPool.close();
        return result;
    }
}
