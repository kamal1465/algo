package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

/**
 * 189. Rotate Array
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Example 2:
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * Constraints:
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 */
public class RotateArray
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        RotateArray rotateArray = new RotateArray();
        rotateArray.rotate(nums, 11);
        Util.printArr2(nums);
    }

    public void rotate(int[] nums, int k)
    {
        int len = nums.length;
        k = k % len;
        if (k > 0)
        {
            reverse(nums, 0, len - k - 1);
            reverse(nums, len - k, len - 1);
            reverse(nums, 0, len - 1);
        }
    }

    private void reverse(int[] nums, int i, int j)
    {
        int t;
        while (i < j)
        {
            t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }
}
