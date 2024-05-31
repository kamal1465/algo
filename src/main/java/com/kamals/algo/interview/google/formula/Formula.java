package com.kamals.algo.interview.google.formula;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class Formula
{
    public static void main(String[] args)
    {
        String s = "aa-(ab+2aa-a(ba-ca))";
        try
        {
            s = simplifyFormula(s);
            System.out.println(s);
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

    public static String simplifyFormula(String formula)
    {
        if (formula == null || StringUtils.isBlank(formula))
        {
            return formula;
        }
        formula = formula.trim();
        char[] arr = formula.toCharArray();
        Map<String, Integer> terms = getTerms(arr, 0, arr.length - 1);
        System.out.println(terms);
        return getFormula(terms);
    }

    private static Map<String, Integer> getTerms(char[] formula, int i, int j)
    {
        Stack<OP> signs = new Stack<>();
        signs.push(OP.PLUS);
        OP lastSign = OP.PLUS;
        Map<String, Integer> termMap = new LinkedHashMap<>();
        StringBuilder term = new StringBuilder();
        for (; i <= j; i++)
        {
            char c = formula[i];
            if (Character.isSpaceChar(c))
            {
                if (term.length() > 0)
                {
                    term.append(c);
                }
            }
            else if (Character.isLetterOrDigit(c))
            {
                term.append(c);
                if (lastSign == null)
                {
                    System.out.println("Error: sign expected: found " + c);
                }
            }
            else
            {
                if (term.length() > 0)
                {
                    collectTerm(term, termMap, combineSign(lastSign, signs.peek()));
                    term.setLength(0);
                    lastSign = null;
                }
                if (c == '(')
                {
                    if (lastSign == null)
                    {
                        System.out.println("Error: sign expected: found (");
                    }
                    signs.push(combineSign(lastSign, signs.peek()));
                    lastSign = OP.PLUS;
                }
                else if (c == ')')
                {
                    signs.pop();
                    lastSign = null;
                    if (signs.size() == 0)
                    {
                        System.out.println("Error: closing parenthesis without matching opening parenthesis");
                    }
                }
                else
                {
                    OP op = OP.getOp(c);
                    if (op == null)
                    {
                        System.out.println("Error: unknown character: " + c);
                        return null;
                    }
                    lastSign = op;
                }
            }
        }
        return termMap;
    }

    private static OP combineSign(OP a, OP b)
    {
        if (a == OP.PLUS)
        {
            return b;
        }
        return b == OP.PLUS ? OP.MINUS : OP.PLUS;
    }

    private static void collectTerm(StringBuilder term, Map<String, Integer> termMap, OP sign)
    {
        int coeff = 1;
        for (int i = 0; i < term.length(); i++)
        {
            if (Character.isDigit(term.charAt(i)))
            {
                int j = i + 1;
                while (j < term.length() && Character.isDigit(term.charAt(j)))
                {
                    j++;
                }
                coeff *= Integer.parseInt(term.substring(i, j));
                term.delete(i, j);
            }
        }
        char[] charArray = new char[term.length()];
        term.getChars(0, term.length(), charArray, 0);
        Arrays.sort(charArray);

        termMap.merge(new String(charArray), sign == OP.MINUS ? -coeff : coeff, Integer::sum);
    }

    private static String getFormula(Map<String, Integer> terms)
    {
        StringBuilder sb = new StringBuilder();
        terms.forEach((term, coeff) ->
        {
            if (coeff != 0)
            {
                if (coeff > 0 && sb.length() > 0)
                {
                    sb.append(OP.PLUS);
                }
                if (coeff == -1)
                {
                    sb.append(OP.MINUS);
                }
                if (coeff != -1 && coeff != 1)
                {
                    sb.append(coeff);
                }
                sb.append(term);
            }
        });
        return sb.length() > 0 ? sb.toString() : "0";
    }


    private enum OP
    {
        PLUS('+'), MINUS('-'), MULTIPLY('*'), DIVIDE('/'), POWER('^');

        private final char sign;

        OP(char sign)
        {
            this.sign = sign;
        }

        static OP getOp(char c)
        {
            for (OP op : values())
            {
                if (op.sign == c)
                {
                    return op;
                }
            }
            return null;
        }


        @Override
        public String toString()
        {
            return String.valueOf(sign);
        }
    }

}
