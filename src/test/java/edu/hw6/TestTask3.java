package edu.hw6;

import edu.hw6.task3.Task3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestTask3 {
    @TempDir
    private Path tempDir;
    private List<Path> paths;

    @BeforeEach
    void initEach() {
        paths = new ArrayList<>();
        try {
            paths.add(Files.writeString(
                Files.createTempFile(tempDir, "file_with_content", ".txt"),
                "content here")
            );
            paths.add(Files.write(
                Files.createTempFile(tempDir, "byte_written", ".bin"),
                new byte[] {(byte) 0x89, 'P', 'N', 'G'})
            );
            paths.add(Files.createTempFile(tempDir, "file_without_content", ".txt"));
            paths.add(Files.createTempFile(tempDir, "a", ".bin"));
            paths.add(Files.createTempFile(tempDir, "b", ".md"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void largerThan_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.largerThan(4);
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths).containsExactly(paths.get(0));
    }

    @Test
    public void globMatches_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.globMatches("*.bin");
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths).containsExactlyInAnyOrder(paths.get(1), paths.get(3));
    }

    @Test
    public void regexContains_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.regexContains("content");
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths).containsExactlyInAnyOrder(paths.get(0), paths.get(2));
    }

    @Test
    public void magicNumbers_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.magicNumber(
            (byte) 0x89, (byte) 'P', (byte) 'N', (byte) 'G'
        );
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths).containsExactlyInAnyOrder(paths.get(1));
    }

    @Test
    public void readable_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.READABLE;
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths.size()).isEqualTo(paths.size());
        assertThat(actualFilteredPaths).containsAll(paths);
    }

    @Test
    public void writable_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.WRITABLE;
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths.size()).isEqualTo(paths.size());
        assertThat(actualFilteredPaths).containsAll(paths);
    }

    @Test
    public void regularFile_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter = Task3.REGULAR_FILE;
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths.size()).isEqualTo(paths.size());
        assertThat(actualFilteredPaths).containsAll(paths);
    }

    @Test
    public void multipleFilter_ShouldReturnExpectedPaths() {
        DirectoryStream.Filter<Path> filter =
            Task3.REGULAR_FILE
                .and(Task3.WRITABLE)
                .and(Task3.READABLE)
                .and(Task3.globMatches("*.txt"));
        List<Path> actualFilteredPaths = useFilter(filter);
        assertThat(actualFilteredPaths).containsExactlyInAnyOrder(paths.get(0), paths.get(2));
    }

    private List<Path> useFilter(DirectoryStream.Filter<Path> filter) {
        List<Path> actualFilteredPaths = new ArrayList<>();
        try (DirectoryStream<Path> path = Files.newDirectoryStream(tempDir, filter)) {
            path.forEach(actualFilteredPaths::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return actualFilteredPaths;
    }
}
