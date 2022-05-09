package com.kamals.algo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringMatchingTest
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            String T = in.nextLine();
            if (T.equalsIgnoreCase("bye"))
            {
                break;
            }
            String P = in.nextLine();
            if (P.equalsIgnoreCase("bye"))
            {
                break;
            }
            List<Integer> res = null;
            if (P.contains("*"))
            {
                String[] ps = P.split("\\*");

                List<Integer>[] ll = new List[ps.length];
                int k = -1;
                int shift = 0;
                for (String p : ps)
                {
                    k++;
                    //System.out.println(k + "-->" + p);
                    ll[k] = match(T, p, shift);
                    if (ll[k].isEmpty())
                    {
                        System.out.println("No Match");
                        System.exit(0);
                    }
                    shift = ll[k].get(0) + p.length();
                }

                List<int[]> matches = new ArrayList<>();
                for (int i : ll[0])
                {
                    int x = i + ps[0].length();

                    for (int j = 1; j <= k; j++)
                    {
                        boolean found = false;
                        for (int y : ll[j])
                        {
                            if (y >= x)
                            {
                                found = true;
                                if (j == k)
                                {
                                    matches.add(new int[]{i, y + ps[j].length()});
                                }
                                else
                                {
                                    x = y + ps[j].length();
                                    break;
                                }
                            }
                        }
                        if (!found)
                        {
                            break;
                        }
                    }
                }

                for (int[] i : matches)
                {
                    System.out.println("Match = " + T.substring(i[0], i[1]));
                }
            }
            else
            {
                res = match(T, P);
                if (res.isEmpty())
                {
                    System.out.println("No match found");
                }
                else
                {
                    System.out.println("Match at shift: " + res);
                }
            }
        }
    }

    private static List<Integer> match(String T, String P)
    {
        return match(T, P, 0);
    }

    private static List<Integer> match(String T, String P, int shift)
    {
        List<Integer> res = new ArrayList<>();
        int count = 0;
        int l = T.length();
        int m = P.length();
        for (int i = shift; i <= l-m; i++)
        {
            boolean match = true;
            for (int j = 0; j < m; j++)
            {
                if (P.charAt(j) != T.charAt(i+j))
                {
                    match = false;
                    break;
                }
            }
            if (match)
            {
                res.add(i);
            }
        }
        return res;
    }
}
