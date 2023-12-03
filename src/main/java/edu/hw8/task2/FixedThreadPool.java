package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.SneakyThrows;

public final class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> tasks;
    private final Worker[] workers;
    private final AtomicBoolean isShutdown;

    public static FixedThreadPool create(int numberOfThreads) {
        if (numberOfThreads <= 0) {
            throw new IllegalArgumentException("Provided number of threads must be positive");
        }
        return new FixedThreadPool(new Worker[numberOfThreads]);
    }

    private FixedThreadPool(Worker[] workers) {
        this.workers = workers;
        tasks = new LinkedBlockingQueue<>();
        isShutdown = new AtomicBoolean(false);
        start();
    }

    @Override
    public void start() {
        for (int i = 0; i < workers.length; ++i) {
            workers[i] = new Worker();
            workers[i].start();
        }
    }

    @Override
    @SneakyThrows
    public void execute(Runnable runnable) {
        if (!isShutdown.get()) {
            tasks.put(runnable);
        }
    }

    @Override
    public void close() {
        for (Thread thread : workers) {
            if (!thread.isInterrupted()) {
                thread.interrupt();
            }
        }
        isShutdown.set(true);
    }

    private final class Worker extends Thread implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            while (!isShutdown.get() || !tasks.isEmpty()) {
                Runnable runnable;
                while ((runnable = tasks.poll()) != null) {
                    runnable.run();
                }
            }
        }
    }
}
