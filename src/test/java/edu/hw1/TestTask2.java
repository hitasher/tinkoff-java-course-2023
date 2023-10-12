package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    @Test
    @DisplayName("Подсчёт количества цифр в числе")
    void testCountDigitsInNumber() {
        // given
        int number = 0;
        // when
        int digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(1);

        // given
        number = 7;
        // when
        digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(1);

        // given
        number = -9;
        // when
        digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(1);

        // given
        number = 544;
        // when
        digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(3);

        // given
        number = -404;
        // when
        digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(3);

        // given
        number = 4666;
        // when
        digitsInNumber = Task2.countDigitsInNumber(number);
        // the
        assertThat(digitsInNumber).isEqualTo(4);
    }
}
