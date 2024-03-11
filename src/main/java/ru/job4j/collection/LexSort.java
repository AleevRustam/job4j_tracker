package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int leftNum = stringToArrayInt(left);
        int rightNum = stringToArrayInt(right);
        return leftNum - rightNum;
    }

    public int stringToArrayInt(String string) {
        String[] strings = string.split("\\.");
        return Integer.parseInt(strings[0]);
    }
}
