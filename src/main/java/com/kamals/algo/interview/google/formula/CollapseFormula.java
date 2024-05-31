package com.kamals.algo.interview.google.formula;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a formula of letters with parentheses, remove all parentheses from the formula.
 * E.g.:
 * a-(b+c) -> a-b-c
 * a-(a-b) -> b
 */
public class CollapseFormula
{
    public static void main(String[] args)
    {
        String expr = "-(a-(b-(c-d)))+ef(a-(b-(c+d)))";
        //--a-+b--c-d   ++a--b-+c+d
        try
        {
            expr = solve(expr);
            System.out.println(expr);
        }
        catch (IllegalArgumentException ie)
        {
            System.out.println(ie);
        }
        catch (Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static final char PLUS = '+';
    private static final char MINUS = '-';

    private static String solve(String expr) throws IllegalArgumentException
    {
        Map<Character, Integer> terms = getTerms(expr);
        return getFormula(terms);
    }

    private static Map<Character, Integer> getTerms(String expr) throws IllegalArgumentException
    {
        Stack<Character> signs = new Stack<>();
        signs.push(PLUS);

        Map<Character, Integer> coefficientMap = new LinkedHashMap<>();

        Character lastSign = PLUS;
        char[] arr = expr.toCharArray();
//        for (char c : arr)
        for (int i = 0; i < arr.length; i++)
        {
            char c = arr[i];
            if (c == PLUS || c == MINUS)
            {
                lastSign = c;
                arr[i] = ' ';
            }
            else if (c == '(')
            {
                char sign = combine(lastSign, signs.peek());
                signs.push(sign);
                lastSign = PLUS;
                arr[i] = ' ';
            }
            else if (c == ')')
            {
                signs.pop();
                lastSign = null;
                arr[i] = ' ';
            }
            else if (c >= 'a' && c <= 'z')
            {
                if (lastSign == null)
                {
                    String msg = "Invalid input: Sign expected, found " + c + " at pos " + i + " Input: " + expr;
                    System.out.println(msg);
                    throw new IllegalArgumentException(msg);
                }
                char sign = combine(lastSign, signs.peek());
                int val = sign == PLUS ? 1 : -1;
                coefficientMap.merge(c, val, Integer::sum);
                lastSign = null;
                if (i > 0)
                {
                    arr[i - 1] = sign;
                }
            }
            else
            {
                //ignore
            }
        }
        System.out.println(arr);
        return coefficientMap;
    }

    private static String getFormula(Map<Character, Integer> coefficientMap)
    {
        StringBuilder res = new StringBuilder();
        coefficientMap.forEach((k, v) ->
        {
            char sign2 = v < 0 ? MINUS : PLUS;
            if (v != 0)
            {
                if (v >= 1 || v == -1)
                {
                    res.append(sign2);
                }
                if (v != 1 && v != -1)
                {
                    res.append(v);
                }
                res.append(k);
            }
        });
        if (res.length() == 0)
        {
            return "0";
        }
        if (res.charAt(0) == PLUS)
        {
            res.deleteCharAt(0);
        }
        return res.toString();
    }

    private static char combine(char sign1, char sign2)
    {
        if (sign1 == PLUS)
        {
            return sign2;
        }
        return sign2 == MINUS ? PLUS : MINUS;
    }

    record Term()
    {
        static String variable;
        static int coeff;
    }
}
