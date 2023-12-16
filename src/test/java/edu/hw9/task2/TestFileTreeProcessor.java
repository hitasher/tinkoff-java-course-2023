package edu.hw9.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class TestFileTreeProcessor {

    private File root;

    @BeforeEach
    void initEach(@TempDir File tempDir) throws IOException {
        root = tempDir;
        File dir1 = createNewDirectory(root, "dir1");
        createNewFile(dir1, "file1.txt");
        createNewFile(dir1, "file2.java");
        createNewFile(dir1, "file3.md");
        createNewFile(dir1, "file4.java");
        File dir2 = createNewDirectory(root, "dir2");
        createNewFile(dir2, "file5.py");
        File subdir1 = createNewDirectory(dir2, "subdir1");
        createNewFile(subdir1, "file6.cpp");
        createNewFile(subdir1, "file7.java");
        createNewDirectory(dir2, "subdir2");
    }

    @Test
    void filterDirectoriesByNumberOfFiles_ShouldReturnExpectedFiles() {
        List<File> actualFiles = FileTreeProcessor.filterDirectoriesByNumberOfFiles(root, 2);
        assertThat(actualFiles).containsExactlyInAnyOrder(
            new File(root, "dir1"),
            new File(root, "dir2/subdir1")
        );
    }

    @Test
    void filterFilesByPredicate_ShouldReturnExpectedFiles() {
        List<File> actualFiles = FileTreeProcessor.filterFilesByPredicate(root, file -> file.getName().endsWith(".java"));
        assertThat(actualFiles).containsExactlyInAnyOrder(
            new File(root, "dir1/file2.java"),
            new File(root, "dir1/file4.java"),
            new File(root, "dir2/subdir1/file7.java")
        );
    }

    private static File createNewDirectory(File parent, String name) {
        File newDirectory = new File(parent, name);
        if (!newDirectory.mkdir()) {
            throw new RuntimeException();
        }
        return newDirectory;
    }

    private static void createNewFile(File parent, String name) throws IOException {
        File newFile = new File(parent, name);
        if (!newFile.createNewFile()) {
            throw new RuntimeException();
        }
    }
}
