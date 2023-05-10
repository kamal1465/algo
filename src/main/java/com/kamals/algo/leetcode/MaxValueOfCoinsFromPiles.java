package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MaxValueOfCoinsFromPiles
{
    public static int maxValueOfCoins(List<List<Integer>> piles, int k)
    {
        int n = piles.size();

        for (List<Integer> p : piles)
        {
            for (int i = 1; i < p.size() && i < k; i++)
            {
                p.set(i, p.get(i) + p.get(i - 1));
            }
        }

        int[][] s = new int[k + 1][n];

        for (int j = 0; j < n; j++)
        {
            for (int i = 0; i <= k; i++)
            {
                for (int x = 0; x <= i && x <= piles.get(j).size(); x++)
                {
                    s[i][j] = Math.max(s[i][j], (j > 0 ? s[i - x][j - 1] : 0) + (x > 0 ? piles.get(j).get(x - 1) : 0));
                }
            }
            Util.printArr2(s);
            System.out.println();
        }

        return s[k][n - 1];
    }

    public static void main(String[] args)
    {
        int[][] s = new int[][]{{100}, {100}, {100}, {100}, {100}, {100}, {1, 1, 1, 1, 1, 1, 700}};
        List<List<Integer>> piles = new ArrayList<>();
        for (int[] x : s)
        {
            List<Integer> p = new ArrayList<>();
            piles.add(p);
            for (int y : x)
            {
                p.add(y);
            }
        }
        int k = 8;
        System.out.println(maxValueOfCoins(piles, k));
    }
}
