package ru.job4j.newcoll.tree;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        return !linked.iterator().hasNext();
    }

}
