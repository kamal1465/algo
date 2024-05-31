package com.kamals.algo.algos.dynamic;


import com.kamals.algo.algos.util.Util;

import java.util.Arrays;

/**
 * Binomial Coefficient - Choosing K elements from N element set
 * N       N-1      N-1
 * C   =    C   +    C
 * K        K        K-1
 */
public class BinomialCoefficient
{
    public static void main(String[] args)
    {
        int n = 1000;
        int k = n / 2;

        System.out.println(n + " -C- " + k + " = " + binCoeff(n, k));
    }

    private static long binCoeff(int n, int k)
    {
        k = Math.min(k, n - k);

        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++)
        {
            dp[i][0] = 1;
            if (i <= k)
            {
                dp[i][i] = 1;
            }
        }

//        Util.printArr2(dp);

        for (int i = 2; i <= n; i++)
        {
            for (int j = 1; j <= k && j < i; j++)
            {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
            }
        }
//        Util.printArr2(dp);

        return dp[n][k];
    }
}
