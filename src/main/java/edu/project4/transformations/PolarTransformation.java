package edu.project4.transformations;

import edu.project4.model.Point;
import static java.lang.Math.PI;

public class PolarTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = TransformationCalculations.r(point);
        double theta = TransformationCalculations.theta(point);
        double x = theta / PI;
        double y = r - 1;
        return new Point(x, y);
    }
}
