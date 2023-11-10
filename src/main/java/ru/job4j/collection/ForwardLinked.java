package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<E> implements SimpleLinked<E>, Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> head;

    public void addFirst(E value) {
        head = new Node<>(value, head);
    }

    @Override
    public void add(E value) {
        modCount++;
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            Node<E> currentNode = head;
            int bound = size - 1;
            for (int i = 0; i < bound; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node<>(value, null);
        }
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Iterator<E> iterator = iterator();
        for (int i = 0; i < index; i++) {
            iterator.next();
        }
        return iterator.next();
    }

    public E deleteFirst() {
        if (Objects.isNull(head)) {
            throw new NoSuchElementException();
        }
        Node<E> toDel = head;
        head = toDel.next;
        E value = toDel.item;
        toDel.item = null;
        toDel.next = null;
        size--;
        modCount++;
        return value;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int startModCount = modCount;
            Node<E> currentNode = head;
            @Override
            public boolean hasNext() {
                if (startModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return !Objects.isNull(currentNode);
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> res = currentNode;
                currentNode = currentNode.next;
                return res.item;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}