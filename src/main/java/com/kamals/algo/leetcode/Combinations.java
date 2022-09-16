package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations
{
    public static void main(String[] args)
    {
        Combinations combinations = new Combinations();
        List<List<Integer>> result = combinations.combine(4, 2);
        System.out.println(result);
    }

    public List<List<Integer>> combine(int n, int k)
    {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        find(n, 1, k, list, result);
        return result;
    }

    public void find(int n, int index, int k, List<Integer> list, List<List<Integer>> result)
    {
        System.out.println("i = " + index + " list = " + list);
        if (index == n + 1)
        {
            if (k == list.size())
            {
                result.add(new ArrayList<>(list));
            }
            return;
        }
        list.add(index);
        find(n, index + 1, k, list, result);
        list.remove(list.size() - 1);
        find(n, index + 1, k, list, result);
    }
}
