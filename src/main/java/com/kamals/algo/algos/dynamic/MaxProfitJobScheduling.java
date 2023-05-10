package com.kamals.algo.algos.dynamic;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxProfitJobScheduling
{
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit)
    {
        int n = startTime.length;
        int[][] jobs = new int[n + 1][3];

        for (int i = 0; i < n; i++)
        {
            jobs[i + 1][0] = startTime[i];
            jobs[i + 1][1] = endTime[i];
            jobs[i + 1][2] = profit[i];
        }

        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        int[] lcj = new int[n + 1];
        for (int i = 2; i <= n; i++)
        {
            int min = 1, max = i - 1;
            while (min <= max)
            {
                int x = (min + max) / 2;
                if (jobs[x][1] <= jobs[i][0])
                {
                    lcj[i] = x;
                    min = x + 1;
                }
                else
                {
                    max = x - 1;
                }
            }
        }

        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++)
        {
            s[i] = Math.max(s[i - 1], jobs[i][2] + s[lcj[i]]);
        }
        var i = 10;

        return s[n];
    }

    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit)
    {
        int n = startTime.length;
        int[][] jobs = new int[n + 1][3];

        for (int i = 0; i < n; i++)
        {
            jobs[i + 1][0] = startTime[i];
            jobs[i + 1][1] = endTime[i];
            jobs[i + 1][2] = profit[i];
        }

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int maxProfit = 0;
        for (int[] j : jobs)
        {
            int[] x = q.peek();
            while (x != null && x[1] <= j[0])
            {
                q.poll();
                maxProfit = Math.max(maxProfit, x[2]);
            }
            j[2] += maxProfit;
            q.offer(j);
        }

        while (q.peek() != null)
        {
            maxProfit = Math.max(maxProfit, q.poll()[2]);
        }
        return maxProfit;
    }

}
