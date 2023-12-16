package edu.project4.transformations;

import edu.project4.model.Point;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.cosh;
import static java.lang.Math.sin;
import static java.lang.Math.sinh;

public class CosineTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = cos(PI * point.x()) * cosh(point.y());
        double y = -sin(PI * point.x()) * sinh(point.y());
        return new Point(x, y);
    }
}
