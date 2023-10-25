package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Multiplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMultiplication {
    @Test
    void evaluateMultiplication_ShouldReturnMinusOneFifthFromDoubles() {
        double firstOperand = 5;
        double secondOperand = -0.04;
        double evaluationResult = new Multiplication(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(-0.2);
    }

    @Test
    void evaluateMultiplication_ShouldReturnMinusOneFifthFromDoubleAndExpression() {
        double firstOperand = 5;
        Expression secondOperand = new Constant(-0.04);
        double evaluationResult = new Multiplication(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(-0.2);
    }

    @Test
    void evaluateMultiplication_ShouldReturnMinusOneFifthFromExpressionAndDouble() {
        Expression firstOperand = new Constant(5);
        double secondOperand = -0.04;
        double evaluationResult = new Multiplication(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(-0.2);
    }

    @Test
    void evaluateMultiplication_ShouldReturnMinusOneFifthFromExpressions() {
        Expression firstOperand = new Constant(5);
        Expression secondOperand = new Constant(-0.04);
        double evaluationResult = new Multiplication(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(-0.2);
    }

    private static Stream<Arguments> evaluateMultiplication_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of(new Constant(5), null),
            Arguments.of(null, new Constant(-0.04)),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateMultiplication_ShouldThrowNullPointerException(Expression firstOperand, Expression secondOperand) {
        assertThatThrownBy(
            () -> new Multiplication(firstOperand, secondOperand).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
