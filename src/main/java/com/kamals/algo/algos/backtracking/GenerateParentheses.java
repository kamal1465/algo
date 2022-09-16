package com.kamals.algo.algos.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * Example 1: Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * Example 2: Input: n = 1
 * Output: ["()"]
 */
public class GenerateParentheses
{
    public static void main(String[] args)
    {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        List<String> list = generateParentheses.generateParenthesis(4);
        System.out.println(list);
    }

    private List<String> generateParenthesis(int n)
    {
        List<String> list = new ArrayList<>();
        if (n == 0)
        {
            return list;
        }
        StringBuilder sb = new StringBuilder();
        int[] x = new int[]{n, n};
        backtrack(x, sb, list);
        return list;
    }

    private void backtrack(int[] x, StringBuilder sb, List<String> list)
    {
        if (x[0] == 0 && x[1] == 0)
        {
            list.add(sb.toString());
            return;
        }
        if (x[0] > 0)
        {
            x[0]--;
            sb.append('(');
            backtrack(x, sb, list);
            x[0]++;
            sb.deleteCharAt(sb.length() - 1);
        }
        if (x[1] > x[0])
        {
            x[1]--;
            sb.append(')');
            backtrack(x, sb, list);
            x[1]++;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
