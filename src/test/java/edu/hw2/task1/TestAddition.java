package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Negate;
import edu.hw2.task1.Expression.Exponent;
import edu.hw2.task1.Expression.Addition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestAddition {
    private static Stream<Arguments> evaluateAddition_ShouldReturn0() {
        return Stream.of(
            Arguments.of(-4, 4.0),
            Arguments.of(new Constant(-3.0), 3),
            Arguments.of(new Negate(-5), new Constant(-5.0)),
            Arguments.of(new Exponent(-2.0, 4), new Negate(16)),
            Arguments.of(new Exponent(-2.0, 3), new Constant(8.0))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateAddition_ShouldReturn0(Object firstOperand, Object secondOperand) {
        var evaluationResult = new Addition(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(0);
    }

    private static Stream<Arguments> evaluateAddition_ShouldReturnOneAndHalf() {
        return Stream.of(
            Arguments.of(3, -1.5),
            Arguments.of(-1, 2.5),
            Arguments.of(new Exponent(-1, 3), new Negate(-2.5))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateAddition_ShouldReturnOneAndHalf(Object firstOperand, Object secondOperand) {
        var evaluationResult = new Addition(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(1.5);
    }

    private static Stream<Arguments> evaluateAddition_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(1.0, false),
            Arguments.of("0.05", 7),
            Arguments.of("-1.0", "-1")
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateAddition_ShouldThrowIllegalArgumentException(Object firstOperand, Object secondOperand) {
        assertThatThrownBy(
            () -> new Addition(firstOperand, secondOperand).evaluate()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> evaluateAddition_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of("0", null),
            Arguments.of(0, null),
            Arguments.of(null, "1"),
            Arguments.of(null, 1),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateAddition_ShouldThrowNullPointerException(Object firstOperand, Object secondOperand) {
        assertThatThrownBy(
            () -> new Addition(firstOperand, secondOperand).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
