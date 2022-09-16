package com.kamals.algo.leetcode;

/**
 * 198. House Robber
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 213. House Robber II
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * <p>
 * Example 2:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Example 3:
 * Input: nums = [1,2,3]
 * Output: 3
 * <p>
 * Constraints:
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobber
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        System.out.println(rob2(nums));
    }

    public int rob1(int[] nums)
    {
        for (int i = 1; i < nums.length; i++)
        {
            nums[i] = Math.max(nums[i - 1], (i == 1 ? 0 : nums[i - 2]) + nums[i]);
        }
        return nums[nums.length - 1];
    }

    public static int rob2(int[] nums)
    {
        int l = nums.length;
        int max = 0;
        if (l <= 3)
        {
            for (int i = 0; i < l; i++)
            {
                max = Math.max(max, nums[i]);
            }
            return max;
        }
        int[] m = new int[l];
        m[0] = nums[0];
        for (int i = 1; i < l - 1; i++)
        {
            m[i] = Math.max(m[i - 1], (i == 1 ? 0 : m[i - 2]) + nums[i]);
        }
        max = m[l - 2];
        m[1] = nums[1];
        for (int i = 2; i < l; i++)
        {
            m[i] = Math.max(m[i - 1], (i == 2 ? 0 : m[i - 2]) + nums[i]);
        }
        max = Math.max(max, m[l - 1]);
        return max;
    }
}
