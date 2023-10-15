package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask5 {
    @ParameterizedTest
    @ValueSource(ints = {11211230, 13001120, 23336014, 11, 1, 1331, 1322, 0, 133113, 133913})
    void isPalindromeDescendant_ShouldReturnTrue(int number) {
        // when
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {123, 1323, 1324, 23, 133110})
    void isPalindromeDescendant_ShouldReturnFalse(int number) {
        // when
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void isPalindromeDescendant_ShouldThrowIllegalArgumentException(int number) {
        assertThatThrownBy(
            () -> Task5.isPalindromeDescendant(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
