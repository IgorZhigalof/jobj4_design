package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;
    private boolean outMode;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (!outMode) {
            for (int i = 0; i < size; i++) {
                out.push(in.pop());
            }
            outMode = true;
        }
        size--;
        return out.pop();
    }

    public void push(T value) {
        if (outMode) {
            for (int i = 0; i < size; i++) {
                in.push(out.pop());
            }
            outMode = false;
        }
        in.push(value);
        size++;
    }
}