package com.kamals.algo.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Permutations
{
    public static void main(String[] args)
    {
        int[] nums = new int[]{1, 2, 1};
        Permutations permutations = new Permutations();
        System.out.println(permutations.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Long> map = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        permute(map, nums.length, new ArrayList<>(nums.length), result);
        return result;
    }

    public void permute(Map<Integer, Long> map, int n, List<Integer> perm, List<List<Integer>> res)
    {
        if (perm.size() == n)
        {
            res.add(new ArrayList<>(perm));
            return;
        }
        Set<Integer> set = new HashSet<>(map.keySet());
        for (int i : set)
        {
            perm.add(i);
            map.merge(i, -1L, (a, b) -> {
                long x = a + b;
                return x == 0 ? null : x;
            });
            permute(map, n, perm, res);
            perm.remove(perm.size() - 1);
            map.merge(i, 1L, Long::sum);
        }
    }

    public List<List<Integer>> permute2(int[] nums)
    {
        List<List<Integer>> res = new ArrayList<>();
        permute2(nums, res, new ArrayList<>(), new HashSet<>());
        return res;
    }

    private void permute2(int[] nums, List<List<Integer>> res, List<Integer> perm, Set<Integer> added)
    {

        if (perm.size() == nums.length)
        {
            res.add(new ArrayList<>(perm));
            return;
        }
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
        {
            if (!added.contains(i) && !unique.contains(nums[i]))
            {
                perm.add(nums[i]);
                added.add(i);
                unique.add(nums[i]);
                permute2(nums, res, perm, added);
                perm.remove(perm.size() - 1);
                added.remove(i);
            }
        }
    }
}

