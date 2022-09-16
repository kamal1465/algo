package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * ;
 * <p>
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 * <p>
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 * <p>
 * Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class LongestIncreasingSubsequence
{
    public static void main(String[] args)
    {
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        nums = new int[]{9, 3, 6, 5, 2, 7, 11, 6, 7, 8, 2};
        System.out.println(longestIncreasingSubsequence.lengthOfLIS2(nums));

        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.ceilingKey(1);
    }

    /**
     * N^2 Solution with a clever strategy to break out of inner loop if checking further will not help
     * by maintaining a runningMax, meaning current i'th element has already found best match (preceding element in LIS)
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums)
    {
        int N = nums.length;
        int[] lisLengths = new int[N];
        int[] runningMax = new int[N];

        lisLengths[0] = runningMax[0] = 1;

        for (int i = 1; i < N; i++)
        {
            for (int j = i - 1; j >= 0; j--)
            {
                if (lisLengths[i] >= runningMax[j])
                {
                    break;
                }
                if (nums[i] > nums[j])
                {
                    lisLengths[i] = Math.max(lisLengths[j], lisLengths[i]);
                }
            }
            lisLengths[i]++;
            runningMax[i] = Math.max(lisLengths[i], runningMax[i - 1]);
        }
        return runningMax[N - 1];
    }

    /**
     * Ingenuous idea - For a particular LIS length, only need to check Last element having that LIS length, that element
     * is guaranteed to be smallest among all elements having that LIS length, o/w this element would have had greater LIS length
     * Maintain an Map of LIS lengths seen so far vs. Index of last element with that LIS length
     * LIS length can be 1 <= LIS Length <= N, an array of size N can work as map with index as key (LIS length)
     * Additionally this array be binary searched, since indexes are sorted. Even without binary search, it is pretty fast
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums)
    {
        int N = nums.length;
        int[] lisLastPos = new int[N + 1];
        Arrays.fill(lisLastPos, -1);
        lisLastPos[1] = 0;
        int max = 1;
        Util.printArr2(nums);
        for (int i = 1; i < N; i++)
        {
            boolean found = false;
            for (int j = max; j > 0; j--)
            {
                int pos = lisLastPos[j];
                if (nums[i] > nums[pos])
                {
                    found = true;
                    lisLastPos[j + 1] = i;
                    max = Math.max(j + 1, max);
                    break;
                }
            }
            if (!found)
            {
                lisLastPos[1] = i;
            }
            Util.printArr2(lisLastPos);
        }
        return max;
    }

    /**
     * N*Log(N) Solution using Binary Search
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS3(int[] nums)
    {
        int N = nums.length;
        int[] lisLastPos = new int[N + 1];
        Arrays.fill(lisLastPos, -1);
        //LIS of size 1's last position is 0. First element whatever is, is a LIS of 1, seen yet
        lisLastPos[1] = 0;
        int max = 1;
        Util.printArr2(nums);
        for (int i = 1; i < N; i++)
        {
            max = find(nums, lisLastPos, i, max);
            Util.printArr2(lisLastPos);
        }
        return max;
    }

    private int find(int[] nums, int[] lisLastPos, int index, int maxLis)
    {
        int start = 1, end = maxLis, maxIn = -1;

        while (start <= end)
        {
            int j = (start + end) / 2;

            int pos = lisLastPos[j];
            if (nums[index] > nums[pos])
            {
                maxIn = j;
                start = j + 1;
            }
            else
            {
                end = j - 1;
            }
        }

        if (maxIn > -1)
        {
            lisLastPos[maxIn + 1] = index;
            maxLis = Math.max(maxIn + 1, maxLis);
        }
        else
        {
            lisLastPos[1] = index;
        }
        return maxLis;
    }
}
