package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask7 {

    private static Stream<Arguments> subtask1_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", false),
            Arguments.of("1", false),
            Arguments.of("00", false),
            Arguments.of("01", false),
            Arguments.of("10", false),
            Arguments.of("11", false),
            Arguments.of("000", true),
            Arguments.of("001", false),
            Arguments.of("010", true),
            Arguments.of("011", false),
            Arguments.of("100", true),
            Arguments.of("101", false),
            Arguments.of("110", true),
            Arguments.of("111", false),
            Arguments.of("0000", true),
            Arguments.of("0010", false),
            Arguments.of("0010101001010", false),
            Arguments.of("1001010101010", true),
            Arguments.of("01010101001101010", true),
            Arguments.of("01010010101010100100100", true),
            Arguments.of("a", false),
            Arguments.of("ab0", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask1_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task7.subtask1(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask2_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("00", true),
            Arguments.of("01", false),
            Arguments.of("10", false),
            Arguments.of("11", true),
            Arguments.of("000", true),
            Arguments.of("001", false),
            Arguments.of("010", true),
            Arguments.of("011", false),
            Arguments.of("100", false),
            Arguments.of("101", true),
            Arguments.of("110", false),
            Arguments.of("111", true),
            Arguments.of("2", false),
            Arguments.of("22", false),
            Arguments.of("12", false),
            Arguments.of("222", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask2_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task7.subtask2(s)).isEqualTo(expectedBoolean);
    }

    private static Stream<Arguments> subtask3_ShouldReturnExpectedBoolean() {
        return Stream.of(
            Arguments.of("", false),
            Arguments.of("0", true),
            Arguments.of("1", true),
            Arguments.of("00", true),
            Arguments.of("01", true),
            Arguments.of("10", true),
            Arguments.of("11", true),
            Arguments.of("000", true),
            Arguments.of("001", true),
            Arguments.of("010", true),
            Arguments.of("011", true),
            Arguments.of("100", true),
            Arguments.of("101", true),
            Arguments.of("110", true),
            Arguments.of("111", true),
            Arguments.of("0000", false),
            Arguments.of("0011", false),
            Arguments.of("1010", false),
            Arguments.of("1111", false),
            Arguments.of("3", false),
            Arguments.of("55", false),
            Arguments.of("69", false),
            Arguments.of("389", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void subtask3_ShouldReturnExpectedBoolean(String s, boolean expectedBoolean) {
        assertThat(Task7.subtask3(s)).isEqualTo(expectedBoolean);
    }
}
