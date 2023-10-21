package edu.hw2.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestPopularCommandExecutor {
    @Test
    void testTryExecute_ShouldNotThrowAnyExceptionsUsingDefaultConnectionManager() {
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(
            new DefaultConnectionManager(0, 0), 1
        );
        assertDoesNotThrow(commandExecutor::updatePackages);
    }

    @Test
    void testTryExecute_ShouldThrowConnectionExceptionUsingDefaultConnectionManager() {
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(
            new DefaultConnectionManager(-1, -1), 1
        );
        assertThatThrownBy(commandExecutor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @Test
    void testTryExecute_ShouldNotThrowAnyExceptionsUsingFaultyConnectionManager() {
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(
            new FaultyConnectionManager(0), 1
        );
        assertDoesNotThrow(commandExecutor::updatePackages);
    }

    @Test
    void testTryExecute_ShouldThrowConnectionExceptionUsingFaultyConnectionManager() {
        PopularCommandExecutor commandExecutor = new PopularCommandExecutor(
            new FaultyConnectionManager(-1), 1
        );
        assertThatThrownBy(commandExecutor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -101})
    void testTryExecute_ShouldThrowIllegalArgumentException(int maxNumberOfAttempts) {
        assertThatThrownBy(
            () -> new PopularCommandExecutor(new FaultyConnectionManager(), maxNumberOfAttempts)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
