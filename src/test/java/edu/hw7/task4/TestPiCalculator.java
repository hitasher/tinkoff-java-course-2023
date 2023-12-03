package edu.hw7.task4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.concurrent.ExecutionException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestPiCalculator {

    private final static int numberOfIterations = 100_000_000;
    private final static Offset<Double> OFFSET = Offset.offset(0.005);

    @Test
    void calculatePi_ShouldReturnValueCloseToPi() {
        double calculatedPi = PiCalculator.calculatePi(numberOfIterations);
        assertThat(calculatedPi).isCloseTo(Math.PI, OFFSET);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 16})
    void calculatePiInParallel_ShouldReturnValueCloseToPi(int numberOfThreads)
        throws ExecutionException, InterruptedException {
        double calculatedPi = PiCalculator.calculatePiInParallel(numberOfIterations, numberOfThreads);
        assertThat(calculatedPi).isCloseTo(Math.PI, OFFSET);
        System.out.println(calculatedPi);
        System.out.println(Math.abs(calculatedPi - Math.PI) / Math.PI * 100);
    }
}
