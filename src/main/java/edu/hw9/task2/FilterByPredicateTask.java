package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Predicate;

public class FilterByPredicateTask extends AbstractFileTask {
    private final Predicate<File> predicate;

    public FilterByPredicateTask(File file, Predicate<File> predicate) {
        super(file);
        this.predicate = predicate;
    }

    @Override
    protected List<File> processDirectoryFiles(File[] filesInDirectory) {
        List<ForkJoinTask<List<File>>> tasks = new ArrayList<>();
        List<File> filteredFiles = new ArrayList<>();
        for (File currentFile : filesInDirectory) {
            if (currentFile.isDirectory()) {
                FilterByPredicateTask task = new FilterByPredicateTask(currentFile, predicate);
                tasks.add(task.fork());
            } else if (predicate.test(currentFile)) {
                filteredFiles.add(currentFile);
            }
        }
        tasks.stream()
            .flatMap(task -> task.join().stream())
            .forEach(filteredFiles::add);
        return filteredFiles;
    }
}
