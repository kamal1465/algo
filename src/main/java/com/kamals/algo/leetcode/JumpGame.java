package com.kamals.algo.leetcode;

/**
 * 45. Jump Game II
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * You can assume that you can always reach the last index.
 * <p>
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame
{
    public static void main(String[] args)
    {
        JumpGame jumpGame = new JumpGame();
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(jumpGame.jump(nums));
    }

    public int jump(int[] nums)
    {
        int len = nums.length;
        int i = 0, j = 0, k = 0;

        int count = 0;

        while (j < len - 1)
        {
            k = j;

            for (; i <= j; i++)
            {
                k = Math.max(k, i + nums[i]);
            }
            count++;
            j = k;
        }
        return count;
    }

    public int jump2(int[] nums)
    {
        int length = nums.length;
        if (length == 1)
        {
            return 0;
        }
        if (nums[0] >= length - 1)
        {
            return 1;
        }
        int[] jumps = new int[length];
        jumps[0] = nums[0];
        int index = 0;
        for (int i = 1; i < length; i++)
        {
            jumps[i] = Math.max(jumps[i - 1], i + nums[i]);
            if (jumps[i] >= length - 1)
            {
                index = i;
                break;
            }
        }
        int count = 0;
        int x = jumps[index] - nums[index];
        for (int i = index - 1; i >= 0; i--)
        {
            if (jumps[i] >= x)
            {
                count++;
                x = jumps[i] - nums[i];
            }
        }
        return count + 1;
    }
}
