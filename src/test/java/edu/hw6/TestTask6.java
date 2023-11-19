package edu.hw6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestTask6 {
    @Test
    void logBusyPorts_ShouldNotThrowAnyExceptions() {
        assertDoesNotThrow(Task6::logBusyPorts);
    }
}
