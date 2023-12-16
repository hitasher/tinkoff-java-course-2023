package edu.hw9.task1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class StatsCollector {

    private final AtomicInteger taskCounter = new AtomicInteger(0);
    private final ExecutorService executorService;
    private final List<Metric> metrics = new CopyOnWriteArrayList<>();

    public StatsCollector(int numberOfThreads) {
        executorService = Executors.newFixedThreadPool(numberOfThreads);
    }

    public void push(String metricName, double... values) {
        taskCounter.incrementAndGet();
        executorService.execute(() -> {
            metrics.add(collect(metricName, values));
            taskCounter.decrementAndGet();
        });
    }

    public List<Metric> stats() {
        while (true) {
            if (taskCounter.get() == 0) {
                break;
            }
        }
        return metrics;
    }

    private Metric collect(String metricName, double... metricData) {
        return new Metric(
            metricName,
            Arrays.stream(metricData).sum(),
            Arrays.stream(metricData).average().orElse(0),
            Arrays.stream(metricData).min().orElse(0),
            Arrays.stream(metricData).max().orElse(0)
        );
    }
}
