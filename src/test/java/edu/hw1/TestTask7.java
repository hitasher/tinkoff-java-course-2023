package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask7 {
    @Test
    @DisplayName("Циклический сдвиг числа влево")
    void testRotateLeft() {
        // given
        int number = 8;
        int shift = 1;
        // when
        int rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(1);

        // given
        number = 5;
        shift = 3;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(5);

        // given
        number = 8;
        shift = 2;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(2);

        // given
        number = 5;
        shift = -3;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(5);

        // given
        number = 6;
        shift = -4;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(3);

        // given
        number = 8;
        shift = -1;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(4);

        // given
        number = 16;
        shift = 1;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(1);

        // given
        number = 17;
        shift = 2;
        // when
        rotatedNumber = Task7.rotateLeft(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(6);
    }

    @Test
    @DisplayName("Циклический сдвиг числа вправо")
    void testRotateRight() {
        // given
        int number = 8;
        int shift = 1;
        // when
        int rotatedNumber = Task7.rotateRight(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(4);

        // given
        number = 6;
        shift = 2;
        // when
        rotatedNumber = Task7.rotateRight(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(5);

        // given
        number = 8;
        shift = 6;
        // when
        rotatedNumber = Task7.rotateRight(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(2);

        // given
        number = 6;
        shift = -3;
        // when
        rotatedNumber = Task7.rotateRight(number, shift);
        // then
        assertThat(rotatedNumber).isEqualTo(6);
    }

    @Test
    @DisplayName("Вызов метода циклического сдвига влево при некорректных аргументах")
    void testRotateLeftWithInvalidArguments() {
        assertThatThrownBy(
            () -> Task7.rotateLeft(-1, 5)
        ).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    @DisplayName("Вызов метода циклического сдвига вправо при некорректных аргументах")
    void testRotateRightWithInvalidArguments() {
        assertThatThrownBy(
            () -> Task7.rotateRight(-1, 3)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
