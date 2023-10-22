package edu.project1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestWordBoard {
    private static Stream<Arguments> isLetterOpened() {
        return Stream.of(
            Arguments.of(new WordBoard("elephant")),
            Arguments.of(new WordBoard("bear"))
        );
    }

    @ParameterizedTest
    @MethodSource("isLetterOpened")
    void isLetterOpened_ShouldReturnFalse(WordBoard wordBoard) {
        assertThat(wordBoard.isLetterOpened('a')).isFalse();
    }

    @ParameterizedTest
    @MethodSource("isLetterOpened")
    void isLetterOpened_ShouldReturnTrue(WordBoard wordBoard) {
        wordBoard.openLetter('a');
        assertThat(wordBoard.isLetterOpened('a')).isTrue();
    }

    private static Stream<Arguments> isNotLetterInHiddenWord() {
        return Stream.of(
            Arguments.of(new WordBoard("tiger")),
            Arguments.of(new WordBoard("elephant"))
        );
    }

    @ParameterizedTest
    @MethodSource("isNotLetterInHiddenWord")
    void isNotLetterInHiddenWord_ShouldReturnTrue(WordBoard wordBoard) {
        assertThat(wordBoard.isNotLetterInHiddenWord('y')).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isNotLetterInHiddenWord")
    void isNotLetterInHiddenWord_ShouldReturnFalse(WordBoard wordBoard) {
        assertThat(wordBoard.isNotLetterInHiddenWord('e')).isFalse();
    }

    private static Stream<Arguments> openLetter_ShouldThrowIllegalArgumentException() {
        WordBoard wordBoardWithOpenedLetter = new WordBoard("bear");
        wordBoardWithOpenedLetter.openLetter('a');
        return Stream.of(
            Arguments.of(wordBoardWithOpenedLetter),
            Arguments.of(new WordBoard("fish"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void openLetter_ShouldThrowIllegalArgumentException(WordBoard wordBoard) {
        assertThatThrownBy(
            () -> wordBoard.openLetter('a')
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> areAllLettersOpened_ShouldReturnFalse() {
        return Stream.of(
            Arguments.of(new WordBoard("buzz")),
            Arguments.of(new WordBoard("jazz"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void areAllLettersOpened_ShouldReturnFalse(WordBoard wordBoard) {
        wordBoard.openLetter('z');
        assertThat(wordBoard.areAllLettersOpened()).isFalse();
    }

    private static Stream<Arguments> areAllLettersOpened_ShouldReturnTrue() {
        return Stream.of(
            Arguments.of(new WordBoard("dog")),
            Arguments.of(new WordBoard("god"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void areAllLettersOpened_ShouldReturnTrue(WordBoard wordBoard) {
        wordBoard.openLetter('o');
        wordBoard.openLetter('g');
        wordBoard.openLetter('d');
        assertThat(wordBoard.areAllLettersOpened()).isTrue();
    }

    private static Stream<Arguments> print_ShouldNotThrowAnyExceptions() {
        return Stream.of(
            Arguments.of(new WordBoard("whale")),
            Arguments.of(new WordBoard("shark"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void print_ShouldNotThrowAnyExceptions(WordBoard wordBoard) {
        assertDoesNotThrow(wordBoard::print);
    }
}
