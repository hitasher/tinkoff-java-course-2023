package edu.project4.transformations;

import edu.project4.model.Point;
import static java.lang.Math.atan;
import static java.lang.Math.sqrt;

public final class TransformationCalculations {
    private TransformationCalculations() {
    }

    public static double r(Point point) {
        return sqrt(point.x() * point.x() + point.y() * point.y());
    }

    public static double theta(Point point) {
        return atan(point.x() / point.y());
    }
}
