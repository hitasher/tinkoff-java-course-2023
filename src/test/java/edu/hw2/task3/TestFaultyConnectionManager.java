package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestFaultyConnectionManager {
    @Test
    void testGetConnection_ShouldReturnFaultyConnection() {
        Connection connection = new FaultyConnectionManager().getConnection();
        assertThat(connection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    void testDefaultConstructor_ShouldNotThrowAnyExceptions() {
        assertDoesNotThrow(() -> new FaultyConnectionManager());
    }
}
