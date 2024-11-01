package ru.job4j.newcoll.tree;

import java.util.*;

public class TreeUtils<T> {
    /**
     * Метод выполняет обход дерева и считает количество узлов
     *
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
     *
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

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     *
     * @param root   корень дерева
     * @param parent ключ узла-родителя
     * @param child  ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }
        if (find(root, child) != null) {
            return false;
        }

        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (currentNode.getValue().equals(parent)) {
                currentNode.getChildren().add(new Node<>(child));
                return true;
            }
            for (Node<T> childNode : currentNode.getChildren()) {
                stack.push(childNode);
            }
        }
        return false;
    }

    private Node<T> find(Node<T> root, T key) {
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node<T> currentNode = stack.pop();
            if (currentNode.getValue().equals(key)) {
                return currentNode;
            }
            for (Node<T> child : currentNode.getChildren()) {
                stack.push(child);
            }
        }
        return null;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }
        stack.push(root);

        Node<T> currentNode;
        while ((currentNode = stack.pop()) != null) {
            if (currentNode.getValue().equals(key)) {
                return Optional.of(currentNode);
            }

            for (Node<T> child : currentNode.getChildren()) {
                stack.push(child);
            }
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     *
     * @param root корень дерева
     * @param key  ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }

        if (root.getValue().equals(key)) {
            return Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);

        Node<T> curentNode;
        while ((curentNode = stack.pop()) != null) {
            for (Node<T> child : curentNode.getChildren()) {
                if (child.getValue().equals(key)) {
                    curentNode.getChildren().remove(child);
                    return Optional.of(child);
                }
                stack.push(child);
            }
        }
        return Optional.empty();
    }
}
