package edu.hw2.task3;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final double FAULTY_CONNECTION_CHANCE = 0.5;

    private final Random random;
    private final Integer connectionSeed;

    public DefaultConnectionManager() {
        random = new Random();
        connectionSeed = null;
    }

    public DefaultConnectionManager(int managerSeed) {
        random = new Random(managerSeed);
        this.connectionSeed = null;
    }

    public DefaultConnectionManager(int managerSeed, int connectionSeed) {
        random = new Random(managerSeed);
        this.connectionSeed = connectionSeed;
    }

    @Override
    public Connection getConnection() {
        if (random.nextDouble() <= FAULTY_CONNECTION_CHANCE) {
            LOGGER.trace("Default connection manager is making a faulty connection");
            if (connectionSeed != null) {
                return new FaultyConnection(connectionSeed);
            } else {
                return new FaultyConnection();
            }
        }
        LOGGER.trace("Default connection manager is making a stable connection");
        return new StableConnection();
    }
}
