package com.kamals.algo.algos.dynamic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LongestCommonSubsequence
{
    public static void main(String[] args)
    {
        Random random = new Random();
        List<Integer> list1 = random.ints(10, 0, 10).boxed().collect(Collectors.toList());
        List<Integer> list2 = random.ints(10, 0, 10).boxed().collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list2);
    }
}
