package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String leftStartWith = getStartWith(left);
        String rightStartWith = getStartWith(right);
        String leftEhdWith = getEndWith(left);
        String rightEndWith = getEndWith(right);
        if (leftStartWith.compareTo(rightStartWith) == 0) {
            for (int i = 0; i < Math.min(leftEhdWith.length(), rightEndWith.length()); i++) {
                if (leftEhdWith.charAt(i) != rightEndWith.charAt(i)) {
                    return Character.compare(leftEhdWith.charAt(i), rightEndWith.charAt(i));
                }
            }
            return Integer.compare(leftEhdWith.length(), rightEndWith.length());
        }
        return rightStartWith.compareTo(leftStartWith);
    }

    private String getStartWith(String string) {
        if (string.contains("/")) {
            return string.substring(0, string.indexOf("/"));
        }
        return string;
    }

    private String getEndWith(String string) {
        if (string.contains("/")) {
            return string.substring(string.indexOf("/"));
        }
        return "";
    }
}
