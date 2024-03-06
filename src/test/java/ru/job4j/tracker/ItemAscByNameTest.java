package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ItemAscByNameTest {

    @Test
    void compareItemAscByName() {
        Item item1 = new Item("First");
        Item item2 = new Item("Second");
        Item item3 = new Item("Third");
        List<Item> items = new ArrayList<>();
        items.add(item3);
        items.add(item1);
        items.add(item2);
        List<Item> expected = new ArrayList<>();
        expected.add(item1);
        expected.add(item2);
        expected.add(item3);
        Collections.sort(items, new ItemAscByName());
        assertThat(items.equals(expected)).isTrue();
    }
}