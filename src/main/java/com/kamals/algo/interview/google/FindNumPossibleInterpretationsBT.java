package com.kamals.algo.interview.google;

import com.kamals.algo.algos.util.Util;

import java.util.*;

/**
 * Given Mapping 1->a, 2->b...26->z
 * Input :- 123
 * Interpretations = {abc, aw, lc}
 * Output: 3 (number of possible interpretations)
 * DP = optimal algorithm with O(N) time and space each.
 * Handle the case of 0
 */
public class FindNumPossibleInterpretationsBT
{
    public static void main(String[] args)
    {
        FindNumPossibleInterpretationsBT interpreter = new FindNumPossibleInterpretationsBT();
        long in = 123456789123l;
        System.out.println(interpreter.numInterpretations(in));
    }
    public List<String> numInterpretations(long num)
    {
        if (num <= 0) return Collections.emptyList();
        int[] digits = getDigits(num);
        StringBuilder sb = new StringBuilder();
        List<String> res = new ArrayList<>();
        backtrack(digits, 0, sb, res, new HashMap<>());
        return res;
    }

    private void backtrack(int[] digits, int i, StringBuilder sb, List<String> res, Map<Integer, String> memtable)
    {
        int n = digits.length;
        if (i == n)
        {
            res.add(sb.toString());
            return;
        }
        Character c = getChar(digits[i]);
        if (c != null)
        {
            sb.append(c);
            backtrack(digits, i + 1, sb, res, memtable);
            sb.deleteCharAt(sb.length() - 1);
            if (i < n - 1)
            {
                c = getChar(digits[i] * 10 + digits[i + 1]);
                if (c != null)
                {
                    sb.append(c);
                    backtrack(digits, i + 2, sb, res, memtable);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    private int[] getDigits(long l)
    {
        List<Integer> list = new ArrayList<>();
        while (l > 0)
        {
            list.add((int) (l % 10));
            l /= 10;
        }
        int[] digits = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            digits[i] = list.get(list.size() - i - 1);
        }
        return digits;
    }

    private Character getChar(int i)
    {
        if (i >= 1 && i <= 26)
        {
            return (char) ('a' + i - 1);
        }
        return null;
    }
}
