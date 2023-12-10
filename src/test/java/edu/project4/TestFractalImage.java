package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestFractalImage {
    @Test
    void pixel_ShouldReturnNull() {
        FractalImage image = FractalImage.create(10, 20);
        assertThat(image.pixel(11, 15)).isNull();
        assertThat(image.pixel(9,23)).isNull();
    }

    @Test
    void getPixelAtPoint_ShouldReturnNull() {
        FractalImage image = FractalImage.create(10, 20);
        Rect rect = new Rect(-3, -4, 6, 8);
        Point point = new Point(-4, 7);
        assertThat(image.getPixelAtPoint(rect, point)).isNull();
    }
}
