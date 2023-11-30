package com.kamals.algo.algos.stringmatch;

import com.kamals.algo.algos.util.Util;

public class KMP
{
    public static void main(String[] args)
    {
        String p = "abcababa";
        Util.printArr2(prefixFunction(p));
    }

    private static int[] prefixFunction(String p)
    {
        int n = p.length();
        int[] pie = new int[n];

        for (int q = 1; q < n; q++)
        {
            if (p.charAt(pie[q - 1]) == p.charAt(q))
            {
                pie[q] = pie[q - 1] + 1;
            }
            else if (p.charAt(q) == p.charAt(0))
            {
                pie[q] = 1;
            }
        }

        return pie;
    }
}
