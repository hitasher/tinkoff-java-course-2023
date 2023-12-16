package edu.project4.renderers;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer {
    public SingleThreadRenderer(
        int affineCount,
        int samples,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @Override
    public void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        for (int i = 0; i < samples; ++i) {
            renderOneSample(image, world, affineTransformations);
        }
    }
}
