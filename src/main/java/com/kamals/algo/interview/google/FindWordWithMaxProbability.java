package com.kamals.algo.interview.google;

import java.util.Arrays;

/**
 * Google Interview - 2nd Round - Coding - 23-02-2024
 * Given Probability of a letter 'b' occurring after another letter 'a' as P['a', 'b'] in the English Text
 * Find the maximum probability of any word of given length m
 * - Assume '^' and '$' denote start and end of word respectively
 * - All characters are lower case - 'a' - 'z'
 * - Words are not dictionary words but random strings with given probability
 * <p>
 * Soln: Backtracking with Search Space Pruning
 */
public class FindWordWithMaxProbability
{
    public static void main(String[] args)
    {
        int m = 10;
        System.out.println(find(m));
    }

    /**
     * Correct Solution DP: Max-P(i, c) = MAX (Max-P(i - 1, x) * P(x, c)) where x => [a, z]
     * @param m
     * @return
     */
    static double findDp(int m)
    {
        double[] p = new double[26];
        double[] q = new double[26];

        for (char c = 'a'; c <= 'z'; c++)
        {
            p[c - 'a'] = P('^', c);
        }

        for (int i = 1; i < m; i++)
        {
            for (char c2 = 'a'; c2 <= 'z'; c2++)
            {
                for (char c1 = 'a'; c1 <= 'z'; c1++)
                {
                    q[c2 - 'a'] = Math.max(q[c2 - 'a'], p[c1 - 'a'] * P(c1, c2));
                }
            }
            double[] t = p;
            p = q;
            q = t;
            Arrays.fill(q, 0);
        }

        double max = 0;
        for (char c = 'a'; c <= 'z'; c++)
        {
            max = Math.max(max, p[c - 'a'] * P(c, '$'));
        }
        return max;
    }

    static double find(int m)
    {
        double[][] cache = new double[m + 1][26];
        double[] max = new double[1];
        search(m, new StringBuilder(), 1.0, cache, max);
        return max[0];
    }

    static void search(int m, StringBuilder sb, double p, double[][] cache, double[] max)
    {
        int k = sb.length();
        if (k == m)
        {
            char c = sb.charAt(sb.length() - 1);
            double q = P(c, '$');
            p = p * q;
            if (p > max[0])
            {
                max[0] = p;
            }
            return;
        }
        if (k == 0)
        {
            for (char a = 'a'; a <= 'z'; a++)
            {
                double q = P('^', a);
                if (q > 0)
                {
                    sb.append(a);
                    cache[k + 1][a - 'a'] = Math.max(cache[k + 1][a - 'a'], q);
                    search(m, sb, q, cache, max);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        else
        {
            char c = sb.charAt(sb.length() - 1);
            if (p <= cache[k][c - 'a'])
            {
                return;
            }
            for (char a = 'a'; a <= 'z'; a++)
            {
                double q = P(c, a) * p;
                if (q > 0)
                {
                    sb.append(a);
                    cache[k + 1][a - 'a'] = Math.max(cache[k + 1][a - 'a'], q);
                    search(m, sb, q, cache, max);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    static double P(char a, char b)
    {
        return 1.0;
    }
}
