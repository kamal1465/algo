package com.kamals.algo.leetcode;

public class RegexMatch
{
    public static void main(String[] args)
    {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String s, String p)
    {
        return isMatch(s, p, 0, 0);
    }

    private static boolean isMatch(String s, String p, int i, int j)
    {
        int m = s.length();
        int n = p.length();

        if (n == j)
        {
            return m == i;
        }

        char p1 = p.charAt(j);

        if (n > j + 1 && p.charAt(j + 1) == '*')
        {
            if (isMatch(s, p, i, j + 2))
            {
                return true;
            }
            if (i < m && (s.charAt(i) == p1 || p1 == '.'))
            {
                return isMatch(s, p, i + 1, j);
            }
        }
        else if (i < m && s.charAt(i) == p1 || p1 == '.')
        {
            return isMatch(s, p, i + 1, j + 1);
        }
        return false;
    }
}
