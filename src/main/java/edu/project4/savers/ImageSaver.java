package edu.project4.savers;

import edu.project4.model.FractalImage;
import java.nio.file.Path;

public interface ImageSaver {
    void save(FractalImage image, Path filename, ImageFormat format);
}
