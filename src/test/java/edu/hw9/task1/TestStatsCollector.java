package edu.hw9.task1;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestStatsCollector {
    private static Stream<Arguments> stats_ShouldReturnExpectedStats() {
        return Stream.of(
            Arguments.of(1, 1),
            Arguments.of(1, 2),
            Arguments.of(2, 2),
            Arguments.of(2, 4),
            Arguments.of(2, 4),
            Arguments.of(4, 4)
        );
    }

    @ParameterizedTest
    @MethodSource
    void stats_ShouldReturnExpectedStats(int numberOfStatsThreads, int numberOfTransferThreads)
        throws InterruptedException {
        StatsCollector collector = new StatsCollector(numberOfStatsThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfTransferThreads);
        executorService.submit(() -> collector.push("a", 0.01, 0.05, 1.4, 5.1, 0.3));
        executorService.submit(() -> collector.push("b", 3829, 83298, 938289, 1389, 3298));
        executorService.submit(() -> collector.push("c", 0.398198, 0.389198, 0.03289839, 0.382904234, 0.3892984932));
        List<Metric> actualStats = collector.stats();
        Thread.sleep(1);
        executorService.shutdown();
        Assertions.assertThat(actualStats).containsExactlyInAnyOrder(
            new Metric("a", 6.859999999999999, 1.3719999999999999, 0.01, 5.1),
            new Metric("b", 1030103, 206020.6, 1389, 938289),
            new Metric("c", 1.5924971172, 0.31849942344, 0.03289839, 0.398198)
        );
    }
}
