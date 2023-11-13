package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int findIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    @Override
    public boolean put(K key, V value) {
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        boolean result = false;
        int index = findIndex(key);
        if (Objects.isNull(table[index])) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (Objects.isNull(element)) {
                continue;
            }
            newTable[findIndex(element.key)] = element;
        }
        table = newTable;
    }

    private boolean isValueExist(MapEntry<K, V> mapEntry, K key) {
        boolean result = false;
        if (Objects.nonNull(mapEntry)) {
            if (Objects.hashCode(mapEntry.key) == Objects.hashCode(key)) {
                if (Objects.equals(mapEntry.key, key)) {
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);
        V result = null;
        if (isValueExist(table[index], key)) {
            result = table[index].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int index = findIndex(key);
        boolean result = isValueExist(table[index], key);
        if (result) {
            modCount++;
            count--;
            table[index] = null;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int startModCount = modCount;
            int index;
            @Override
            public boolean hasNext() {
                if (startModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table.length > index && table[index] == null) {
                    index++;
                }
                return table.length > index;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    public static void main(String[] args) {
        NonCollisionMap<String, String> nonCollisionMap = new NonCollisionMap<>();
        System.out.println(nonCollisionMap.hash(65536));
        System.out.println(nonCollisionMap.indexFor(7));
        System.out.println(nonCollisionMap.indexFor(8));
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}