package ru.job4j.binarytree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AvlTreeTest {

    @Test
    void symmetricalOrderIsOk() {
        AvlTree<Integer> tree = new AvlTree<>();
        for (int i = 1; i < 8; i++) {
            tree.insert(i);
        }
        List<Integer> list = tree.inSymmetricalOrder();
        assertThat(list).containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void testRightRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);

        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void testLeftRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);

        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void testLeftRightRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(3);
        tree.insert(1);
        tree.insert(2);

        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }

    @Test
    void testRightLeftRotation() {
        AvlTree<Integer> tree = new AvlTree<>();
        tree.insert(1);
        tree.insert(3);
        tree.insert(2);

        assertThat(tree.inPreOrder()).containsExactly(2, 1, 3);
    }
}