package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum
{
    public static void main(String[] args)
    {

    }

    //Sort the array, choose first element and then Run two sum on remaining array
    public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < nums.length - 2 && nums[i] <= 0; i++)
        {
            if (i == 0 || nums[i] != nums[i - 1])
            {
                int j = i + 1, k = n - 1;
                int tgt = -nums[i];
                while (j < k && nums[k] >= 0)
                {
                    int sum = nums[j] + nums[k];
                    if (sum == tgt)
                    {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;
                        k--;
                        while (j < k && nums[j] == nums[j - 1])
                            j++;
                    }
                    else if (sum > tgt)
                    {
                        k--;
                    }
                    else
                    {
                        j++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k
     * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     * Example 1:
     * Input: nums = [-2,0,1,3], target = 2
     * Output: 2
     * Explanation: Because there are two triplets which sums are less than 2:
     * [-2,0,1]
     * [-2,0,3]
     */
    public int threeSumSmaller(int[] nums, int target)
    {
        int count = 0;
        int n = nums.length;
        int sum = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1, k = n - 1; j < k; )
            {
                sum = nums[i] + nums[j] + nums[k];
                if (sum < target)
                {
                    count += (k - j);
                    j++;
                }
                else
                {
                    k--;
                }
            }
        }
        return count;
    }

    /**
     * Given an integer array nums of length n and an integer target, find three integers in nums such that
     * the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     * Example 1:
     * Input: nums = [-1,2,1,-4], target = 1
     * Output: 2
     * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */
    public int threeSumClosest(int[] nums, int target)
    {
        Arrays.sort(nums);
        int n = nums.length;
        Integer res = null;
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1, k = n - 1; j < k; )
            {
                int sum = nums[i] + nums[j] + nums[k];
                if (res == null || Math.abs(target - sum) < Math.abs(target - res))
                {
                    res = sum;
                }
                if (sum > target)
                {
                    k--;
                }
                else
                {
                    j++;
                }
            }
        }
        return res;
    }
}