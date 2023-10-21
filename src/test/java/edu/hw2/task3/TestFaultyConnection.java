package edu.hw2.task3;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class TestFaultyConnection {
    @Test
    void testExecute_ShouldNotThrowAnyExceptions() {
        FaultyConnection faultyConnection = new FaultyConnection(0);;
        assertDoesNotThrow(() -> faultyConnection.execute(""));
        faultyConnection.close();
    }

    @Test
    void testExecute_ShouldNotThrowConnectionException() {
        FaultyConnection faultyConnection = new FaultyConnection(-1);
        assertThatThrownBy(
            () -> faultyConnection.execute("")
        ).isInstanceOf(ConnectionException.class);
        faultyConnection.close();
    }
}
