package com.kamals.algo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 567. Permutation in String (Medium)
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 * <p>
 * Example 1:
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * <p>
 * Example 2:
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 * <p>
 * Constraints:
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString
{
    public static void main(String[] args)
    {
        String s1 = "ab", s2 = "eidbaooo";
        PermutationInString permutationInString = new PermutationInString();
        System.out.println(permutationInString.checkInclusion(s1, s2));
    }

    //Assuming chars = {a..z}
    public boolean checkInclusion_Arr(String s1, String s2)
    {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l2 < l1)
        {
            return false;
        }
        int[] a1 = new int[26];
        int[] a2 = new int[26];

        for (int i = 0; i < l1; i++)
        {
            a1[s1.charAt(i) - 'a']++;
            a2[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(a1, a2))
        {
            return true;
        }

        int start = 0, end = l1;
        while (end < l2)
        {
            a2[s2.charAt(start++) - 'a']--;
            a2[s2.charAt(end++) - 'a']++;

            if (Arrays.equals(a1, a2))
            {
                return true;
            }
        }
        return false;
    }

    public boolean checkInclusion(String s1, String s2)
    {
        BiFunction<Integer, Integer, Integer> func = (a, b) -> (a + b);
        int l1 = s1.length();
        int l2 = s2.length();
        if (l2 < l1)
        {
            return false;
        }
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        for (int i = 0; i < l1; i++)
        {
            m1.merge(s1.charAt(i), 1, func);
            m2.merge(s2.charAt(i), 1, func);
        }
        if (m1.equals(m2))
        {
            return true;
        }
        int start = 0, end = l1;
        char a, b;
        while (end < l2)
        {
            a = s2.charAt(start++);
            b = s2.charAt(end++);
            m2.merge(a, -1, func);
            m2.merge(b, 1, func);
            if (m2.get(a) == 0)
            {
                m2.remove(a);
            }
            if (m1.equals(m2))
            {
                return true;
            }
        }
        return false;
    }
}
