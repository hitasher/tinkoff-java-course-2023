package edu.project4.transformations;

import edu.project4.model.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SwirlTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double r = TransformationCalculations.r(point);
        double x = point.x() * sin(r * r) - point.y() * cos(r * r);
        double y = point.x() * cos(r * r) + point.y() * sin(r * r);
        return new Point(x, y);
    }
}
