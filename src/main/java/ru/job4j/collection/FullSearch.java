package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;

public class FullSearch {
    private HashSet<String> set;

    public HashSet<String> extractNumber(List<Task> list) {
        set = new HashSet<>();
        for (Task task : list) {
            set.add(task.getNumber());
        }
        return set;
    }
}
