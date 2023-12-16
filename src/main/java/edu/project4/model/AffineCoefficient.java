package edu.project4.model;

import java.awt.Color;
import java.util.Random;

public record AffineCoefficient(double a, double b, double c, double d, double e, double f, Color color) {

    @SuppressWarnings("checkstyle:MagicNumber")
    public static AffineCoefficient generateRandom(Random random) {
        double a = random.nextDouble(-1, 1);
        double b = random.nextDouble(-1, 1);
        double c = random.nextDouble(-1, 1);
        double d = random.nextDouble(-1, 1);
        double e = random.nextDouble(-1, 1);
        double f = random.nextDouble(-1, 1);

        while (!isAffine(a, b, d, e)) {
            a = random.nextDouble(-1, 1);
            b = random.nextDouble(-1, 1);
            d = random.nextDouble(-1, 1);
            e = random.nextDouble(-1, 1);
        }

        return new AffineCoefficient(a, b, c, d, e, f, generateRandomColor(random));
    }

    private static boolean isAffine(double a, double b, double d, double e) {
        return ((a * a + d * d) < 1) && ((b * b + e * e) < 1)
            && ((a * a + b * b + d * d + e * e) < (1 + (a * e - b * d) * (a * e - b * d)));
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static Color generateRandomColor(Random random) {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}
