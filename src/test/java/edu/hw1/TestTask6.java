package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask6 {
    @Test
    @DisplayName("Подсчёт количества вызовов функции Капрекара")
    void testCountK() {
        // given
        int number = 6621;
        // when
        int k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(5);

        // given
        number = 6554;
        // when
        k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(4);

        // given
        number = 1234;
        // when
        k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(3);

        // given
        number = 9876;
        // when
        k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(3);

        // given
        number = 6174;
        // when
        k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(0);
    }

    @Test
    @DisplayName("Вызов метода при некорректных аргументах")
    void testCountKWithInvalidArguments() {
        assertThatThrownBy(
            () -> Task6.countK(-1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(999)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(10000)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task6.countK(7777)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
