package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private int outSize;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (outSize == 0) {
            outSize = size;
            for (int i = 0; i < outSize; i++) {
                out.push(in.pop());
            }
        }
        outSize--;
        size--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}