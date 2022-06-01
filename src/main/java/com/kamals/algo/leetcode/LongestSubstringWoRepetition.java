package com.kamals.algo.leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWoRepetition
{
    public static void main(String[] args)
    {
        String s = "pwwkew";
        LongestSubstringWoRepetition longestSubstringWoRepetition = new LongestSubstringWoRepetition();
        int n = longestSubstringWoRepetition.lengthOfLongestSubstring_Set(s);
        System.out.println(n);
    }

    public int lengthOfLongestSubstring(String s)
    {
        char[] a = s.toCharArray();
        int l = a.length;
        int[] M = new int[l + 1];
        M[0] = 0;
        int max = 0;
        for (int i = 0; i < l; i++)
        {
            int j = 0;
            while (j < M[i] && a[i - j - 1] != a[i])
            {
                j++;
            }
            M[i + 1] = j + 1;
            max = Math.max(max, M[i + 1]);
        }
        return max;
    }

    public int lengthOfLongestSubstring_HashMap(String s)
    {
        Map<Character, Integer> map = new HashMap<>();
        char[] a = s.toCharArray();
        int l = a.length;
        int max = 0;
        int start = 0;
        for (int i = 0; i < l; i++)
        {
            Integer p = map.put(a[i], i);
            if (p != null && p >= start)
            {
                max = Math.max(max, i - start);
                start = p + 1;
            }
        }
        max = Math.max(max, l - start);
        return max;
    }

    public int lengthOfLongestSubstring_Set(String s)
    {
        Set<Character> set = new HashSet<>();
        char[] a = s.toCharArray();
        int l = a.length;
        int max = 0;
        int start = 0;
        for (int i = 0; i < l; i++)
        {
            if (!set.add(a[i]))
            {
                max = Math.max(max, i - start);
                while (a[start] != a[i])
                {
                    set.remove(a[start]);
                    start++;
                }
                start++;
            }
        }
        max = Math.max(max, l - start);
        return max;
    }
}
