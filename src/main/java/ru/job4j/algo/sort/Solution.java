package ru.job4j.algo.sort;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[] {3, 6, 10, 1};
        System.out.println(Arrays.toString(nums));
        System.out.println(maximumGap(nums));
        System.out.println(maxGapByHash(nums));
    }

    public static int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int maxGap = 0;
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }

        return maxGap;
    }

    public static int maxGapByHash(int[] nums) {

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        if (nums.length < 2) {
            return 0;
        }

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxGap = 0;
        int previous = min;

        for (int i = min; i <= max; i++) {
            if (set.contains(i)) {
                maxGap = Math.max(maxGap, i - previous);
                previous = i;
            }
        }

        return maxGap;
    }
}
