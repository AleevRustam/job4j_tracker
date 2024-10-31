package ru.job4j.newcoll.tree;

import java.util.*;

public class SimpleQueue<T> implements Queue<T> {
    private LinkedList<T> list = new LinkedList<>();

    @Override
    public boolean add(T value) {
        list.addLast(value);
        return true;
    }

    @Override
    public boolean offer(T value) {
        try {
            list.addLast(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public T remove() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.removeFirst();
    }

    @Override
    public T poll() {
        return list.isEmpty() ? null : list.removeFirst();
    }

    @Override
    public T element() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.getFirst();
    }

    @Override
    public T peek() {
        return list.isEmpty() ? null : list.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <K> K[] toArray(K[] array) {
        return list.toArray(array);
    }
}
