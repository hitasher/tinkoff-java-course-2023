package edu.project4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestMain {
    @Test
    void main_ShouldNotThrowAnyException() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
