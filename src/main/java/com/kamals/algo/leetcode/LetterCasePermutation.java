package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 784. Letter Case Permutation
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create. Return the output in any order.
 * <p>
 * Example 1:
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * <p>
 * Example 2:
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 */
public class LetterCasePermutation
{
    public static void main(String[] args)
    {
        String s = "ab";
        System.out.println(letterCasePermutation_Backtracking(s));
    }

    public static List<String> letterCasePermutation(String s)
    {
        List<String> l1 = new ArrayList<>();
        List<String> l2 = new ArrayList<>();
        l1.add(s);
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (Character.isAlphabetic(c))
            {
                char y = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
                for (String x : l1)
                {
                    char[] a = x.toCharArray();
                    a[i] = y;
                    l2.add(String.valueOf(a));
                }
                l1.addAll(l2);
                l2.clear();
            }
        }
        return l1;
    }

    public static List<String> letterCasePermutation_Backtracking(String s)
    {
        char[] a = s.toCharArray();
        List<String> list = new ArrayList<>();
        letterCasePermutation_Backtracking(a, 0, list);
        return list;
    }

    public static void letterCasePermutation_Backtracking(char[] s, int i, List<String> list)
    {
        System.out.println(i + " " + Arrays.toString(s));
        if (i == s.length)
        {
            list.add(String.valueOf(s));
            return;
        }
        char c = s[i];
        if (Character.isAlphabetic(c))
        {
            char y = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            letterCasePermutation_Backtracking(s, i + 1, list);
            s[i] = y;
            letterCasePermutation_Backtracking(s, i + 1, list);
        }
        else
        {
            letterCasePermutation_Backtracking(s, i + 1, list);
        }
    }
}
