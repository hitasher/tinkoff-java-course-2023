package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask8 {

    private static Stream<Arguments> subtask1_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("00", false),
            Arguments.of("01", false),
            Arguments.of("10", false),
            Arguments.of("11", false),
            Arguments.of("000", true),
            Arguments.of("001", true),
            Arguments.of("010", true),
            Arguments.of("011", true),
            Arguments.of("100", true),
            Arguments.of("101", true),
            Arguments.of("110", true),
            Arguments.of("111", true),
            Arguments.of("0000", false),
            Arguments.of("0010", false),
            Arguments.of("01010", true),
            Arguments.of("101001", false),
            Arguments.of("0101010", true),
            Arguments.of("10010101", false),
            Arguments.of("-", false),
            Arguments.of("**", false),
            Arguments.of("___", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask1_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask1(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask2_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", false),
            Arguments.of("00", false),
            Arguments.of("01", false),
            Arguments.of("10", true),
            Arguments.of("11", true),
            Arguments.of("000", true),
            Arguments.of("001", true),
            Arguments.of("010", true),
            Arguments.of("011", true),
            Arguments.of("100", false),
            Arguments.of("101", false),
            Arguments.of("110", false),
            Arguments.of("111", false),
            Arguments.of("0000", false),
            Arguments.of("0010", false),
            Arguments.of("1010", true),
            Arguments.of("1111", true),
            Arguments.of("01001", true),
            Arguments.of("101001", true),
            Arguments.of("1010011", false),
            Arguments.of("00100110", false),
            Arguments.of("z", false),
            Arguments.of("xyz", false),
            Arguments.of("0uw", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask2_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask2(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask3_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", false),
            Arguments.of("1", false),
            Arguments.of("00", false),
            Arguments.of("11", false),
            Arguments.of("000", true),
            Arguments.of("001", false),
            Arguments.of("100", false),
            Arguments.of("111", false),
            Arguments.of("0000", false),
            Arguments.of("0010", true),
            Arguments.of("1000", true),
            Arguments.of("1010", false),
            Arguments.of("0001000", true),
            Arguments.of("001001010", true),
            Arguments.of("0011110100", false),
            Arguments.of("000_000", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask3_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask3(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask4_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("00", true),
            Arguments.of("01", true),
            Arguments.of("10", true),
            Arguments.of("11", false),
            Arguments.of("000", true),
            Arguments.of("001", true),
            Arguments.of("010", true),
            Arguments.of("011", true),
            Arguments.of("100", true),
            Arguments.of("101", true),
            Arguments.of("110", true),
            Arguments.of("111", false),
            Arguments.of("1110", true),
            Arguments.of("1111", true),
            Arguments.of("01010100", true),
            Arguments.of("10100101", true),
            Arguments.of("110101011", true),
            Arguments.of("a", false),
            Arguments.of("'", false),
            Arguments.of("/|\\", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask4_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask4(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask5_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", false),
            Arguments.of("1", true),
            Arguments.of("00", false),
            Arguments.of("01", false),
            Arguments.of("10", true),
            Arguments.of("11", true),
            Arguments.of("000", false),
            Arguments.of("001", false),
            Arguments.of("010", false),
            Arguments.of("011", false),
            Arguments.of("100", false),
            Arguments.of("101", true),
            Arguments.of("110", false),
            Arguments.of("111", true),
            Arguments.of("10010", false),
            Arguments.of("10101", true),
            Arguments.of("101011", true),
            Arguments.of("1010110", false),
            Arguments.of("1010110110", false),
            Arguments.of("a", false),
            Arguments.of("1o1", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask5_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask5(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask6_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", false),
            Arguments.of("1", false),
            Arguments.of("00", true),
            Arguments.of("01", false),
            Arguments.of("10", false),
            Arguments.of("11", false),
            Arguments.of("000", true),
            Arguments.of("001", true),
            Arguments.of("010", true),
            Arguments.of("011", false),
            Arguments.of("100", true),
            Arguments.of("101", false),
            Arguments.of("110", false),
            Arguments.of("111", false),
            Arguments.of("1000", true),
            Arguments.of("0100", true),
            Arguments.of("0010", true),
            Arguments.of("0001", true),
            Arguments.of("0000", true),
            Arguments.of("0011", false),
            Arguments.of("1000000", true),
            Arguments.of("1000010", false),
            Arguments.of("010000000", true),
            Arguments.of("010000010", false),
            Arguments.of("001000000", true),
            Arguments.of("0000001000000", true),
            Arguments.of("0000010100000", false),
            Arguments.of("00000000000000000", true),
            Arguments.of("0010110101110100000", false),
            Arguments.of("a", false),
            Arguments.of("001a", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask6_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask6(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask7_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("00", true),
            Arguments.of("01", true),
            Arguments.of("10", true),
            Arguments.of("11", false),
            Arguments.of("000", true),
            Arguments.of("001", true),
            Arguments.of("010", true),
            Arguments.of("011", false),
            Arguments.of("100", true),
            Arguments.of("101", true),
            Arguments.of("110", false),
            Arguments.of("111", false),
            Arguments.of("1000", true),
            Arguments.of("0100", true),
            Arguments.of("0010", true),
            Arguments.of("0001", true),
            Arguments.of("0000", true),
            Arguments.of("0011", false),
            Arguments.of("1010", true),
            Arguments.of("10101", true),
            Arguments.of("101011", false),
            Arguments.of("a", false),
            Arguments.of("z101", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask7_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task8.subtask7(s)).isEqualTo(expectedBoolean);
    }
}
