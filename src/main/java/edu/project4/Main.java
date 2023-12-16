package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.Rect;
import edu.project4.processors.ImageProcessor;
import edu.project4.processors.LogarithmicGammaCorrectionImageProcessor;
import edu.project4.renderers.MultiThreadRenderer;
import edu.project4.renderers.Renderer;
import edu.project4.savers.DefaultImageSaver;
import edu.project4.savers.ImageFormat;
import edu.project4.savers.ImageSaver;
import edu.project4.transformations.SphereTransformation;
import edu.project4.transformations.SwirlTransformation;
import java.nio.file.Path;
import java.util.List;

public final class Main {

    private Main() {
    }

    private final static int IMAGE_WIDTH = 1920;
    private final static int IMAGE_HEIGHT = 1080;
    private final static Rect AREA = new Rect(-3, -4, 6, 8);
    private final static Renderer RENDERER =
        new MultiThreadRenderer(
            5,
            5,
            1000000,
            5,
            List.of(new SphereTransformation(), new SwirlTransformation())
        );
    private final static Path PATH = Path.of("fractal-flames.png");
    private final static ImageFormat IMAGE_FORMAT = ImageFormat.PNG;

    private final static List<ImageProcessor> IMAGE_PROCESSORS = List.of(
        new LogarithmicGammaCorrectionImageProcessor()
    );

    public static void main(String[] args) {
        FractalImage image = RENDERER.render(IMAGE_WIDTH, IMAGE_HEIGHT, AREA);
        for (ImageProcessor imageProcessor : IMAGE_PROCESSORS) {
            imageProcessor.process(image);
        }
        ImageSaver imageSaver = new DefaultImageSaver();
        imageSaver.save(image, PATH, IMAGE_FORMAT);
    }
}
