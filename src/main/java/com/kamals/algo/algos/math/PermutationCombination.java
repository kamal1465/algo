package com.kamals.algo.algos.math;

import java.util.*;

public class PermutationCombination
{
    public static void main(String[] args)
    {
        PermutationCombination permutationCombination = new PermutationCombination();

        String alph = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

        //System.out.println(permutationCombination.permutations(alph));

        int n = 2;
        long t1 = System.nanoTime();
        List<String> res = permutationCombination.combinations(alph, n);
        System.out.println(res);
        long t2 = System.nanoTime();

        t1 = t2 - t1;
        System.out.println("Recursion = " + t1 / 1000000);

        res = permutationCombination.combinationsDup(alph, n);
        System.out.println(res);
        long t3 = System.nanoTime();

        t3 -= t2;
        System.out.println("DP = " + t3 / 1000000);
    }

    public List<String> permutations(String alph)
    {
        List<String> all = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        Map<Character, Integer> am = new HashMap<>();
        for (char c : alph.toCharArray())
        {
            am.merge(c, 1, (a, b) -> a + b);
        }
        permute(am, all, sb);
        return all;
    }

    public void permute(Map<Character, Integer> am, List<String> all, StringBuffer sb)
    {
        if (am.isEmpty())
        {
            all.add(sb.toString());
            return;
        }

        Set<Character> set = new HashSet<>(am.keySet());

        for (Character c : set)
        {
            sb.append(c);
            if (am.get(c) == 1)
            {
                am.remove(c);
            }
            else
            {
                am.put(c, am.get(c) - 1);
            }

            permute(am, all, sb);

            sb.deleteCharAt(sb.length() - 1);
            am.merge(c, 1, (a, b) -> a + b);
        }
    }

    public List<String> combinations(String alph, int n)
    {
        List<String> all = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        combine(alph.toCharArray(), 0, all, sb, n);
        return all;
    }

    public void combine(char[] ca, int pos, List<String> all, StringBuffer sb, int n)
    {
        if (sb.length() == n)
        {
            all.add(sb.toString());
            return;
        }

        if (ca.length - pos < n - sb.length())
        {
            //Not enough characters left
            return;
        }

        if (pos < ca.length)
        {
            Character c = ca[pos];
            sb.append(c);

            combine(ca, pos + 1, all, sb, n);

            sb.deleteCharAt(sb.length() - 1);

            combine(ca, pos + 1, all, sb, n);
        }
    }

    public List<String> combinationsDP(String alph, int k)
    {
        int n = alph.length();
        char[] ca = alph.toCharArray();

        List<String>[][] dp = new List[k + 1][n];

        List<String> emptySet = Collections.emptyList();

        for (int i = 0; i <= k; i++)
        {
            for (int j = 0; j < (n - k + i); j++)
            {
                if (i == 0 || i > j + 1)
                {
                    dp[i][j] = emptySet;
                }
                else if (i == 1 && j == 0)
                {
                    dp[i][j] = Arrays.asList(String.valueOf(ca[0]));
                }
                else
                {
                    dp[i][j] = new ArrayList<>(dp[i][j - 1]);
                    if (dp[i - 1][j - 1].isEmpty())
                    {
                        dp[i][j].add(String.valueOf(ca[j]));
                    }
                    else
                    {
                        for (String s : dp[i - 1][j - 1])
                        {
                            dp[i][j].add(s + ca[j]);
                        }
                    }
                }
            }
        }

        return dp[k][n - 1];
    }


    public List<String> combinationsDup(String alph, int k)
    {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : alph.toCharArray())
        {
            map.merge(c, 1, (a, b) -> a + b);
        }

        int n = map.size();
        char[] ca = new char[n];
        int[] fa = new int[n];
        int[] ta = new int[n];
        int x = 0;
        for (Map.Entry<Character, Integer> e : map.entrySet())
        {
            ca[x] = e.getKey();
            fa[x] = e.getValue();
            ta[x] = fa[x];
            if (x > 0)
            {
                ta[x] += ta[x - 1];
            }
            x++;
        }

        List<String>[][] dp = new List[k + 1][n];

        List<String> emptySet = Collections.emptyList();

        for (int i = 0; i <= k; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == 0 || i > ta[j])
                {
                    dp[i][j] = emptySet;
                }
                else if (j == 0)
                {
                    dp[i][j] = Collections.singletonList(String.valueOf(ca[j]).repeat(i));
                }
                else
                {
                    dp[i][j] = new ArrayList<>(dp[i][j-1]);
                    int z = i - 1;
                    int w = 1;
                    StringBuilder sb = new StringBuilder();
                    while (z >= 0 && w <= fa[j])
                    {
                        sb.append(ca[j]);
                        if (dp[z][j-1].isEmpty())
                        {
                            dp[i][j].add(sb.toString());
                        }
                        else
                        {
                            for (String s : dp[z][j-1])
                            {
                                dp[i][j].add(s + sb.toString());
                            }
                        }
                        z--;
                        w++;
                    }
                }
            }
        }

        return dp[k][n - 1];
    }
}
