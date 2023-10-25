package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Integer seed;

    public FaultyConnectionManager() {
        seed = null;
    }

    public FaultyConnectionManager(int seed) {
        this.seed = seed;
    }

    @Override
    public FaultyConnection getConnection() {
        LOGGER.trace("Faulty connection manager is making a faulty connection");
        if (seed != null) {
            return new FaultyConnection(seed);
        } else {
            return new FaultyConnection();
        }
    }
}
