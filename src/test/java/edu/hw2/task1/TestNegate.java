package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Negate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestNegate {
    @Test
    void evaluateNegate_ShouldReturnOneFromDouble() {
        double value = -1.0;
        double evaluationResult = new Negate(value).evaluate();
        assertThat(evaluationResult).isEqualTo(1);
    }

    @Test
    void evaluateNegate_ShouldReturnOneFromExpression() {
        Expression expression = new Constant(-1);
        double evaluationResult = new Negate(expression).evaluate();
        assertThat(evaluationResult).isEqualTo(1);
    }

    @ParameterizedTest
    @NullSource
    void evaluateConstant_ShouldThrowNullPointerException(Expression expression) {
        assertThatThrownBy(
            () -> new Negate(expression).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
