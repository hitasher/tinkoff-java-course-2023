package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask2 {
    private static Stream<Arguments> clusterizeBrackets_ShouldReturnClusters() {
        return Stream.of(
            Arguments.of("()()()", Arrays.asList("()", "()", "()")),
            Arguments.of("((()))", List.of("((()))")),
            Arguments.of("((()))(())()()(()())", Arrays.asList("((()))", "(())", "()", "()", "(()())")),
            Arguments.of("((())())(()(()()))", Arrays.asList("((())())", "(()(()()))"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void clusterizeBrackets_ShouldReturnClusters(String bracketSequence, List<String> expectedClusters) {
        // when
        List<String> actualClusters = Task2.clusterizeBrackets(bracketSequence);
        // then
        assertThat(actualClusters).isEqualTo(expectedClusters);
    }

    @ParameterizedTest
    @EmptySource
    void clusterizeBrackets_ShouldReturnEmptyList(String bracketSequence) {
        // when
        List<String> clusters = Task2.clusterizeBrackets(bracketSequence);
        // then
        assertThat(clusters.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @NullSource
    void clusterizeBrackets_ShouldThrowNullPointerException(String bracketSequence) {
        assertThatThrownBy(
            () -> Task2.clusterizeBrackets(bracketSequence)
        ).isInstanceOf(NullPointerException.class);
    }

    public static Stream<Arguments> clusterizeBrackets_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of("a"),
            Arguments.of("(a)"),
            Arguments.of("()("),
            Arguments.of("())()"),
            Arguments.of("()(()")
        );
    }

    @ParameterizedTest
    @MethodSource
    void clusterizeBrackets_ShouldThrowIllegalArgumentException(String bracketSequence) {
        assertThatThrownBy(
            () -> Task2.clusterizeBrackets(bracketSequence)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}
