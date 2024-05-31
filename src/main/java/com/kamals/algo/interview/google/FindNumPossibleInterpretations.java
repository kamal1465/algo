package com.kamals.algo.interview.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given Mapping 1->a, 2->b...26->z
 * Input :- 123
 * Interpretations = {abc, aw, lc}
 * Output: 3 (number of possible interpretations)
 * DP = optimal algorithm with O(N) time and space each.
 * Handle the case of 0
 */
public class FindNumPossibleInterpretations
{
    public static void main(String[] args)
    {
        Interpreter interpreter = new Interpreter();
        long num = 123;
        System.out.println(interpreter.numInterpretations(num));
        System.out.println(interpreter.numInterpretationsV2(num));
    }

    static class Interpreter
    {
        public static int numInterpretations(long num)
        {
            String s = Long.toString(num);
            char[] ca = s.toCharArray();
            int n = ca.length;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 0; i < n; i++)
            {
                if (dp[i] > 0)
                {
                    if (ca[i] >= '1' && ca[i] <= '9')
                    {
                        dp[i + 1] += dp[i];
                    }
                    if (i < n - 1 && ((ca[i] == '1' && ca[i + 1] >= '0' && ca[i + 1] <= '9') ||
                            (ca[i] == '2' && ca[i + 1] >= '0' && ca[i + 1] <= '6')))
                    {
                        dp[i + 2] += dp[i];
                    }
                }
                else if (dp[i - 1] == 0)
                {
                    break;
                }
            }
            return dp[n];
        }

        public static int numInterpretationsV2(long num)
        {
            String s = Long.toString(num);
            char[] ca = s.toCharArray();
            int n = ca.length;
            int[] dp = new int[n + 1];
            dp[0] = 1;
            List<List<StringBuilder>> res = new ArrayList<>();
            for (int i = 0; i <= n; i++)
            {
                res.add(new ArrayList<>());
            }
            res.get(0).add(new StringBuilder());
            for (int i = 0; i < n; i++)
            {
                if (dp[i] > 0)
                {
                    List<Character> cs = getCharRep(ca, i);
                    for (char x : cs)
                    {
                        if (x <= 'i')
                        {
                            dp[i + 1] += dp[i];
                        }
                        else
                        {
                            dp[i + 2] += dp[i];
                        }
                    }

                    for (StringBuilder sb : res.get(i))
                    {
                        StringBuilder sb2 = null;
                        if (cs.size() > 1)
                        {
                            sb2 = new StringBuilder(sb);
                        }
                        for (char x : cs)
                        {
                            if (sb == null)
                            {
                                sb = sb2;
                            }
                            sb.append(x);
                            if (x <= 'i')
                            {
                                res.get(i + 1).add(sb);
                            }
                            else
                            {
                                res.get(i + 2).add(sb);
                            }
                            sb = null;
                        }
                    }
                }
                else if (dp[i - 1] == 0)
                {
                    break;
                }
            }
            System.out.println(res.get(n));
            return dp[n];
        }

        private static List<Character> getCharRep(char[] ca, int i)
        {
            List<Character> res = new ArrayList<>();
            if (i < ca.length)
            {
                int x = ca[i] - '0';
                if (x > 0)
                {
                    res.add((char) ('a' + x - 1));
                    if (i < ca.length - 1)
                    {
                        int y = ca[i + 1] - '0';
                        x = x * 10 + y;
                        if (x <= 26)
                        {
                            res.add((char) ('a' + x - 1));
                        }
                    }
                }
            }
            return res;
        }
    }

}
