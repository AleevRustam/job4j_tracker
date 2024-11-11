package ru.job4j.binarytree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void whenAddToEmptyTreeThenListContainsOneElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(1)
                .containsExactly("first");
    }

    @Test
    void whenAddTwoToEmptyTreeThenListContainsTwoElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThat(tree.put("first")).isTrue();
        assertThat(tree.put("second")).isTrue();
        assertThat(tree.inSymmetricalOrder()).hasSize(2)
                .containsExactly("first", "second");
    }

    @Test
    void whenAddElementThenContainElementOk() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.put("first");
        tree.put("second");
        tree.put("three");
        assertThat(tree.contains("second")).isTrue();
        assertThat(tree.contains("four")).isFalse();
    }

    @Test
    void whenAddMaximumNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 8, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(8);
    }

    @Test
    void whenAddMaximumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.maximum()).isEqualTo(7);
    }

    @Test
    void whenAddMinimumIsEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(1);
    }

    @Test
    void whenAddMinimumIsNotEndThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7 }) {
            tree.put(element);
        }
        assertThat(tree.minimum()).isEqualTo(2);
    }

    @Test
    void whenSymmetricalOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inSymmetricalOrder()).hasSize(7)
                .containsExactly(1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    void whenPreOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPreOrder()).hasSize(7)
                .containsExactly(4, 2, 1, 3, 6, 5, 7);
    }

    @Test
    void whenPostOrderThenOk() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 3, 5, 7, 1}) {
            tree.put(element);
        }
        assertThat(tree.inPostOrder()).hasSize(7)
                .containsExactly(1, 3, 2, 5, 7, 6, 4);
    }

    @Test
    void whenRemoveNodeWithOneChildThenTreeUpdates() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 5}) {
            tree.put(element);
        }
        assertThat(tree.remove(6)).isTrue();
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 4, 5);
    }

    @Test
    void whenRemoveNodeWithTwoChildrenThenTreeUpdates() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.remove(4)).isTrue();
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 3, 5, 6, 7);
    }

    @Test
    void whenRemoveRootNodeThenTreeUpdates() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6, 1, 3, 5, 7}) {
            tree.put(element);
        }
        assertThat(tree.remove(4)).isTrue();
        assertThat(tree.inSymmetricalOrder()).containsExactly(1, 2, 3, 5, 6, 7);
    }

    @Test
    void whenRemoveNonExistentElementThenReturnFalse() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.put(4);
        assertThat(tree.remove(10)).isFalse();
    }

    @Test
    void whenClearTreeThenTreeIsEmpty() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6}) {
            tree.put(element);
        }

        assertThat(tree.inSymmetricalOrder()).hasSize(3);

        tree.clear();

        assertThat(tree.inSymmetricalOrder()).isEmpty();
    }

    @Test
    void whenClearTreeMultipleTimesThenNoErrors() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int element : new int[]{4, 2, 6}) {
            tree.put(element);
        }
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).isEmpty();
        tree.clear();
        assertThat(tree.inSymmetricalOrder()).isEmpty();
    }
}