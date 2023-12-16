package edu.project4;

import edu.project4.model.Point;
import edu.project4.model.Rect;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestRect {
    @Test
    public void rotatePointRelativeToTheCenter_ShouldReturnExpectedPoint() {
        Rect rect = new Rect(0, 0, 6, 8);
        Point point = new Point(1, 2);
        double angle = Math.PI / 2;
        Point expectedPoint = new Point(5, 2);
        Point actualPoint = rect.rotatePointRelativeToTheCenter(point, angle);
        assertThat(actualPoint).isEqualTo(expectedPoint);
    }

    @Test
    public void randomPoint_ShouldReturnPointInRect() {
        Rect rect = new Rect(0, 0, 8, 6);
        Point picked = rect.getRandomPoint();
        assertThat(rect.notContains(picked)).isFalse();
    }
}
