package edu.project4.transformations;

import edu.project4.model.Point;
import static java.lang.Math.sin;

public class HandkerchiefTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = TransformationCalculations.r(point);
        double theta = TransformationCalculations.theta(point);
        double x = r * sin(theta + r);
        double y = r * sin(theta - r);
        return new Point(x, y);
    }
}
