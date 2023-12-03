package edu.hw7.task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestCounter {

    private static final int NUMBER_OF_INCREASES_FOR_EACH_THREAD = 42;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 6, 7, 8, 10, 15, 16})
    void test(int numberOfThreads) throws InterruptedException {
        Counter counter = new Counter();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch countDownLatch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            executorService.submit(() -> {
                for (int j = 0; j < NUMBER_OF_INCREASES_FOR_EACH_THREAD; ++j) {
                    counter.increment();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        int counterValue = counter.getCounterValue();
        assertThat(counterValue).isEqualTo(NUMBER_OF_INCREASES_FOR_EACH_THREAD * numberOfThreads);
    }
}
