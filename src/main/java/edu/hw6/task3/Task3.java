package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public final class Task3 {

    public static final AbstractFilter REGULAR_FILE = Files::isRegularFile;
    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    private Task3() {
    }

    public static AbstractFilter largerThan(int size) {
        return path -> path.toFile().length() > size;
    }

    public static AbstractFilter globMatches(String glob) {
        return path -> path
            .getParent()
            .getFileSystem()
            .getPathMatcher("glob:" + glob)
            .matches(path.getFileName());
    }

    public static AbstractFilter regexContains(String regex) {
        return path -> Pattern.compile(regex).matcher(path.toString()).find();
    }

    public static AbstractFilter magicNumber(byte... bytes) {
        return path -> {
            try {
                byte[] fileBytes = Files.readAllBytes(path);
                if (fileBytes.length < bytes.length) {
                    return false;
                }
                for (int i = 0; i < bytes.length; ++i) {
                    if (fileBytes[i] != bytes[i]) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
