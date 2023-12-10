package edu.project4;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class ListUtils {
    private ListUtils() {
    }

    public static <T> T random(List<T> list) {
        Random random = ThreadLocalRandom.current();
        return list.get((int) (random.nextDouble() * list.size()));
    }
}
