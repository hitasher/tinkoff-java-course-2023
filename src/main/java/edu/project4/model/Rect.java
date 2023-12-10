package edu.project4.model;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {
    public boolean notContains(Point p) {
        return !(p.x() >= x) || !(p.x() < x + width) || !(p.y() >= y) || !(p.y() < y + height);
    }

    public Point getRandomPoint() {
        Random random = ThreadLocalRandom.current();
        return new Point(x() + random.nextDouble() * width(), y() + random.nextDouble() * height());
    }

    public Point rotatePointRelativeToTheCenter(Point point, double angle) {
        double centerX = x() + width() / 2;
        double centerY = y() + height() / 2;
        return new Point(
            (point.x() - centerX) * Math.cos(angle) - (point.y() - centerY) * Math.sin(angle) + centerX,
            (point.x() - centerX) * Math.sin(angle) + (point.y() - centerY) * Math.cos(angle) + centerY
        );
    }
}
