package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask6 {

    @ParameterizedTest
    @ValueSource(ints = {6174})
    void countK_ShouldReturn0(int number) {
        // when
        int k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(0);
    }

    @ParameterizedTest
    @ValueSource(ints = {1234, 9876, 4321, 6789})
    void countK_ShouldReturn3(int number) {
        // when
        int k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(3);
    }

    @ParameterizedTest
    @ValueSource(ints = {6554, 4556})
    void countK_ShouldReturn4(int number) {
        // when
        int k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(4);
    }

    @ParameterizedTest
    @ValueSource(ints = {6621, 1266})
    void countK_ShouldReturn5(int number) {
        // when
        int k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {2005, 5002})
    void countK_ShouldReturn7(int number) {
        // when
        int k = Task6.countK(number);
        // then
        assertThat(k).isEqualTo(7);
    }

    @ParameterizedTest
    @ValueSource(ints={-1, 0, 999, 10000, 7777})
    void countK_ShouldThrowIllegalArgumentException(int number) {
        assertThatThrownBy(
            () -> Task6.countK(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
