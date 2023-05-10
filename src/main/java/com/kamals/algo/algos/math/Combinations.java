package com.kamals.algo.algos.math;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Combinations
{
    public static void main(String[] args)
    {
        Combinations combinations = new Combinations();

        String alph = "aabbcc";

        //System.out.println(permutationCombination.permutations(alph));

        int n = 3;
        long t1 = System.nanoTime();
        List<String> res = combinations.combinations(alph, n);
        System.out.println(res);
        long t2 = System.nanoTime();

        t1 = t2 - t1;
        System.out.println("Recursion = " + t1 / 1000000);
    }

    public List<String> combinations(String alph, int k)
    {
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : alph.toCharArray())
        {
            map.merge(c, 1, (a, b) -> a + b);
        }

        int n = map.size();
        char[] ca = new char[n];
        int[] fa = new int[n];
        int x = 0;
        for (Map.Entry<Character, Integer> e : map.entrySet())
        {
            ca[x] = e.getKey();
            fa[x] = e.getValue();
            x++;
        }

        List<String> all = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        combine(ca, fa, 0, k, sb, all);
        return all;
    }

    private void combine(char[] ca, int[] fa, int pos, int k, StringBuilder sb, List<String> all)
    {
        if (sb.length() == k)
        {
            all.add(sb.toString());
            return;
        }
        if (sb.length() > k)
        {
            //overflow
            return;
        }

        if (pos < ca.length)
        {
            char c = ca[pos];

            for (int i = fa[pos]; i > 0; i--)
            {
                sb.append(String.valueOf(c).repeat(i));
                combine(ca, fa, pos + 1, k, sb, all);
                sb.delete(sb.length() - i, sb.length());
            }

            combine(ca, fa, pos + 1, k, sb, all);
        }
    }
}
