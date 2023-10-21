package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double EXCEPTION_CHANCE = 0.5;

    private final Random random;

    public FaultyConnection() {
        random = new Random();
    }

    public FaultyConnection(int seed) {
        random = new Random(seed);
    }

    @Override
    public void execute(String command) {
        LOGGER.trace("Executing a faulty connection");
        if (random.nextDouble() <= EXCEPTION_CHANCE) {
            LOGGER.trace("Connection failed");
            throw new ConnectionException("Connection error");
        }
        LOGGER.trace("Connection was successful");

    }

    @Override
    public void close() {
        LOGGER.trace("Closing a faulty connection");
    }
}
