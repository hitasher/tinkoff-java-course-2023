package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask3 {
    @Test
    @DisplayName("Проверка, может ли первый массив быть вложен во второй")
    void testIsFirstArrayNestedInSecond() {
        // given
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {0, 6};
        // when
        boolean isFirstArrayNestedInSecond = Task3.isFirstArrayNestedInSecond(arr1, arr2);
        // then
        assertThat(isFirstArrayNestedInSecond).isTrue();

        // given
        arr1 = new int[]{3, 1};
        arr2 = new int[]{4, 0};
        // when
        isFirstArrayNestedInSecond = Task3.isFirstArrayNestedInSecond(arr1, arr2);
        // then
        assertThat(isFirstArrayNestedInSecond).isTrue();

        // given
        arr1 = new int[]{9, 9, 8};
        arr2 = new int[]{8, 9};
        // when
        isFirstArrayNestedInSecond = Task3.isFirstArrayNestedInSecond(arr1, arr2);
        // then
        assertThat(isFirstArrayNestedInSecond).isFalse();

        // given
        arr1 = new int[]{1, 2, 3, 4};
        arr2 = new int[]{2, 3};
        // when
        isFirstArrayNestedInSecond = Task3.isFirstArrayNestedInSecond(arr1, arr2);
        // then
        assertThat(isFirstArrayNestedInSecond).isFalse();
    }

    @Test
    @DisplayName("Вызов метода при некорректных аргументах")
    void testIsFirstArrayNestedInSecondWithInvalidArguments() {
        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(null, new int[]{1, 2})
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(new int[]{1, 2}, null)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(null, null)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(new int[]{}, new int[]{0, 1})
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(new int[]{0, 1}, new int[]{})
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> Task3.isFirstArrayNestedInSecond(new int[]{}, new int[]{})
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
