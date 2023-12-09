package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class FilterByNumberOfFilesTask extends AbstractFileTask {
    private final int requiredNumberOfFiles;

    public FilterByNumberOfFilesTask(File file, int requiredNumberOfFiles) {
        super(file);
        this.requiredNumberOfFiles = requiredNumberOfFiles;
    }

    @Override
    protected List<File> processDirectoryFiles(File[] filesInDirectory) {
        List<ForkJoinTask<List<File>>> tasks = new ArrayList<>();
        List<File> filteredFiles = new ArrayList<>();
        int numberOfFiles = 0;
        for (File currentFile : filesInDirectory) {
            if (!currentFile.isDirectory()) {
                numberOfFiles++;
            } else {
                FilterByNumberOfFilesTask task = new FilterByNumberOfFilesTask(currentFile, requiredNumberOfFiles);
                tasks.add(task.fork());
            }
        }
        if (numberOfFiles >= requiredNumberOfFiles) {
            filteredFiles.add(file);
        }
        tasks.stream()
            .flatMap(task -> task.join().stream())
            .forEach(filteredFiles::add);
        return filteredFiles;
    }
}
