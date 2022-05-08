package com.kamals.algo.algos.util;

import java.util.*;

public class MathTest
{
    public static void main(String[] args)
    {
        printCommonSumCubes(100);
    }

    private static void printCommonSumCubes(int N)
    {
        int[] cubes = new int[N+1];
        Map<Integer, List<List<Integer>>> sums = new HashMap<>();
        Set<Integer> commonSums = new HashSet<>();
        for (int i = 1; i < N; i++)
        {
            for (int j = i+1; j <= N; j++)
            {
                if (cubes[i] == 0)
                {
                    cubes[i] = i*i*i;
                }
                if (cubes[j] == 0)
                {
                    cubes[j] = j*j*j;
                }
                int sum = cubes[i] + cubes[j];
                if (sums.get(sum) == null)
                {
                    sums.put(sum, new ArrayList<>());
                }
                else
                {
                    commonSums.add(sum);
                }
                sums.get(sum).add(Arrays.asList(i,j));
            }
        }
        for (Integer x : commonSums)
        {
            for (List<Integer> pair : sums.get(x))
            {
                System.out.print(pair + " = ");
            }
            System.out.println(x);
        }
        System.out.print(sums);
    }
}
