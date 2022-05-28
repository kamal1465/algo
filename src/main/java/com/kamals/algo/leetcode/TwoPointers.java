package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

public class TwoPointers
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        Util.printArr2(nums);
        moveZeroes(nums);
        Util.printArr2(nums);
        nums = new int[]{2, 7, 11, 15};
        Util.printArr2(nums);
        Util.printArr2(twoSum(nums, 18));
    }

    /**
     * 167. Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
     * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
     * The tests are generated such that there is exactly one solution. You may not use the same element twice.
     * Your solution must use only constant extra space.
     * <p>
     * Example 1:
     * Input: numbers = [2,7,11,15], target = 9
     * Output: [1,2]
     * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2]
     */
    public static int[] twoSum(int[] numbers, int target)
    {
        int i = 0, j = numbers.length - 1;
        int sum;
        while (i < j)
        {
            sum = numbers[i] + numbers[j];
            if (sum == target)
            {
                return new int[]{i + 1, j + 1};
            }
            else if (sum > target)
            {
                j--;
            }
            else
            {
                i++;
            }
        }
        return null;
    }

    /**
     * 283. Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * Note that you must do this in-place without making a copy of the array.
     * <p>
     * Example 1:
     * Input: nums = [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */
    public static void moveZeroes(int[] nums)
    {
        int N = nums.length;
        int i = 0, j = 0;
        while (j < N)
        {
            if (nums[j] == 0)
            {
                j++;
            }
            else
            {
                nums[i++] = nums[j++];
            }
        }
        while (i < N)
        {
            nums[i++] = 0;
        }
    }
}
