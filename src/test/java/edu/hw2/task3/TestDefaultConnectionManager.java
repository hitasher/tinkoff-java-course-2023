package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestDefaultConnectionManager {
    @Test
    void testGetConnection_ShouldSometimesReturnFaultyConnection() {
        Connection connection = new DefaultConnectionManager(-1, -1).getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    void testGetConnection_ShouldSometimesReturnStableConnection() {
        Connection connection = new DefaultConnectionManager(0, 0).getConnection();
        assertThat(connection).isInstanceOf(StableConnection.class);
    }

    @Test
    void testGetConnection_ShouldNotThrowAnyExceptionsWithDefaultConstructor() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager();
        assertDoesNotThrow(() -> {
            Connection connection = defaultConnectionManager.getConnection();
            connection.close();
        });
    }

    @Test
    void testGetConnection_ShouldNotThrowAnyExceptionsWithOneParameterConstructor() {
        DefaultConnectionManager defaultConnectionManager = new DefaultConnectionManager(-1);
        assertDoesNotThrow(() -> {
            Connection connection = defaultConnectionManager.getConnection();
            connection.close();
        });
    }
}
