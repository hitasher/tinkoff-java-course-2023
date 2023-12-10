package edu.project4.processors;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;

public class LogarithmicGammaCorrectionImageProcessor implements ImageProcessor {
    private static final double GAMMA = 1.25;

    @Override
    public void process(FractalImage image) {
        double maxNormal = getMaxNormal(image);
        for (int x = 0; x < image.width(); ++x) {
            for (int y = 0; y < image.height(); ++y) {
                Pixel pixel = image.pixel(x, y);
                pixel.setNormal(pixel.normal() / maxNormal);
                pixel.setRGB(
                    (int) (pixel.r() * Math.pow(pixel.normal(), (1.0 / GAMMA))),
                    (int) (pixel.g() * Math.pow(pixel.normal(), (1.0 / GAMMA))),
                    (int) (pixel.b() * Math.pow(pixel.normal(), (1.0 / GAMMA)))
                );
            }
        }

    }

    private static double getMaxNormal(FractalImage image) {
        double maxNormal = 0.0;
        for (int x = 0; x < image.width(); ++x) {
            for (int y = 0; y < image.height(); ++y) {
                Pixel currentPixel = image.pixel(x, y);
                if (currentPixel.hitCount() == 0) {
                    continue;
                }
                double normal = Math.log10(currentPixel.hitCount());
                currentPixel.setNormal(normal);
                if (normal > maxNormal) {
                    maxNormal = normal;
                }
            }
        }
        return maxNormal;
    }
}
