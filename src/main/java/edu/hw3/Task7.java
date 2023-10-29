package edu.hw3;

import java.util.Comparator;
import java.util.TreeMap;

public final class Task7 {
    private Task7() {
    }

    public static TreeMap<String, String> getTreeMapWithNullKey() {
        TreeMap<String, String> treeMap = new TreeMap<>(Comparator.nullsFirst(Comparator.naturalOrder()));
        treeMap.put(null, "test");
        return treeMap;
    }
}
