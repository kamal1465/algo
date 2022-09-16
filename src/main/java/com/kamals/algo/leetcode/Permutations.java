package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Permutations
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 2, 4};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>(nums.length);
        permute(nums, list, result);
        return result;
    }

    public void permute(int[] nums, List<Integer> list, List<List<Integer>> result)
    {
        if (list.size() == nums.length)
        {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i : nums)
        {
            if (!list.contains(i))
            {
                list.add(i);
                permute(nums, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
}
