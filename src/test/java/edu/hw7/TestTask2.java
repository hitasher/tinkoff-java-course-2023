package edu.hw7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.math.BigInteger;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask2 {
    private static Stream<Arguments> calculateFactorialOf_ShouldReturnExpectedValue() {
        return Stream.of(
            Arguments.of(0, BigInteger.valueOf(1)),
            Arguments.of(1, BigInteger.valueOf(1)),
            Arguments.of(2, BigInteger.valueOf(2)),
            Arguments.of(3, BigInteger.valueOf(6)),
            Arguments.of(4, BigInteger.valueOf(24)),
            Arguments.of(5, BigInteger.valueOf(120)),
            Arguments.of(6, BigInteger.valueOf(720)),
            Arguments.of(7, BigInteger.valueOf(5040)),
            Arguments.of(8, BigInteger.valueOf(40320)),
            Arguments.of(9, BigInteger.valueOf(362880)),
            Arguments.of(10, BigInteger.valueOf(3628800)),
            Arguments.of(20, new BigInteger("2432902008176640000")),
            Arguments.of(30, new BigInteger("265252859812191058636308480000000")),
            Arguments.of(42, new BigInteger("1405006117752879898543142606244511569936384000000000")),
            Arguments.of(100, new BigInteger("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"))
        );
    }

    @ParameterizedTest
    @MethodSource
    void calculateFactorialOf_ShouldReturnExpectedValue(int number, BigInteger expectedValue) {
        BigInteger actualValue = Task2.calculateFactorialOf(number);
        assertThat(actualValue).isEqualTo(expectedValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, -99})
    void calculateFactorialOf_ShouldThrowIllegalArgumentException(int number) {
        assertThatThrownBy(
            () -> Task2.calculateFactorialOf(number)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
