package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask3 {
    private static Stream<Arguments> isFirstArrayNestedInSecond_ShouldReturnTrue() {
        return Stream.of(
            Arguments.of(new int[]{1, 2, 3, 4}, new int[]{0, 6}),
            Arguments.of(new int[]{3, 1}, new int[]{4, 0})
        );
    }

    @ParameterizedTest
    @MethodSource
    void isFirstArrayNestedInSecond_ShouldReturnTrue(int[] arr1, int[] arr2) {
        // when
        boolean isFirstArrayNestedInSecond = Task3.isFirstArrayNestedInSecond(arr1, arr2);
        // then
        assertThat(isFirstArrayNestedInSecond).isTrue();
    }

    private static Stream<Arguments> isFirstArrayNestedInSecond_ShouldReturnFalse() {
        return Stream.of(
            Arguments.of(new int[]{9, 9, 8}, new int[]{8, 9}),
            Arguments.of(new int[]{1, 2, 3, 4}, new int[]{2, 3})
        );
    }

    @ParameterizedTest
    @MethodSource
    void isFirstArrayNestedInSecond_ShouldReturnFalse(int[] arr1, int[] arr2) {
        // when
        boolean isFirstArrayNestedInSecond = Task3.isFirstArrayNestedInSecond(arr1, arr2);
        // then
        assertThat(isFirstArrayNestedInSecond).isFalse();
    }

    private static Stream<Arguments> isFirstArrayNestedInSecond_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of(null, new int[]{1, 2}),
            Arguments.of(new int[]{1, 2}, null),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void isFirstArrayNestedInSecond_ShouldThrowNullPointerException(int[] arr1, int[] arr2) {
        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(arr1, arr2)
        ).isInstanceOf(NullPointerException.class);
    }

    private static Stream<Arguments> isFirstArrayNestedInSecond_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(new int[]{}, new int[]{1, 2}),
            Arguments.of(new int[]{1, 2}, new int[]{}),
            Arguments.of(new int[]{}, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource
    void isFirstArrayNestedInSecond_ShouldThrowIllegalArgumentException(int[] arr1, int[] arr2) {
        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(arr1, arr2)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
