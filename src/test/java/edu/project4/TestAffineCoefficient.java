package edu.project4;

import edu.project4.model.AffineCoefficient;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestAffineCoefficient {
    @Test
    public void generateRandom_ShouldReturnAffineCoefficient() {
        Random random = new Random();
        AffineCoefficient actualAffine = AffineCoefficient.generateRandom(random);
        assertThat(isAffine(
            actualAffine.a(),
            actualAffine.b(),
            actualAffine.c(),
            actualAffine.d(),
            actualAffine.e(),
            actualAffine.f())
        ).isTrue();
    }

    private static boolean isAffine(double a, double b, double c, double d, double e, double f) {
        return ((a * a + d * d) < 1) && ((b * b + e * e) < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }
}
