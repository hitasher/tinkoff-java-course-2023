package edu.project4.renderers;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        int width,
        int height,
        Rect world
    );
}
