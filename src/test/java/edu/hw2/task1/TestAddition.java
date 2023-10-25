package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Addition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestAddition {
    @Test
    void evaluateAddition_ShouldReturnOneAndHalfFromDoubles() {
        double firstOperand = -1.5;
        double secondOperand = 3;
        double evaluationResult = new Addition(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(1.5);
    }

    @Test
    void evaluateAddition_ShouldReturnOneAndHalfFromDoubleAndExpression() {
        double firstOperand = -1.5;
        Expression secondOperand = new Constant(3);
        double evaluationResult = new Addition(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(1.5);
    }

    @Test
    void evaluateAddition_ShouldReturnOneAndHalfFromExpressionAndDouble() {
        Expression firstOperand = new Constant(-1.5);
        double secondOperand = 3;
        double evaluationResult = new Addition(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(1.5);
    }

    @Test
    void evaluateAddition_ShouldReturnOneAndHalfFromExpressions() {
        Expression firstOperand = new Constant(-1.5);
        Expression secondOperand = new Constant(3);
        double evaluationResult = new Addition(firstOperand, secondOperand).evaluate();
        assertThat(evaluationResult).isEqualTo(1.5);
    }

    private static Stream<Arguments> evaluateAddition_ShouldThrowNullPointerException() {
        return Stream.of(
            Arguments.of(new Constant(-1.5), null),
            Arguments.of(null, new Constant(3)),
            Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateAddition_ShouldThrowNullPointerException(Expression firstOperand, Expression secondOperand) {
        assertThatThrownBy(
            () -> new Addition(firstOperand, secondOperand).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
