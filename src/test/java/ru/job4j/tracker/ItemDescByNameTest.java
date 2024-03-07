package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemDescByNameTest {
    @Test
    void compareItemDescByName() {
        List<Item> items = Arrays.asList(
                new Item("First"),
                new Item("Second"),
                new Item("Third")
        );
        List<Item> expected = Arrays.asList(
                new Item("Third"),
                new Item("Second"),
                new Item("First")
        );
        Collections.sort(items, new ItemDescByName());
        assertThat(items.equals(expected)).isTrue();
    }

}