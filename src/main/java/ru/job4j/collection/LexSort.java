package ru.job4j.collection;

import java.util.Arrays;
import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int[] leftNum = stringToArrayInt(left);
        int[] rightNum = stringToArrayInt(right);
        return Arrays.compare(leftNum, rightNum);
    }

    public int[] stringToArrayInt(String string) {
        String[] strings = (string.split(". "))[0].split("\\.");
        int[] result = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
}
