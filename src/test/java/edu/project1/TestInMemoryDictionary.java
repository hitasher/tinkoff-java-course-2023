package edu.project1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestInMemoryDictionary {
    @Test
    void getRandomWord_ShouldNotThrowAnyExceptions() {
        InMemoryDictionary inMemoryDictionary = new InMemoryDictionary();
        assertDoesNotThrow(inMemoryDictionary::getRandomWord);
    }

}
