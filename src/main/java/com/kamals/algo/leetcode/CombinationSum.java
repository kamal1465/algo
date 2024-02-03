package com.kamals.algo.leetcode;

import java.util.*;

public class CombinationSum
{

    public static void main(String[] args)
    {
        CombinationSum com = new CombinationSum();
//        int[] nums = new int[]{1, 50};
        int[] nums = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580, 590, 600, 610, 620, 630, 640, 650, 660, 670, 680, 690, 700, 710, 720, 730, 740, 750, 760, 770, 780, 790, 800, 810, 820, 830, 840, 850, 860, 870, 880, 890, 900, 910, 920, 930, 940, 950, 960, 970, 980, 990, 111};
        System.out.println(com.combinationSum42(nums, 999));
    }

    public int combinationSum42(int[] nums, int target)
    {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++)
        {
            for (int j : nums)
            {
                if (i >= j)
                {
                    dp[i] += dp[i - j];
                }
            }
        }
        return dp[target];
    }

    public int combinationSum4(int[] nums, int target)
    {
        int[] total = new int[1];
        Arrays.sort(nums);
        backtrack(nums, target, 0, new ArrayList<>(), total);
        return total[0];
    }

    private void backtrack(int[] nums, int target, int i, List<Integer> arr, int[] total)
    {
        if (i < nums.length)
        {
            if (nums[i] == target)
            {
                arr.add(nums[i]);
                total[0] += calc(arr);
                arr.remove(arr.size() - 1);
            }
            else if (nums[i] < target)
            {
                arr.add(nums[i]);
                backtrack(nums, target - nums[i], i, arr, total);
                arr.remove(arr.size() - 1);
                backtrack(nums, target, i + 1, arr, total);
            }
        }
    }

    private long calc(List<Integer> arr)
    {
        if (arr.size() <= 1)
        {
            return arr.size();
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i : arr)
        {
            int z = map.merge(i, 1, (a, b) -> a + b);
            max = Math.max(max, z);
        }
        long x = facto(arr.size(), max);
        boolean done = false;
        for (int i : map.values())
        {
            if (i == max && !done)
            {
                done = true;
                continue;
            }
            long y = facto(i);
            x /= y;
        }
        System.out.println(arr + " = " + x);
        return x;
    }

    static Map<Integer, Long> fact = new HashMap<>();

    private Long facto(int n)
    {
        if (n <= 2)
        {
            return (long) n;
        }
        if (fact.containsKey(n))
        {
            return fact.get(n);
        }
        long x = n * facto(n - 1);
        fact.put(n, x);
        return x;
    }

    private Long facto(int n, int m)
    {
        if (n == m)
        {
            return 1L;
        }
        return n * facto(n - 1, m);
    }
}
