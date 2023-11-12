package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask6 {

    private static Stream<Arguments> isSubstring_ShouldReturnTrue() {
        return Stream.of(
            Arguments.of("abc", "abc"),
            Arguments.of("abcaba", "abc"),
            Arguments.of("abcaba", "abca"),
            Arguments.of("abcaba", "a"),
            Arguments.of("abcaba", "c"),
            Arguments.of("abcaba", "ca"),
            Arguments.of("abcaba", "cab"),
            Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSubstring_ShouldReturnTrue(String s, String t) {
        assertThat(Task6.isSubstring(s, t)).isTrue();
    }

    private static Stream<Arguments> isSubstring_ShouldReturnFalse() {
        return Stream.of(
            Arguments.of("ab", "abc"),
            Arguments.of("", "a"),
            Arguments.of("", "abc"),
            Arguments.of("abacaba", "bc"),
            Arguments.of("abacaba", "cba")
        );
    }

    @ParameterizedTest
    @MethodSource
    void isSubstring_ShouldReturnFalse(String s, String t) {
        assertThat(Task6.isSubstring(s, t)).isFalse();
    }
}
