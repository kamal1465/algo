package com.kamals.algo.interview.amazon;

import java.util.*;

/**
 * Given a Dictionary, that returns Dictionary.containsWord(String word) = True/False
 * And a string containing any number of word
 */
public class BreakStringIntoWords
{
    private static final Set<String> WORDS = Set.of("this", "is", "a", "chair", "isa", "abcd", "efgh");

    public static void main(String[] args)
    {
        String s = "thischair";
        int n = s.length();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        boolean[] bitVector = new boolean[n];
        List<String> all = new ArrayList<>();
        solve(arr, 0, bitVector, new StringBuilder(), all);
    }

    private static void solve(char[] s, int i, boolean[] bitVector, StringBuilder sb, List<String> all)
    {
        if (i == s.length)
        {
            if (isWord(getLastWord(sb)))
            {
                all.add(sb.toString());
                System.out.println("Found solution: " + sb + "|");
            }
            return;
        }
        if (sb.length() > 0 && isWord(getLastWord(sb)))
        {
            sb.append(' ');
            extracted(s, i, bitVector, sb, all);
            sb.deleteCharAt(sb.length() - 1);
        }
        extracted(s, i, bitVector, sb, all);
    }

    private static void extracted(char[] s, int i, boolean[] bitVector, StringBuilder sb, List<String> all)
    {
        char c = ' ';
        for (int k = 0; k < s.length; k++)
        {
            if (!bitVector[k] && c != s[k])
            {
                c = s[k];
                sb.append(c);
                bitVector[k] = true;
                solve(s, i + 1, bitVector, sb, all);
                sb.deleteCharAt(sb.length() - 1);
                bitVector[k] = false;
            }
        }
    }

    private static String getLastWord(StringBuilder sb)
    {
        int i = sb.length() - 1;
        while (i >= 0 && sb.charAt(i) != ' ')
        {
            i--;
        }
        return sb.substring(i + 1);
    }

    private static boolean isWord(String word)
    {
        return WORDS.contains(word);
    }
}
