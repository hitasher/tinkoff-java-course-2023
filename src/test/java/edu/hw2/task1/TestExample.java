package edu.hw2.task1;

import edu.hw2.task1.Expression.Constant;
import edu.hw2.task1.Expression.Negate;
import edu.hw2.task1.Expression.Exponent;
import edu.hw2.task1.Expression.Addition;
import edu.hw2.task1.Expression.Multiplication;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestExample {
    @Test
    void testExample() {
        var two = new Constant(2);
        var four = new Constant(4);
        var minusOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var multiplication = new Multiplication(sumTwoFour, minusOne);
        var exponent = new Exponent(multiplication, 2);
        var result = new Addition(exponent, new Constant(1));
        double evaluationResult = result.evaluate();
        assertThat(evaluationResult).isEqualTo(37);
    }
}
