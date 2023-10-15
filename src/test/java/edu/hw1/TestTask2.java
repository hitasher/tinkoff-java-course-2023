package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 7, -9})
    void countDigitsInNumber_ShouldReturn1(int number) {
        // when
        int digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {16, -27, 99, -82})
    void countDigitsInNumber_ShouldReturn2(int number) {
        // when
        int digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {544, 112, -404})
    void countDigitsInNumber_ShouldReturn3(int number) {
        // when
        int digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {4666, 6174, -8642})
    void countDigitsInNumber_ShouldReturn4(int number) {
        // when
        int digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(4);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_000_000_000, -2_000_000_000})
    void countDigitsInNumber_ShouldReturn10(int number) {
        // when
        int digitsInNumber = Task2.countDigitsInNumber(number);
        // then
        assertThat(digitsInNumber).isEqualTo(10);
    }
}
