package edu.project4.renderers;

import edu.project4.ListUtils;
import edu.project4.model.AffineCoefficient;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractRenderer implements Renderer {

    private static final int STEPS_FOR_NORMALIZATION = 20;

    protected final int affineCount;
    protected final int samples;
    protected final int iterPerSample;
    protected final int symmetry;
    protected final List<Transformation> variations;

    public FractalImage render(int width, int height, Rect world) {
        FractalImage image = FractalImage.create(width, height);
        List<AffineTransformation> affineTransformations = generateAffineTransformations();
        renderAllImage(image, world, affineTransformations);
        return image;
    }

    public abstract void renderAllImage(
        FractalImage image,
        Rect world,
        List<AffineTransformation> affineTransformations
    );

    private List<AffineTransformation> generateAffineTransformations() {
        Random random = ThreadLocalRandom.current();
        return IntStream
            .range(0, affineCount)
            .mapToObj(i -> new AffineTransformation(AffineCoefficient.generateRandom(random)))
            .toList();
    }

    protected void renderOneSample(FractalImage image, Rect world, List<AffineTransformation> affineTransformations) {
        Point currentPoint = world.getRandomPoint();
        for (int step = -STEPS_FOR_NORMALIZATION; step < iterPerSample; ++step) {
            AffineTransformation affine = ListUtils.random(affineTransformations);
            Transformation transformation = ListUtils.random(variations);
            currentPoint = affine.apply(currentPoint);
            currentPoint = transformation.apply(currentPoint);
            if (step > 0) {
                double theta = 0.0;
                for (int chunk = 0; chunk < symmetry; theta += Math.PI * 2 / symmetry, ++chunk) {
                    Point point = world.rotatePointRelativeToTheCenter(currentPoint, theta);
                    processPoint(world, image, point, affine);
                }
            }
        }
    }

    private void processPoint(Rect world, FractalImage image, Point point, AffineTransformation affine) {
        if (world.notContains(point)) {
            return;
        }
        Pixel pixel = image.getPixelAtPoint(world, point);
        if (pixel != null) {
            synchronized (pixel) {
                Color color = affine.affineCoefficient().color();
                pixel.recordHit(color);
            }
        }
    }
}
