package edu.hw3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static <T> @NotNull Map<T, Integer> getCounter(List<T> list) {
        Objects.requireNonNull(list);
        LOGGER.trace("Creating counter from {}", list);
        final Map<T, Integer> counter = new HashMap<>();
        for (final T object : list) {
            LOGGER.trace("Adding object \"{}\" to the counter", object);
            counter.merge(object, 1, Integer::sum);
        }
        return counter;
    }
}
