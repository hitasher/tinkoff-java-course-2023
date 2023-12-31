package edu.project1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestGuessResult {
    private static Stream<Arguments> guessResultArgumentsExample() {
        return Stream.of(
            Arguments.of(new WordBoard("parrot"), 0, 2),
            Arguments.of(new WordBoard("rhino"), 3, 5)
        );
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void defeat_ShouldNotThrowAnyExceptions(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.Defeat(wordBoard, usedAttempts, maxAttempts);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void win_ShouldNotThrowAnyExceptions(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.Win(wordBoard, usedAttempts, maxAttempts);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void successfulGuess_ShouldNotThrowAnyExceptions(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.SuccessfulGuess(wordBoard, usedAttempts, maxAttempts);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void failedGuess_ShouldNotThrowAnyExceptions(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.FailedGuess(wordBoard, usedAttempts, maxAttempts);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void alreadyOpenedGuess_ShouldNotThrowAnyExceptions(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.AlreadyOpenedGuess(wordBoard, usedAttempts, maxAttempts);
        assertDoesNotThrow(guessResult::message);
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void invalidGuess_ShouldNotThrowAnyExceptions(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.InvalidGuess(wordBoard, usedAttempts, maxAttempts);
        assertDoesNotThrow(guessResult::message);
    }


    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void isTerminal_ShouldReturnTrueForDefeat(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.Defeat(wordBoard, usedAttempts, maxAttempts);
        assertThat(guessResult.isTerminal()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void isTerminal_ShouldReturnTrueForWin(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.Win(wordBoard, usedAttempts, maxAttempts);
        assertThat(guessResult.isTerminal()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void isTerminal_ShouldReturnFalseForSuccessfulGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.SuccessfulGuess(wordBoard, usedAttempts, maxAttempts);
        assertThat(guessResult.isTerminal()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void isTerminal_ShouldReturnFalseForFailedGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.FailedGuess(wordBoard, usedAttempts, maxAttempts);
        assertThat(guessResult.isTerminal()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void isTerminal_ShouldReturnFalseForAlreadyOpenedGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.AlreadyOpenedGuess(wordBoard, usedAttempts, maxAttempts);
        assertThat(guessResult.isTerminal()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("guessResultArgumentsExample")
    void isTerminal_ShouldReturnFalseForInvalidGuess(WordBoard wordBoard, int usedAttempts, int maxAttempts) {
        GuessResult guessResult = new GuessResult.InvalidGuess(wordBoard, usedAttempts, maxAttempts);
        assertThat(guessResult.isTerminal()).isFalse();
    }
}
