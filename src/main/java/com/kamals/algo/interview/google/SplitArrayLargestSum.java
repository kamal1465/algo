package com.kamals.algo.interview.google;

import com.kamals.algo.algos.util.Util;

import java.util.*;

public class SplitArrayLargestSum
{
    public int splitArray2_BinarySearch(int[] nums, int k)
    {
        int res = -1, n = nums.length;

        int maxLimit = Arrays.stream(nums).sum();
        int minLimit = Arrays.stream(nums).max().getAsInt();

        if (k == 1)
        {
            return maxLimit;
        }
        if (k == nums.length)
        {
            return minLimit;
        }

        List<Integer> sumList = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            int sum = nums[i];
            if (sum >= minLimit)
            {
                sumList.add(sum);
            }
            for (int j = i + 1; j < n; j++)
            {
                sum += nums[j];
                if (sum >= minLimit)
                {
                    sumList.add(sum);
                }
            }
        }

        Collections.sort(sumList);

        minLimit = 0;
        maxLimit = sumList.size() - 1;

        while (minLimit <= maxLimit)
        {
            int pos = (minLimit + maxLimit) / 2;
            int guess = sumList.get(pos);

            int splitNeeded = 1, sum = 0;

            boolean flag = true;
            for (int x : nums)
            {
                int y = sum + x;
                if (y > guess)
                {
                    splitNeeded++;
                    sum = x;
                    if (splitNeeded > k)
                    {
                        flag = false;
                        minLimit = pos + 1;
                        break;
                    }
                }
                else
                {
                    sum = y;
                }
            }
            if (flag)
            {
                res = guess;
                maxLimit = pos - 1;
            }
        }

        return res;
    }

    public int splitArray(int[] nums, int k)
    {
        Util.printArr2(nums);
        int n = nums.length;

        int maxSum = Integer.MIN_VALUE;

        if (k == n)
        {
            for (int i = 0; i < n; i++)
            {
                maxSum = Math.max(maxSum, nums[i]);
            }
            return maxSum;
        }

        int[][] e = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            e[i][i] = nums[i];
            for (int j = i + 1; j < n; j++)
            {
                e[i][j] = e[i][j - 1] + nums[j];
            }
        }
        Util.printArr2(e);

        int[][] s = new int[k + 1][n];
        for (int i = 1; i <= k; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 1)
                {
                    s[i][j] = e[0][j];
                }
                else
                {
                    int min = Integer.MAX_VALUE;
                    for (int x = i - 1; x <= j; x++)
                    {
                        min = Math.min(min, Math.max(s[i - 1][x - 1], e[x][j]));
                    }
                    s[i][j] = min;
                }
            }
        }
        Util.printArr2(s);

        return s[k][n - 1];
    }

    public static void main(String[] args)
    {
        SplitArrayLargestSum splitArrayLargestSum = new SplitArrayLargestSum();
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int k = 5;
        System.out.println(splitArrayLargestSum.splitArray(nums, k));
    }
}