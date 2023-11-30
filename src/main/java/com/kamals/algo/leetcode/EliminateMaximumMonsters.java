package com.kamals.algo.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class EliminateMaximumMonsters
{

    public static void main(String[] args)
    {
        int[] dist = new int[]{5, 4, 3, 3, 3};
        int[] speed = new int[]{1, 1, 5, 3, 1};

        int i = eliminateMaximumPQ(dist, speed);

        System.out.println(i);
    }

    public static int eliminateMaximumCountingSort(int[] dist, int[] speed)
    {
        int n = dist.length;
        int[] countingSortArr = new int[n];

        for (int i = 0; i < n; i++)
        {
            int x = (int) Math.ceil((double) dist[i] / speed[i]);
            if (x < n)
            {
                countingSortArr[x]++;
            }
        }

        int count = 0;
        int weapon = 0;
        for (int i = 0; i < n; i++)
        {
            if (countingSortArr[i] > weapon)
            {
                count += weapon;
                return count;
            }
            else if (countingSortArr[i] > 0)
            {
                count += countingSortArr[i];
                weapon -= countingSortArr[i];
            }
            weapon++;
        }

        return n;
    }

    public static int eliminateMaximumArrSort(int[] dist, int[] speed)
    {
        int n = dist.length;

        float[] arrivalTimes = new float[n];

        //calculate arrival times
        for (int i = 0; i < n; i++)
        {
            arrivalTimes[i] = (float) dist[i] / speed[i];
        }

        //Sort arrival times
        Arrays.sort(arrivalTimes);

        int count = 0;
        for (int i = 0; i < n; i++)
        {
            if ((float) i < arrivalTimes[i])
            {
                count++;
            }
        }

        return count;
    }


    public static int eliminateMaximumPQ(int[] dist, int[] speed)
    {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < dist.length; i++)
        {
            int x = dist[i] / speed[i];
            if (dist[i] % speed[i] != 0)
            {
                x++;
            }
            pq.offer(x);
        }

        int count = 0;

        for (int i = 0; i < dist.length; i++)
        {
            int x = pq.poll();
            if (i < x)
            {
                count++;
            }
        }

        return count;
    }
}
