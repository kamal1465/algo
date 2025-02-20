package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * You can return the answer in any order.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * Example 2:
 * <p>
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * Example 3:
 * <p>
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 */
public class TwoSum
{
    public static void main(String[] args)
    {
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 22;
        int[] arr = twoSum.new Solution().twoSum(nums, target);
        Util.printArr2(nums);
        System.out.println("Target => " + target);
        Util.printArr2(arr);
    }

    class Solution
    {
        public int[] twoSum(int[] nums, int target)
        {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++)
            {
                int y = target - nums[i];

                if (map.get(y) != null)
                {
                    return new int[]{i, map.get(y)};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }
}
