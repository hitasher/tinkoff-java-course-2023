package edu.hw9.task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public final class FileTreeProcessor {

    private FileTreeProcessor() {
    }

    private static final ForkJoinPool FORK_JOIN_POOL = ForkJoinPool.commonPool();

    public static List<File> filterDirectoriesByNumberOfFiles(File rootFile, int number) {
        List<File> result = FORK_JOIN_POOL.invoke(new FilterByNumberOfFilesTask(rootFile, number));
        FORK_JOIN_POOL.shutdown();
        return result;
    }

    public static List<File> filterFilesByPredicate(File rootFile, Predicate<File> predicate) {
        List<File> result = FORK_JOIN_POOL.invoke(new FilterByPredicateTask(rootFile, predicate));
        FORK_JOIN_POOL.shutdown();
        return result;
    }
}
