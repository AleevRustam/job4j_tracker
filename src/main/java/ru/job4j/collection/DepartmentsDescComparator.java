package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int res = right.split("/")[0].compareTo(left.split("/")[0]);
        if (res == 0) {
            return left.compareTo(right);
        }
        return res;
    }
}
