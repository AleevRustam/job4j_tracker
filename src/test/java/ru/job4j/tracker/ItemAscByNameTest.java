package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemAscByNameTest {

    @Test
    void compareItemAscByName() {
        List<Item> items = Arrays.asList(
                new Item("Third"),
                new Item("First"),
                new Item("Second")
        );
        List<Item> expected = Arrays.asList(
                new Item("First"),
                new Item("Second"),
                new Item("Third")
        );
        Collections.sort(items, new ItemAscByName());
        assertThat(items.equals(expected)).isTrue();
    }
}