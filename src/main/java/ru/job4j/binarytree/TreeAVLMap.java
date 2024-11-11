package ru.job4j.binarytree;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {
    private Node root;

    public boolean insert(T key, V value) {
        if (root == null) {
            root = new Node(key, value);
            return true;
        } else {
            int oldSize = size();
            root = insert(root, key, value);
            return size() == oldSize || size() > oldSize;
        }
    }

    private Node insert(Node node, T key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = insert(node.left, key, value);
        } else if (comparisonResult > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }
        updateHeight(node);
        return balance(node);
    }

    public boolean remove(T key) {
        int oldSize = size();
        root = remove(root, key);
        return size() < oldSize;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node minNode = getMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = remove(node.right, minNode.key);
        }
        updateHeight(node);
        return balance(node);
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public V get(T key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, T key) {
        while (node != null) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node = node.left;
            } else if (comparisonResult > 0) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }

    public Set<T> keySet() {
        Set<T> keys = new TreeSet<>();
        inOrderKeySet(root, keys);
        return keys;
    }

    private void inOrderKeySet(Node node, Set<T> keys) {
        if (node != null) {
            inOrderKeySet(node.left, keys);
            keys.add(node.key);
            inOrderKeySet(node.right, keys);
        }
    }

    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        inOrderValues(root, values);
        return values;
    }

    private void inOrderValues(Node node, List<V> values) {
        if (node != null) {
            inOrderValues(node.left, values);
            values.add(node.value);
            inOrderValues(node.right, values);
        }
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;

        updateHeight(node);
        updateHeight(newParent);

        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;

        updateHeight(node);
        updateHeight(newParent);

        return newParent;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private int size() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
