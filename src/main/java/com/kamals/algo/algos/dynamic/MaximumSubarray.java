package com.kamals.algo.algos.dynamic;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Example 2:
 * Input: nums = [1]
 * Output: 1
 */
public class MaximumSubarray
{
    public static void main(String[] args)
    {

    }

    /**
     * DP Soln - Time O(N^2) Space O(N)
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums)
    {
        int n = nums.length;
        int[] dp = new int[n];
        int max = Integer.MIN_VALUE;
        ;
        for (int j = 0; j < n; j++)
        {
            for (int i = 0; i < n - j; i++)
            {
                dp[i] += nums[i + j];
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * OPTIMIZED - Time O(N) Space O(1) In place, Array is corrupted
     *
     * @param nums
     * @return
     */
    public int maxSubArrayOptimized(int[] nums)
    {
        int n = nums.length;
        int max = nums[0], min = nums[0];

        for (int i = 1; i < n; i++)
        {
            nums[i] += nums[i - 1];
            int sum = min < 0 ? nums[i] - min : nums[i];
            max = Math.max(max, sum);
            min = Math.min(min, nums[i]);
        }

        return max;
    }

    /**
     * OPTIMIZED - Time O(N) Space O(1), Array is unchanged
     *
     * @param nums
     * @return
     */
    public int maxSubArrayOptimized2(int[] nums)
    {
        int n = nums.length;
        int max = nums[0], min = nums[0], sum = nums[0];

        for (int i = 1; i < n; i++)
        {
            sum += nums[i];
            max = Math.max(max, Math.max(sum, sum - min));
            min = Math.min(min, sum);
        }

        return max;
    }

    /**
     * OPTIMIZED - Time O(N) Space O(1), Array is unchanged
     * Kadane's Algorithm
     *
     * @param nums
     * @return
     */
    public int maxSubArrayOptimized3(int[] nums)
    {
        int n = nums.length;
        int max = nums[0], sum = nums[0];

        for (int i = 1; i < n; i++)
        {
            sum = Math.max(nums[i], nums[i] + sum);
            max = Math.max(max, sum);
        }

        return max;
    }
}
