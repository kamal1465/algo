package com.kamals.algo.leetcode;

import java.util.Arrays;

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 * Example 2:
 * <p>
 * Input: nums = [5,4,3,2,1]
 * Output: false
 * Explanation: No triplet exists.
 * Example 3:
 * <p>
 * Input: nums = [2,1,5,0,4,6]
 * Output: true
 * Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
 */
public class IncreasingTripletSubsequence
{
    public static void main(String[] args)
    {
        IncreasingTripletSubsequence increasingTripletSubsequence = new IncreasingTripletSubsequence();
        int[] nums = new int[]{2, 1, 5, 0, 4, 3};
        System.out.println(increasingTripletSubsequence.increasingTriplet(nums));
    }

    private boolean increasingTriplet(int[] nums)
    {
        int[] subsequenceLengthVsIndexMap = new int[4];
        Arrays.fill(subsequenceLengthVsIndexMap, -1);
        subsequenceLengthVsIndexMap[1] = 0;
        int max = 1;
        for (int i = 1; i < nums.length; i++)
        {
            boolean found = false;
            for (int j = max; j > 0; j--)
            {
                if (nums[i] > nums[subsequenceLengthVsIndexMap[j]])
                {
                    found = true;
                    subsequenceLengthVsIndexMap[j + 1] = i;
                    max = Math.max(j + 1, max);
                    break;
                }
            }
            if (!found)
            {
                subsequenceLengthVsIndexMap[1] = i;
            }
            if (max == 3)
            {
                return true;
            }
        }
        return false;
    }
}
