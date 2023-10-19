package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Negate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestNegate {
    private static Stream<Arguments> evaluateNegate_ShouldReturn0() {
        return Stream.of(
            Arguments.of(0),
            Arguments.of(0.0),
            Arguments.of(new Constant(0))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateNegate_ShouldReturn0(Object object) {
        var evaluationResult = new Negate(object).evaluate();
        assertThat(evaluationResult).isEqualTo(0);
    }

    private static Stream<Arguments> evaluateNegate_ShouldReturnMinusOne() {
        return Stream.of(
            Arguments.of(1),
            Arguments.of(1.0),
            Arguments.of(new Constant(1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateNegate_ShouldReturnMinusOne(Object object) {
        var evaluationResult = new Negate(object).evaluate();
        assertThat(evaluationResult).isEqualTo(-1);
    }

    private static Stream<Arguments> evaluateNegate_ShouldThrowIllegalArgumentException() {
        return Stream.of(
            Arguments.of(true),
            Arguments.of("-1.0")
        );
    }

    @ParameterizedTest
    @MethodSource
    void evaluateNegate_ShouldThrowIllegalArgumentException(Object object) {
        assertThatThrownBy(
            () -> new Constant(object).evaluate()
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @NullSource
    void evaluateConstant_ShouldThrowNullPointerException(Object object) {
        assertThatThrownBy(
            () -> new Negate(object).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
