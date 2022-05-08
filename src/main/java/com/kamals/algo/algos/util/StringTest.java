package com.kamals.algo.algos.util;

import java.util.LinkedHashSet;
import java.util.Set;

public class StringTest
{
    public static void main(String[] args)
    {
        permute("abc");
    }

    private static void permute(String s)
    {
        Set<String> p = new LinkedHashSet<>();
        Set<String> q = new LinkedHashSet<>();

        Character d = s.charAt(s.length() - 1);
        String t = d.toString();

        p.add(t);

        for (int i = s.length() - 2; i >= 0; i--)
        {
            Character c = s.charAt(i);

            for (String x : p)
            {
                StringBuilder y = new StringBuilder(x);
                for (int j = 0; j < x.length(); j++)
                {
                    if (!c.equals(x.charAt(j)))
                    {
                        y.insert(j, c);
                        q.add(y.toString());
                        y.deleteCharAt(j);
                    }
                }
                y.append(c);
                q.add(y.toString());
            }
            p.clear();
            Set<String> tmp = p;
            p = q;
            q = tmp;
//            System.out.println(p);
        }
        System.out.print(p);
    }
}
