package edu.hw6;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class TestTask4 {

    @Test
    void compositionWrite_ShouldWriteTextToFile(@TempDir Path tempDir) {
        Path file = tempDir.resolve("file.txt");
        Task4.compositionWrite(file);
        assertThat(file).hasContent("Programming is learned by writing programs. ― Brian Kernighan");
    }

    @Test
    void compositionWrite_ShouldThrowRuntimeException() {
        Path file = Path.of(".");
        assertThatThrownBy(() -> Task4.compositionWrite(file)).isInstanceOf(RuntimeException.class);
    }
}
