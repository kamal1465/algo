package com.kamals.algo.algos.dynamic;

public class LongestPalindromeSubstring
{

    public static void main(String[] args)
    {
        String s = "tattarrattat";
        System.out.println(s);
        System.out.println(longestPalindrome(s));
    }
    public static String longestPalindromeDP(String s)
    {
        int n = s.length();
        boolean[][] p = new boolean[n][n];
        int len = 1, start = 0, end = 0;
        char[] ca = s.toCharArray();
        for (int j = 0; j < n; j++)
        {
            for (int i = 0; i < n - j; i++)
            {
                if ((j < 2 || p[i + 1][i + j - 1]) && ca[i] == ca[i + j])
                {
                    p[i][i + j] = true;
                    if (j + 1 > len)
                    {
                        len = j + 1;
                        start = i;
                        end = i + j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static String longestPalindrome(String s)
    {
        int n = s.length();
        int len = 1, start = 0, end = 0;
        int max = 0;
        char[] ca = s.toCharArray();

        for (int j = 0; j < 2; j++)
        {
            for (int i = 0; i < n; i++)
            {
                int x = expand(s, i, i + j);
                len = 2 * x - 1 + j;
                if (max < len)
                {
                    max = len;
                    start = i - x + 1;
                    end = i + x - 1 + j;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public static int expand(String s, int i, int j)
    {
        char[] c = s.toCharArray();
        int n = c.length;
        int x = 0;
        while (i >= 0 && j < n && c[i] == c[j])
        {
            i--;
            j++;
            x++;
        }
        return x;
    }
}
