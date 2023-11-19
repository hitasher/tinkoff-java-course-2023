package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Task2 {

    private Task2() {
    }

    public static void cloneFile(Path path) throws IOException {
        String directory = path.getParent().toString();
        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String fileNameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));

        Path pathOfClonedFile = Paths.get(directory, fileNameWithoutExtension + " - копия" + extension);
        int counter = 2;
        while (Files.exists(pathOfClonedFile)) {
            pathOfClonedFile = Paths.get(
                directory,
                String.format("%s - копия (%d)%s", fileNameWithoutExtension, counter, extension)
            );
            ++counter;
        }

        Files.copy(path, pathOfClonedFile);
    }
}
