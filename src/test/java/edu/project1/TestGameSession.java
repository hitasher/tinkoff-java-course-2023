package edu.project1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestGameSession {
    private static Stream<Arguments> guess_ShouldReturnInvalidGuess() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("pig"), 1), 'A'),
            Arguments.of(new GameSession(new OneWordDictionary("cow"), 4), 'б'),
            Arguments.of(new GameSession(new OneWordDictionary("cat"), 3), '0'),
            Arguments.of(new GameSession(new OneWordDictionary("dog"), 2), 'B'),
            Arguments.of(new GameSession(new OneWordDictionary("pig"), 5), 'а')  // русская а
        );
    }

    @ParameterizedTest
    @MethodSource
    void guess_ShouldReturnInvalidGuess(GameSession gameSession, char guess) {
        assertThat(gameSession.guess(guess)).isInstanceOf(GuessResult.InvalidGuess.class);
    }

    private static Stream<Arguments> guess_ShouldReturnAlreadyOpenedGuess() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("camel"), 1), 'm'),
            Arguments.of(new GameSession(new OneWordDictionary("bull"), 2), 'u'),
            Arguments.of(new GameSession(new OneWordDictionary("bat"), 4), 't')
        );
    }

    @ParameterizedTest
    @MethodSource
    void guess_ShouldReturnAlreadyOpenedGuess(GameSession gameSession, char guess) {
        gameSession.guess(guess);
        assertThat(gameSession.guess(guess)).isInstanceOf(GuessResult.AlreadyOpenedGuess.class);
    }

    private static Stream<Arguments> guess_ShouldReturnDefeat() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("wolf"), 4)),
            Arguments.of(new GameSession(new OneWordDictionary("goat"), 3)),
            Arguments.of(new GameSession(new OneWordDictionary("bear"), 2))
        );
    }

    @ParameterizedTest
    @MethodSource
    void guess_ShouldReturnDefeat(GameSession gameSession) {
        gameSession.guess('a');
        gameSession.guess('b');
        gameSession.guess('c');
        assertThat(gameSession.guess('d')).isInstanceOf(GuessResult.Defeat.class);
    }

    private static Stream<Arguments> guess_ShouldReturnFailedGuess() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("lion"), 4)),
            Arguments.of(new GameSession(new OneWordDictionary("hippopotamus"), 3))
        );
    }

    @ParameterizedTest
    @MethodSource
    void guess_ShouldReturnFailedGuess(GameSession gameSession) {
        assertThat(gameSession.guess('y')).isInstanceOf(GuessResult.FailedGuess.class);
    }

    private static Stream<Arguments> guess_ShouldReturnWin() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("eat"), 2)),
            Arguments.of(new GameSession(new OneWordDictionary("tea"), 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void guess_ShouldReturnWin(GameSession gameSession) {
        gameSession.guess('a');
        gameSession.guess('t');
        assertThat(gameSession.guess('e')).isInstanceOf(GuessResult.Win.class);
    }

    private static Stream<Arguments> guess_ShouldReturnSuccessfulGuess() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("crab"), 2), 'b'),
            Arguments.of(new GameSession(new OneWordDictionary("rabbit"), 4), 'i')
        );
    }

    @ParameterizedTest
    @MethodSource
    void guess_ShouldReturnSuccessfulGuess(GameSession gameSession, char guess) {
        assertThat(gameSession.guess(guess)).isInstanceOf(GuessResult.SuccessfulGuess.class);
    }

    @Test
    void giveUp_ShouldReturnDefeat() {
        GameSession gameSession = new GameSession(new OneWordDictionary("turtle"), 3);
        assertThat(gameSession.giveUp()).isInstanceOf(GuessResult.Defeat.class);
    }

    private static Stream<Arguments> printWordBoard_ShouldNotThrowAnyExceptions() {
        return Stream.of(
            Arguments.of(new GameSession(new OneWordDictionary("sheep"), 1)),
            Arguments.of(new GameSession(new OneWordDictionary("fox"), 2))
        );
    }

    @ParameterizedTest
    @MethodSource
    void printWordBoard_ShouldNotThrowAnyExceptions(GameSession gameSession) {
        assertDoesNotThrow(gameSession::printWordBoard);
    }

    private static Stream<Arguments> constructor_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of("ox", 1),
            Arguments.of("octopUs", 3),
            Arguments.of("1234", 2),
            Arguments.of("panda", 0),
            Arguments.of("monkey", -1)
        );
    }

    @ParameterizedTest
    @MethodSource
    void constructor_ShouldThrowIllegalArgumentException(String hiddenWord, int maxAttempts) {
        assertThatThrownBy(
            () -> new GameSession(new OneWordDictionary(hiddenWord), maxAttempts)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> getHiddenWord_ShouldReturnHiddenWord() {
        return Stream.of(
            Arguments.of("car"),
            Arguments.of("bike"),
            Arguments.of("bus")
        );
    }

    @ParameterizedTest
    @MethodSource
    void getHiddenWord_ShouldReturnHiddenWord(String word) {
        GameSession gameSession = new GameSession(new OneWordDictionary(word), 4);
        assertThat(gameSession.getHiddenWord()).isEqualTo(word);
    }

}
