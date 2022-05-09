package com.kamals.algo.algos.interview;

import java.util.ArrayList;
import java.util.List;

//Wrong.. candidate code
public class MergeSortTest
{

    private static void sort(int l, int r, int[] v)
    {
        if (l >= r)
            return;

        int m = (l + r) / 2;
        sort(l, m, v);
        sort(m + 1, r, v);

        int i = l, j = m + 1;
        List<Integer> temp = new ArrayList<>();
        while (i <= m || j <= r)
        {
            if (j > r || (i <= m && v[i] < v[j]))
            {
                System.out.println("Adding i=" + i);
                temp.add(v[i]);
                i++;
            }
            else //if (i > m || v[j] <= v[i])
            {
                System.out.println("Adding j=" + j);
                temp.add(v[j]);
                j++;
            }
        }
        System.out.println(temp);

        int x = 0;
        for (int k = l; k <= r; k++)
        {
            //System.out.println(x);
            v[k] = temp.get(x++);
        }

    }

    public static void main(String[] args)
    {
        int[] v = {4,3,2,1,7};
        sort(0, 4, v);

        int k = 1;
        System.out.print(v[v.length - k]);
    }
}