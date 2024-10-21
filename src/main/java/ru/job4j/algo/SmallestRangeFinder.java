package ru.job4j.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int minRange = Integer.MAX_VALUE;
        int[] result = null;

        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < n; right++) {
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);
            while (freqMap.size() == k) {
                if (right - left + 1 < minRange) {
                    minRange = right - left + 1;
                    result = new int[]{left, right};
                }

                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);

                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }

                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9};
        int k = 3;
        int[] result = findSmallestRange(nums, k);
        if (result != null) {
            System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
        } else {
            System.out.println("Такой диапазон не существует.");
        }
    }
}
