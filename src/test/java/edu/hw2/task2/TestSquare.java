package edu.hw2.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestSquare {
    private static Stream<Arguments> testGetArea() {
        return Stream.of(
            Arguments.of(new Square(2))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetArea(Square square) {
        assertThat(square.getArea()).isEqualTo(4);
    }

    private static Stream<Arguments> testSetSizeLength() {
        return Stream.of(
            Arguments.of(new Square(3)),
            Arguments.of(new Square(1))

        );
    }

    @ParameterizedTest
    @MethodSource
    void testSetSizeLength(Square square) {
        square = square.setSizeLength(5);
        assertThat(square.getArea()).isEqualTo(25);
    }
}
