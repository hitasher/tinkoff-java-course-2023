package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Negate;
import edu.hw2.task1.Expression.Multiplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMultiplication {
    private static Stream<Arguments> evaluateMultiplication_ShouldReturn0() {
        return Stream.of(
            Arguments.of(-329, 0.0),
            Arguments.of(0, 0),
            Arguments.of(new Constant(0.0), 8133),
            Arguments.of(new Negate(0), new Constant(27))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldReturn0(Object firstOperand, Object secondOperand) {
        var evaluationResult = new Multiplication(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(0);
    }

    private static Stream<Arguments> evaluateMultiplication_ShouldReturnMinusOneFifth() {
        return Stream.of(
            Arguments.of(2.0, -0.1),
            Arguments.of(new Constant(0.1), new Negate(2)),
            Arguments.of(new Negate(0.04), 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldReturnMinusOneFifth(Object firstOperand, Object secondOperand) {
        var evaluationResult = new Multiplication(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(-0.2);
    }

    private static Stream<Arguments> evaluateMultiplication_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of("-0.0001", "500"),
            Arguments.of(false, 0.05),
            Arguments.of(1, "0.05")
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldThrowIllegalArgumentException(Object firstOperand, Object secondOperand) {
        assertThatThrownBy(
            () -> new Multiplication(firstOperand, secondOperand).evaluate()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> evaluateMultiplication_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of("-1.0", null),
            Arguments.of(-1.0, null),
            Arguments.of(null, "1"),
            Arguments.of(null, 1),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldThrowNullPointerException(Object firstOperand, Object secondOperand) {
        assertThatThrownBy(
            () -> new Multiplication(firstOperand, secondOperand).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
