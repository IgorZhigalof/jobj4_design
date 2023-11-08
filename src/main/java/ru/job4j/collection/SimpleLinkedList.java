package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    private Node<E> node(int index) {
        Node<E> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public void add(E value) {
        modCount++;
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            Node<E> node = node(size - 1);
            node.next = new Node<>(value, null);
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