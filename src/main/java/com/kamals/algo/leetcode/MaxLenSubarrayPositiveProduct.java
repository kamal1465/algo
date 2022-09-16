package com.kamals.algo.leetcode;

/**
 * 1567. Maximum Length of Subarray With Positive Product
 * Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 * Return the maximum length of a subarray with positive product.
 * <p>
 * Example 1:
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 */
public class MaxLenSubarrayPositiveProduct
{
    public static void main(String[] args)
    {
        int[] a = new int[]{0, -19, 26, -24, -13, -2, 26, 10, 0, 4, 0, -26, -22, 9, 35, -11, -14, 0, -29};
        System.out.println(getMaxLen(a));
    }

    public static int getMaxLen2(int[] nums)
    {
        int len = nums.length;
        int totalNeg = 0, firstNeg = -1, lastNeg = -1, maxCount = 0;
        int start = -1, end = -1;
        for (int i = 0; i < len; i++)
        {
            if (nums[i] == 0)
            {
                if (start != -1)
                {
                    if (totalNeg % 2 == 0)
                    {
                        maxCount = Math.max(maxCount, end - start + 1);
                    }
                    else
                    {
                        maxCount = Math.max(maxCount, Math.max(lastNeg - start, end - firstNeg));
                    }
                    totalNeg = 0;
                    firstNeg = lastNeg = -1;
                    start = end = -1;
                }
            }
            else
            {
                if (start == -1)
                {
                    start = i;
                }
                end = i;
                if (nums[i] < 0)
                {
                    totalNeg++;
                    if (firstNeg == -1)
                    {
                        firstNeg = i;
                    }
                    lastNeg = i;
                }
            }
        }
        if (start >= 0 && start <= end)
        {
            if (totalNeg % 2 == 0)
            {
                maxCount = Math.max(maxCount, end - start + 1);
            }
            else
            {
                maxCount = Math.max(maxCount, Math.max(lastNeg - start, end - firstNeg));
            }
        }
        return maxCount;
    }

    public static int getMaxLen(int[] nums)
    {
        int len = nums.length;
        int pos = 0, count = 0, maxCount = 0;
        for (int i = 0; i < len; i++)
        {
            if (nums[i] == 0)
            {
                pos = 0;
                count = 0;
            }
            else
            {
                count++;
                pos = (pos + (nums[i] < 0 ? 1 : 0)) % 2;
                if (pos == 0)
                {
                    maxCount = Math.max(maxCount, count);
                }
            }
        }

        pos = 0;
        count = 0;
        for (int i = len - 1; i >= 0; i--)
        {
            if (nums[i] == 0)
            {
                pos = 0;
                count = 0;
            }
            else
            {
                count++;
                pos = (pos + (nums[i] < 0 ? 1 : 0)) % 2;
                if (pos == 0)
                {
                    maxCount = Math.max(maxCount, count);
                }
            }
        }
        return maxCount;
    }
}
