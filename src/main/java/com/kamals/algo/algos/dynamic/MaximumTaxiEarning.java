package com.kamals.algo.algos.dynamic;

import java.util.Arrays;

public class MaximumTaxiEarning
{
    public static long maxTaxiEarnings(int n, int[][] rides)
    {
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);

        int m = rides.length;

        int[] lcr = new int[m];
        Arrays.fill(lcr, -1);
        for (int i = 1; i < m; i++)
        {
            int min = 0, max = i - 1;

            while (min <= max)
            {
                int x = (min + max) / 2;
                if (rides[x][1] <= rides[i][0])
                {
                    lcr[i] = x;
                    min = x + 1;
                }
                else
                {
                    max = x - 1;
                }
            }
        }

        long[] s = new long[m];
        s[0] = rides[0][1] - rides[0][0] + rides[0][2];
        for (int i = 1; i < m; i++)
        {
            long v = 0;
            if (rides[i][0] >= 1 && rides[i][1] <= n)
            {
                v += rides[i][1] - rides[i][0] + rides[i][2];
                if (lcr[i] > -1)
                {
                    v += s[lcr[i]];
                }
            }
            s[i] = Math.max(s[i - 1], v);
        }

        return s[m - 1];
    }

    public static void main(String[] args)
    {

    }
}
