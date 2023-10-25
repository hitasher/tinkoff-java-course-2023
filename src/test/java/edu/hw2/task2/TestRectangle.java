package edu.hw2.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestRectangle {
    private static Stream<Arguments> testGetArea() {
        return Stream.of(
            Arguments.of(new Rectangle(1, 1)),
            Arguments.of(new Square(1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testGetArea(Rectangle rectangle) {
        rectangle = rectangle.setWidth(20);
        rectangle = rectangle.setHeight(10);

        assertThat(rectangle.getArea()).isEqualTo(200.0);
    }
}
