package com.kamals.algo.leetcode;

public class CountHmogeneous
{
    public static void main(String[] args)
    {
        String s = "abbcccaa";
        System.out.println(countHomogenous(s));
    }
    public static int countHomogenous(String s)
    {
        int n = s.length();

        int res = 0;

        for (int i = 0; i < n; i++)
        {
            char c = s.charAt(i);
            int sublen = 1;
            for (int j = i + 1; j < n; j++)
            {
                if (s.charAt(j) == c)
                {
                    sublen++;
                    i = j;
                }
                else break;
            }
            int x = sublen / 2;
            if (sublen % 2 != 0)
            {
                x++;
            }
            else
            {
                sublen++;
            }
            x = (sublen * x) % 1000000007;
            res += x;
        }
        return res;
    }
}
