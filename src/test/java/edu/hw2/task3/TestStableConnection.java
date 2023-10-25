package edu.hw2.task3;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class TestStableConnection {
    @Test
    void testStableConnection_ShouldNotThrowExceptions() {
        StableConnection stableConnection = new StableConnection();
        assertDoesNotThrow(() -> stableConnection.execute(""));
        stableConnection.close();
    }
}
