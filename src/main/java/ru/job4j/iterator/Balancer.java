package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        CyclicIterator<ArrayList<Integer>> iterator = new CyclicIterator<>(nodes);
        while (source.hasNext()) {
            iterator.next().add(source.next());
        }
    }
}