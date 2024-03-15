package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        if (right.split("/")[0].compareTo(left.split("/")[0]) == 0) {
            return left.compareTo(right);
        }
        return right.split("/")[0].compareTo(left.split("/")[0]);
    }
}
