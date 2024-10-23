package ru.job4j.algo;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        int k = 0;

        System.out.println(subarraySum(nums, k));
    }

    public static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int currentSum = 0;
        int count = 0;

        if (n == 1 && nums[0] != k) {
            return 0;
        }

        while (right < n) {
            currentSum += nums[right];

            while (currentSum > k && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            if (currentSum == k) {
                count++;
            }

            right++;
        }
        return count;
    }
}
