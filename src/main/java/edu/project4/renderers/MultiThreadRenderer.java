package edu.project4.renderers;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.SneakyThrows;

public class MultiThreadRenderer extends AbstractRenderer {

    public MultiThreadRenderer(
        int affineCount,
        int samples,
        int iterPerSample,
        int symmetry,
        List<Transformation> variations
    ) {
        super(affineCount, samples, iterPerSample, symmetry, variations);
    }

    @SneakyThrows
    @Override
    public void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    ) {
        var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < samples; ++i) {
            executorService.execute(
                () -> renderOneSample(image, world, affineTransformations)
            );
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }
}
