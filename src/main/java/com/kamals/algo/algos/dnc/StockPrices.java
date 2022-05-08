package com.kamals.algo.algos.dnc;

import com.kamals.algo.algos.util.Util;

import java.util.Random;

public class StockPrices
{

    public static void main(String[] args)
    {
        int N = 1024*1024;

        int[] P = new int[N];

        Random rnd = new Random();

        for (int i = 0; i < N; i++)
        {
            P[i] = rnd.nextInt(N);
        }

        Util.printArr(P);

        long t1 = System.nanoTime();
        Trade t = getTrade(P, 0, N - 1);
        long t2 = System.nanoTime();
        if (t.profit > 0)
        {
            System.out.println("Buy at " + t.buy + " for " + P[t.buy] + " & Sell at " + t.sell + " for " +
                    P[t.sell]);
            System.out.println("Profit = " + t.profit);
        }
        else
        {
            System.out.println("No profitable trade exists");
        }
        System.out.println("Times taken = " + (t2 - t1) / 1000000);
        t1 = System.nanoTime();
        t = getTrade(P);
        t2 = System.nanoTime();
        if (t.profit > 0)
        {
            System.out.println("Buy at " + t.buy + " for " + P[t.buy] + " & Sell at " + t.sell + " for " +
                    P[t.sell]);
            System.out.println("Profit = " + t.profit);
        }
        else
        {
            System.out.println("No profitable trade exists");
        }

        System.out.println("Times taken = " + (t2 - t1) / 1000000);
    }

    private static Trade getTrade(int[] P, int i, int j)
    {
        if (i == j)
        {
            return new Trade(-1, -1, i, i, 0);
        }

        int k = (i + j) / 2;

        Trade a = getTrade(P, i, k);
        Trade b = getTrade(P, k + 1, j);

        int profit = P[b.max] - P[a.min]; //Math.max(0, P[b.max] - P[a.min]);

        Trade c = a.profit >= b.profit ? a : b;
        //        int buy = c.buy;
        //        int sell = c.sell;
        if (profit > c.profit)
        {
            c.buy = a.min;
            c.sell = b.max;
            c.profit = profit;
        }
        c.min = P[a.min] <= P[b.min] ? a.min : b.min;
        c.max = P[a.max] >= P[b.max] ? a.max : b.max;
        return c; //new Trade(buy, sell, min, max, profit);
    }

    private static Trade getTrade(int[] P)
    {
        int N = P.length;

        int buy = -1, sell = -1, profit = 0;

        for (int i = N - 2; i >= 0; i--)
        {
            for (int j = i + 1; j < N; j++)
            {
                if (P[j] - P[i] > profit)
                {
                    profit = P[j] - P[i];
                    buy = i;
                    sell = j;
                }
            }
        }
        return new Trade(buy, sell, 0, 0, profit);
    }

    private static class Trade
    {
        private int buy = -1;
        private int sell = -1;
        private int min = -1;
        private int max = -1;
        private int profit = 0;

        Trade(int buy, int sell, int min, int max, int profit)
        {
            this.buy = buy;
            this.sell = sell;
            this.min = min;
            this.max = max;
            this.profit = profit;
        }
    }
}
