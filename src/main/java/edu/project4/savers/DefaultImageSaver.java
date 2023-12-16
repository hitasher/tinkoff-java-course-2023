package edu.project4.savers;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import lombok.SneakyThrows;

public class DefaultImageSaver implements ImageSaver {
    @SneakyThrows
    @Override
    public void save(FractalImage image, Path path, ImageFormat format) {
        BufferedImage renderedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < image.width(); ++x) {
            for (int y = 0; y < image.height(); ++y) {
                Pixel pixel = image.pixel(x, y);
                Color color = new Color(pixel.r(), pixel.g(), pixel.b());
                renderedImage.setRGB(x, y, color.getRGB());
            }
        }
        ImageIO.write(renderedImage, format.getExtension(), path.toFile());
    }
}
