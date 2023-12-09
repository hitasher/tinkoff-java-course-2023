package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractFileTask extends RecursiveTask<List<File>> {

    protected final File file;

    protected abstract List<File> processDirectoryFiles(File[] filesInDirectory);

    @Override
    protected List<File> compute() {
        File[] directoryFiles = file.listFiles();
        if (directoryFiles == null) {
            return List.of();
        }
        return processDirectoryFiles(directoryFiles);
    }
}
