package edu.project4.transformations;

import edu.project4.model.Point;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = TransformationCalculations.r(point);
        double x = point.x() / r / r;
        double y = point.y() / r / r;
        return new Point(x, y);
    }
}
