package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Negate;
import edu.hw2.task1.Expression.Exponent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestExponent {
    private static Stream<Arguments> evaluateExponent_ShouldReturn8() {
        return Stream.of(
            Arguments.of(2, 3),
            Arguments.of(2.0, 3),
            Arguments.of(new Constant(2.0), new Constant(3.0)),
            Arguments.of(new Negate(-2), new Constant(3))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateExponent_ShouldReturn8(Object base, Object exp) {
        var evaluationResult = new Exponent(base, exp).evaluate();
        assertThat(evaluationResult).isEqualTo(8);
    }

    private static Stream<Arguments> evaluateExponent_ShouldReturnMinusOne() {
        return Stream.of(
            Arguments.of(-1, 3.0),
            Arguments.of(-1.0, 23),
            Arguments.of(new Constant(-1.0), 249),
            Arguments.of(new Negate(new Constant(1)), new Constant(3.0))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateExponent_ShouldReturnMinusOne(Object base, Object exp) {
        var evaluationResult = new Exponent(base, exp).evaluate();
        assertThat(evaluationResult).isEqualTo(-1);
    }

    private static Stream<Arguments> evaluateExponent_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(true, 2.0),
            Arguments.of(1.0, "-1.0"),
            Arguments.of("-1.0", "22")
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateExponent_ShouldThrowIllegalArgumentException(Object base, Object exp) {
        assertThatThrownBy(
            () -> new Exponent(base, exp).evaluate()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> evaluateMultiplication_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of("2", null),
            Arguments.of(2, null),
            Arguments.of(null, "-4.0"),
            Arguments.of(null, -4.0),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldThrowNullPointerException(Object base, Object exp) {
        assertThatThrownBy(
            () -> new Exponent(base, exp).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
