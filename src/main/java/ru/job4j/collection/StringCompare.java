package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int maxSize = Math.max(left.length(), right.length());
        int minSize = Math.min(left.length(), right.length());
        int index = 0;
        char leftChar;
        char rightChar;
        int result = 0;
        while (index < minSize) {
            leftChar = left.charAt(index);
            rightChar = right.charAt(index);
            if (leftChar == rightChar && leftChar != ' ' && rightChar != ' ') {
                index++;
            } else {
                result = Character.compare(leftChar, rightChar);
                break;
            }
        }
        if (minSize != maxSize) {
            result = Integer.compare(left.length(), right.length());
        }

        return result;
    }

}
