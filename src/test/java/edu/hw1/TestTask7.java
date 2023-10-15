package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask7 {

    @ParameterizedTest
    @CsvSource({"8, 1", "8, 5", "8, 13", "8, -3", "16, 1", "16, -4"})
    void rotateLeft_ShouldReturn1(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"8, 2", "8, 6", "8, 14", "8, -2, 16, 2, 16, -5"})
    void rotateLeft_ShouldReturn2(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource({"6, -4", "6, -1", "6, 2", "5, -2"})
    void rotateLeft_ShouldReturn3(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource({"5, 3", "5, 0", "5, -3", "6, 1", "6, -2"})
    void rotateLeft_ShouldReturn5(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource({"17, 2", "17, 7", "17, -3"})
    void rotateLeft_ShouldReturn6(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(6);
    }

    @ParameterizedTest
    @CsvSource({"16, 2", "8, 1", "8, -3", "8, 5", "4, 0", "4, -3"})
    void rotateRight_ShouldReturn4(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateRight(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(4);
    }

    @ParameterizedTest
    @CsvSource({"6, 2", "6, 5", "6, -1", "6, -4"})
    void rotateRight_ShouldReturn5(int number, int shift) {
        // when
        int rotatedNumber = Task7.rotateRight(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource({"-1, 2", "-43, -4", "0, -1298", "0, 0", "0, 43"})
    void rotateLeft_ShouldThrowIllegalArgumentException(int number, int shift) {
        assertThatThrownBy(
            () -> Task7.rotateLeft(number, shift)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({"-3, -7", "-382, 21", "0, -21", "0, 0", "0, 312"})
    void rotateRight_ShouldThrowIllegalArgumentException(int number, int shift) {
        assertThatThrownBy(
            () -> Task7.rotateRight(number, shift)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
