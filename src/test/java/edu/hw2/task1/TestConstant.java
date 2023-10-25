package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestConstant {
    @Test
    void evaluateConstant_ShouldReturnZeroFromDouble() {
        double value = 0;
        double evaluationResult = new Constant(value).evaluate();
        assertThat(evaluationResult).isEqualTo(0);
    }

    @Test
    void evaluateConstant_ShouldReturnZeroFromExpression() {
        Expression expression = new Constant(0.0);
        double evaluationResult = new Constant(expression).evaluate();
        assertThat(evaluationResult).isEqualTo(0);
    }

    @ParameterizedTest
    @NullSource
    void evaluateConstant_ShouldThrowNullPointerException(Expression expression) {
        assertThatThrownBy(
            () -> new Constant(expression).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
