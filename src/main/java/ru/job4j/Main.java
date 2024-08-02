package ru.job4j;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] result = merge(
                new int[] {1, 3},
                new int[] {2, 4}
        );
        System.out.println(Arrays.toString(result));
    }

    public static int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0, j = 0, r = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                rsl[r] = left[i];
                i++;
            } else {
                rsl[r] = right[j];
                j++;
            }
            r++;
        }
        if (i < left.length) {
            System.arraycopy(left, i, rsl, r, (left.length - i));
        }
        if (j < right.length) {
            System.arraycopy(right, j, rsl, r, (right.length - j));
        }
        return rsl;
    }
}
