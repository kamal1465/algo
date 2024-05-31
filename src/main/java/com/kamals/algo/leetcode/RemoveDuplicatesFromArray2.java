package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

/**
 * In-place, no extra memory, Keep 2 duplicates and truncate next
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 */
public class RemoveDuplicatesFromArray2
{

    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 1, 1};
        Util.printArr2(nums);
        RemoveDuplicatesFromArray2 remove = new RemoveDuplicatesFromArray2();
        remove.removeDuplicates2(nums);
        Util.printArr2(nums);
    }

    public int removeDuplicates2(int[] nums)
    {
        //12.47
        int n = nums.length;
        int dup = 0;
        int i = 0;
        int j = 1;
        while (j < n)
        {
            if (nums[i] == nums[j])
            {
                dup++;
                if (dup > 1)
                {
                    j++;
                    while (j < n && nums[j] == nums[j - 1])
                    {
                        j++;
                    }
                    dup = 0;
                }
            }
            else
            {
                dup = 0;
            }
            if (j < n)
            {
                nums[++i] = nums[j++];
            }
        }
        return i + 1;
    }

    public int removeDuplicates(int[] nums)
    {
        //12.47
        int i = 0;
        int n = nums.length;
        int dup = 0;
        for (; i < n - 1; i++)
        {
            if (nums[i] == nums[i + 1])
            {
                dup++;

                if (dup > 1)
                {
                    int k = i + 1;
                    int j = i + 1;
                    while (j < n && nums[j] == nums[k])
                    {
                        j++;
                    }
                    while (j < n)
                    {
                        nums[k++] = nums[j++];
                    }
                    dup = 0;
                    n = k;
                }
            }
            else
            {
                dup = 0;
            }
        }

        return Math.min(n, i + 1);
    }

}
