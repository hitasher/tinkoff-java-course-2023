package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {
    private final List<T> list;
    private int currentIndex;

    public BackwardIterator(Collection<T> collection) {
        list = new ArrayList<>(collection);
        this.currentIndex = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public T next() {
        T currentElement = list.get(currentIndex);
        --currentIndex;
        return currentElement;
    }
}
