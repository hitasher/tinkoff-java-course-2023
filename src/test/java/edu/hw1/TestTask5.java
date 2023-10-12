package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask5 {
    @Test
    @DisplayName("Проверка, является ли число или один из его потомков палиндромом")
    void testIsPalindromeDescendant() {
        // given
        int number = 11211230;
        // when
        boolean isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 13001120;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 23336014;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 11;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 1;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 123;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isFalse();

        // given
        number = 1331;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 1322;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 1323;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isFalse();

        // given
        number = 1324;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isFalse();

        // given
        number = 23;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isFalse();

        // given
        number = 0;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 133113;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 133913;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isTrue();

        // given
        number = 133110;
        // when
        isPalindromeDescendant = Task5.isPalindromeDescendant(number);
        // then
        assertThat(isPalindromeDescendant).isFalse();
    }

    @Test
    @DisplayName("Вызов метода при некорректных аргументах")
    void testIsPalindromeDescendantWithInvalidArguments() {
        assertThatThrownBy(
            () -> Task5.isPalindromeDescendant(-1)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
