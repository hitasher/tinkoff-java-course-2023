package edu.project4;

import edu.project4.model.Rect;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.renderers.SingleThreadRenderer;
import edu.project4.transformations.SphereTransformation;
import edu.project4.transformations.SwirlTransformation;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestRenderers {
    @Test
    void singleThreadRenderer_ShouldNotThrowAnyExceptions() {
        Renderer renderer = new SingleThreadRenderer(
            5, 5, 3000, 5, List.of(new SphereTransformation())
        );
        assertDoesNotThrow(() -> renderer.render(400, 400, new Rect(-3, -4, 6, 8)));
    }

    @Test
    void multiThreadRenderer_ShouldNotThrowAnyExceptions() {
        Renderer renderer = new MultiThreadRenderer(
            3, 7, 5000, 3, List.of(new SwirlTransformation())
        );
        assertDoesNotThrow(() -> renderer.render(256, 256, new Rect(-4, -3, 8, 6)));
    }
}
