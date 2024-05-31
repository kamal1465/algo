package com.kamals.algo.algos.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation
{
    public static void main(String[] args)
    {
        String s = "abba";
        List<String> all = new ArrayList<>();
        char[] a = s.toCharArray();
        Arrays.sort(a);

        permute(a, 0, new StringBuilder(), new boolean[a.length], all);
    }

    private static void permute(char[] s, int i, StringBuilder sb, boolean[] bitVector, List<String> all)
    {
        if (i == s.length)
        {
            System.out.println("Perm: " + sb);
            all.add(sb.toString());
            return;
        }
        char c = ' ';
        for (int k = 0; k < s.length; k++)
        {
            if (!bitVector[k] && c != s[k])
            {
                sb.append(s[k]);
                bitVector[k] = true;
                permute(s, i + 1, sb, bitVector, all);
                sb.deleteCharAt(sb.length() - 1);
                bitVector[k] = false;
                c = s[k];
            }
        }
    }
}
