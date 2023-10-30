package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask3 {
    private static Stream<Arguments> getCounter_ShouldReturnCounter() {
        return Stream.of(
            Arguments.of(
                Arrays.asList("a", "bb", "a", "bb"),
                Map.of(
                    "bb", 2,
                    "a", 2
                )
            ),
            Arguments.of(
                Arrays.asList("this", "and", "that", "and"),
                Map.of(
                    "that", 1,
                    "and", 2,
                    "this", 1
                )
            ),
            Arguments.of(
                Arrays.asList(1, 1, 2, 2),
                Map.of(
                    1, 2,
                    2, 2
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void getCounter_ShouldReturnCounter(List<?> list, Map<?, Integer> expectedCounter) {
        // when
        Map<?, Integer> actualCounter = Task3.getCounter(list);
        // then
        assertThat(actualCounter).isEqualTo(expectedCounter);
    }

    @ParameterizedTest
    @EmptySource
    void getCounter_ShouldReturnEmptyMap(List<?> list) {
        // when
        Map<?, Integer> counter = Task3.getCounter(list);
        // then
        assertThat(counter.isEmpty()).isTrue();
    }

    @ParameterizedTest
    @NullSource
    void getCounter_ShouldThrowNullPointerException(List<?> list) {
        assertThatThrownBy(
            () -> Task3.getCounter(list)
        ).isInstanceOf(NullPointerException.class);
    }

}
