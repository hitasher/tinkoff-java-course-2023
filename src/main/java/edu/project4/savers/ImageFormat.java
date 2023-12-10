package edu.project4.savers;

public enum ImageFormat {
    JPEG("jpeg"),
    BMP("bmp"),
    PNG("png");

    private final String extension;

    ImageFormat(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
