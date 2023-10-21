package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();

    private final ConnectionManager manager;
    private final int maxNumberOfAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxNumberOfAttempts) {
        if (maxNumberOfAttempts <= 0) {
            throw new IllegalArgumentException("Parameter maxNumberOfAttempts must be positive");
        }
        this.manager = manager;
        this.maxNumberOfAttempts = maxNumberOfAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    @SuppressWarnings("SameParameterValue")
    private void tryExecute(String command) {
        int currentNumberOfAttempts = 0;
        ConnectionException lastConnectionException = null;
        while (currentNumberOfAttempts < maxNumberOfAttempts) {
            LOGGER.trace("Attempt {} to execute the command", currentNumberOfAttempts + 1);
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                LOGGER.trace("The command was executed successfully");
                return;
            } catch (Exception exception) {
                ++currentNumberOfAttempts;
                if (exception instanceof ConnectionException) {
                    lastConnectionException = (ConnectionException) exception;
                }
            }
        }
        if (lastConnectionException != null) {
            LOGGER.trace("Maximum number of connection attempts exceeded");
            throw new ConnectionException(lastConnectionException);
        }
    }
}
