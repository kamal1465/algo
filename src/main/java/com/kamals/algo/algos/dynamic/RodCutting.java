package com.kamals.algo.algos.dynamic;

import com.kamals.algo.algos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class RodCutting
{
    public static void main(String[] args)
    {
        int[] prices = new int[]{0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        System.out.println(maxValue(prices));
    }

    private static int maxValue(int[] prices)
    {
        int n = prices.length - 1;
        int[] value = new int[n + 1];
        int[] cut = new int[n + 1];
        for (int i = 1; i <= n; i++)
        {
            value[i] = prices[i];
            cut[i] = i;
            int x = i / 2;
            for (int j = 1; j <= x; j++)
            {
                int y = value[j] + value[i - j];
                if (y > value[i])
                {
                    value[i] = y;
                    cut[i] = j;
                }
            }
        }
        Util.printArr2(value);
        List<List<Integer>> lens = new ArrayList<>();
        for (int i = 1; i <= n; i++)
        {
            int x = i;
            List<Integer> list = new ArrayList<>();
            while (cut[x] > 0)
            {
                list.add(cut[x]);
                x = x - cut[x];
            }
            lens.add(list);
        }
        System.out.println(lens);
        return value[n];
    }
}
