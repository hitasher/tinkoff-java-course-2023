package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Exponent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestExponent {
    @Test
    void evaluateExponent_ShouldReturnEightFromDoubles() {
        double base = 2;
        double power = 3;
        double evaluationResult = new Exponent(base, power).evaluate();
        assertThat(evaluationResult).isEqualTo(8);
    }

    @Test
    void evaluateExponent_ShouldReturnEightFromDoubleAndExpression() {
        double base = 2;
        Expression power = new Constant(3);
        double evaluationResult = new Exponent(base, power).evaluate();
        assertThat(evaluationResult).isEqualTo(8);
    }

    @Test
    void evaluateExponent_ShouldReturnEightFromExpressionAndDouble() {
        Expression base = new Constant(2);
        double power = 3;
        double evaluationResult = new Exponent(base, power).evaluate();
        assertThat(evaluationResult).isEqualTo(8);
    }

    @Test
    void evaluateExponent_ShouldReturnEightFromExpressions() {
        Expression base = new Constant(2);
        Expression power = new Constant(3);
        double evaluationResult = new Exponent(base, power).evaluate();
        assertThat(evaluationResult).isEqualTo(8);
    }

    private static Stream<Arguments> evaluateMultiplication_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of(new Constant(2), null),
            Arguments.of(null, new Constant(3)),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldThrowNullPointerException(Expression base, Expression power) {
        assertThatThrownBy(
            () -> new Exponent(base, power).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
