package com.kamals.algo.algos.dynamic;

import com.kamals.algo.algos.util.Util;

import java.util.Arrays;

public class MaxEventsAttended
{
    public static int maxValue(int[][] events, int k)
    {
        int n = events.length;

        //Sort by finish times
        Arrays.sort(events, (a, b) -> a[1] - b[1]);

        //last compatible event - lce()
        int[] lce = new int[n + 1];
        for (int i = 1; i < n; i++)
        {
            int x = 0, y = i - 1;
            while (x <= y)
            {
                int m = (x + y) / 2;
                if (events[m][1] < events[i][0])
                {
                    lce[i + 1] = m + 1;
                    x = m + 1;
                }
                else
                {
                    y = m - 1;
                }
            }
        }
        Util.printArr2(lce);

        //DP array 2-D
        int[][] soln = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                soln[i][j] = Math.max(soln[i][j - 1], events[j - 1][2] + soln[i - 1][lce[j]]);
            }
        }
        Util.printArr2(soln);

        return soln[k][n];
    }

    public static void main(String[] args)
    {
        int[][] e = new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 10}};
        int k = 2;
        maxValue(e, k);
    }
}
