package com.kamals.algo.algos.dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * You are given an integer array coins representing coins of different denominations and
 * an integer amount representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 */
public class CoinChange
{
    // BFS, since need minimum number of coins in change
    public int coinChange(int[] coins, int amount)
    {
        if (amount == 0)
        {
            return 0;
        }
        Arrays.sort(coins);
        int n = coins.length;
        for (int i = 0, j = n - 1; i < j; i++, j--)
        {
            int t = coins[i];
            coins[i] = coins[j];
            coins[j] = t;
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> all = new HashSet<>();
        int level = 0;
        set1.add(amount);
        all.add(amount);
        while (!set1.isEmpty())
        {
            level++;
            Set<Integer> set2 = new HashSet<>();
            for (int am : set1)
            {
                for (int coin : coins)
                {
                    int x = am - coin;
                    if (x == 0)
                    {
                        return level;
                    }
                    else if (x > 0 && !all.contains(x))
                    {
                        set2.add(x);
                    }
                }
            }
            all.addAll(set2);
            set1 = set2;
        }
        return -1;
    }

}
