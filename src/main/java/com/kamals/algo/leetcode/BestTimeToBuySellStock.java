package com.kamals.algo.leetcode;

public class BestTimeToBuySellStock
{
    public static void main(String[] args)
    {

    }

    //One buy and sell transaction
    public int maxProfit(int[] p)
    {
        int profit = 0;
        int min = p[0];
        for (int i = 1; i < p.length; i++)
        {
            profit = Math.max(p[i] - min, profit);
            min = Math.min(p[i], min);
        }
        return profit;
    }

    //Any number of transactions
    public int maxProfit2(int[] p)
    {
        int profit = 0;
        for (int i = 1; i < p.length; i++)
        {
            if (p[i] > p[i - 1])
            {
                profit += (p[i] - p[i - 1]);
            }
        }
        return profit;
    }

    //At most two transactions
    public int maxProfit3(int[] p)
    {
        int n = p.length;
        int min = p[0];
        int[] leftProfit = new int[n];
        for (int i = 1; i < n; i++)
        {
            leftProfit[i] = Math.max(leftProfit[i - 1], p[i] - min);
            min = Math.min(min, p[i]);
        }
        int profit = leftProfit[n - 1];
        int max = p[n - 1];
        for (int i = n - 2; i >= 0; i--)
        {
            profit = Math.max(profit, max - p[i] + leftProfit[i - 1]);
            max = Math.max(max, p[i]);
        }
        return profit;
    }

    //At most k transactions
    public int maxProfit4(int[] p, int k)
    {
        int n = p.length;
        if (k >= n / 2)
        {
            return maxProfit2(p);
        }

        int[][] profit = new int[k + 1][n + 1];
        //dp[t][i] == Max Profit for At most t txns for prices subset p[0,1,..i]
        //dp[t][i] = Max(dp[t][i-1], Max(dp[t-1][j] + Max(prices[i] - prices[k]))) , where 0 < j < i, and j < k < i
        //dp[t][i] = Max(dp[t][i-1], prices[i] + Max(dp[t-1][j] - prices[k]))

        for (int t = 1; t <= k; t++)
        {
            for (int i = 1; i <= n; i++)
            {
                for (int j = 0; j < i; j++)
                {
                    for (int x = j + 1; x < i; x++)
                    {

                    }
                }
                profit[t][i] = Math.max(profit[t][i - 1], p[i]);
            }
        }

        return profit[k][n];
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
     * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
     * Input: prices = [1,2,3,0,2]
     * Output: 3
     * Explanation: transactions = [buy, sell, cooldown, buy, sell]
     *
     * @param prices
     * @return
     */
    public int maxProfitWithCooldown1Day(int[] prices)
    {
        int n = prices.length;

        int[] s = new int[n];

        for (int j = 1; j < n; j++)
        {
            s[j] = s[j - 1];
            for (int i = 0; i < j; i++)
            {
                s[j] = Math.max(s[j], prices[j] - prices[i] + i > 1 ? s[i - 2] : 0);
            }
        }

        return s[n - 1];
    }
}
