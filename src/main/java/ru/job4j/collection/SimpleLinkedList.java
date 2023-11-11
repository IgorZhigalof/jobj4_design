package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {
    private int size;
    private int modCount;
    private Node<E> head;

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
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
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
                return Objects.nonNull(currentNode);
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