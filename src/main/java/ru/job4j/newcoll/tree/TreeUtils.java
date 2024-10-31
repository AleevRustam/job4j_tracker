package ru.job4j.newcoll.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeUtils<T> {
    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        Queue<Node<T>> queue = new SimpleQueue<>();

        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }

        queue.add(root);
        int count = 0;

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            count++;
            for (Node<T> child : currentNode.getChildren()) {
                queue.add(child);
            }
        }
        return count;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        Queue<Node<T>> queue = new SimpleQueue<>();

        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }

        List<T> result = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.poll();
            result.add(currentNode.getValue());
            for (Node<T> child : currentNode.getChildren()) {
                queue.add(child);
            }
        }
        return result;
    }
}
