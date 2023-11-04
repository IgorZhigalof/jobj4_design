package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {
    private List<T> data;
    private int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return data.size() > 0;
    }

    @Override
    public T next() {
        if (data.size() == 0) {
            throw new NoSuchElementException();
        }
        if (data.size() == index) {
            index = 0;
        }
        return data.get(index++);
    }
}