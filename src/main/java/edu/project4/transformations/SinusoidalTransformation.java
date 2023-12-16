package edu.project4.transformations;

import edu.project4.model.Point;
import static java.lang.Math.sin;

public class SinusoidalTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = sin(point.x());
        double y = sin(point.y());
        return new Point(x, y);
    }
}
