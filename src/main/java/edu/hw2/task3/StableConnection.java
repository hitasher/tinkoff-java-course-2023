package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StableConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.trace("Executing a stable connection");
    }

    @Override
    public void close() {
        LOGGER.trace("Closing a stable connection");
    }
}
