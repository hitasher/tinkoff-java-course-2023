package edu.hw6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {

    @Test
    void cloneFile_ShouldCloneFile(@TempDir Path tempDir) throws IOException {
        Path filePath = Files.createFile(tempDir.resolve("READ.ME.md"));
        Task2.cloneFile(filePath);
        assertThat(Files.exists(tempDir.resolve("READ.ME - копия.md"))).isTrue();
        Task2.cloneFile(filePath);
        assertThat(Files.exists(tempDir.resolve("READ.ME - копия (2).md"))).isTrue();
        Task2.cloneFile(filePath);
        assertThat(Files.exists(tempDir.resolve("READ.ME - копия (3).md"))).isTrue();
        assertThat(Files.exists(tempDir.resolve("READ.ME - копия (4).md"))).isFalse();

        filePath = Files.createFile(tempDir.resolve("example - копия.txt"));
        Task2.cloneFile(filePath);
        assertThat(Files.exists(tempDir.resolve("example - копия - копия.txt"))).isTrue();
        assertThat(Files.exists(tempDir.resolve("example - копия - копия (1).txt"))).isFalse();
    }
}
