package edu.hw7.task4;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public final class PiCalculator {

    private static final int MONTE_CARLO_CONSTANT = 4;

    private PiCalculator() {
    }

    public static double calculatePi(int numberOfIterations) {
        int circleCount = countNumberOfPointsInCircle(numberOfIterations);
        return MONTE_CARLO_CONSTANT * ((double) circleCount / numberOfIterations);
    }

    public static double calculatePiInParallel(int numberOfIterations, int numberOfThreads)
        throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        Future<Integer>[] futures = new Future[numberOfThreads];
        for (int i = 0; i < numberOfThreads; ++i) {
            futures[i] = executorService.submit(
                () -> countNumberOfPointsInCircle(numberOfIterations / numberOfThreads)
            );
        }
        int circleCount = 0;
        for (int i = 0; i < numberOfThreads; ++i) {
            circleCount += futures[i].get();
        }
        return MONTE_CARLO_CONSTANT * ((double) circleCount / numberOfIterations);
    }

    private static boolean isPointInCircle(double x, double y) {
        return x * x + y * y < 1;
    }

    private static int countNumberOfPointsInCircle(int numberOfIterations) {
        Random random = ThreadLocalRandom.current();
        int circleCount = 0;
        for (int i = 0; i < numberOfIterations; ++i) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isPointInCircle(x, y)) {
                ++circleCount;
            }
        }
        return circleCount;
    }
}
