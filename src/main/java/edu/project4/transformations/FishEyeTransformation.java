package edu.project4.transformations;

import edu.project4.model.Point;

public class FishEyeTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double r = TransformationCalculations.r(point);
        double x = 2 / (r + 1) * point.y();
        double y = 2 / (r + 1) * point.x();
        return new Point(x, y);
    }
}
