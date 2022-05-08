package com.kamals.algo.algos.util;

import java.util.*;

public class MaxSumNoCommonDigit
{
    public static void main(String[] args)
    {
        final Integer ZERO = 0;
        final Integer ONE = 1;
        final Integer MINUS = -1;
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        int[] output = new int[T];

        for (int k = 0; k < T; k++)
        {
            int N = in.nextInt();
            Set<Integer> integers = new HashSet<>(N);
            for (int i = 0; i < N; i++)
            {
                //integers.add(in.nextInt());
                integers.add(new Random().nextInt(30));
            }
            System.out.println(integers);
            N = integers.size();
            int[] x = new int[N];
            int c = 0;
            for (int i : integers)
            {
                x[c++] = i;
            }
            List<Integer>[] digits = new List[N];
            for (int i = 0; i < N; i++)
            {
                digits[i] = getDigits(x[i]);
            }

            List<Integer> res = new ArrayList<>(N);
            List<Integer>[] map = new List[N];
            for (int i = 0; i < N; i++)
            {
                map[i] = new ArrayList<>();
                res.add(ZERO);
            }
            for (int i = 0; i < N; i++)
            {
                for (int j = i + 1; j < N; j++)
                {
                    if (hasCommonDigit(digits[i], digits[j]))
                    {
                        map[i].add(j);
                        map[j].add(i);
                    }
                }
                if (map[i].isEmpty())
                {
                    res.set(i, ONE);
                }
            }


            Set<List<Integer>> all1 = new HashSet<>();
            Set<List<Integer>> all2 = new HashSet<>();
            Set<List<Integer>> all3 = new HashSet<>();
            all1.add(res);

            while (!all1.isEmpty())
            {
                all2.clear();
                System.out.println("Size = " + all1.size());
                for (List<Integer> a : all1)
                {
                    //System.out.println("******************************");
                    //print(a);
                    boolean found = false;
                    for (int i = 0; i < N; i++)
                    {
                        if (a.get(i) == ZERO)
                        {
                            List<Integer> b = new ArrayList<>(a);
                            b.set(i, ONE);
                            for (int j : map[i])
                            {
                                b.set(j, MINUS);
                            }

                            found = true;
                            all2.add(b);
                            //System.out.println("------------------------------");
                            //print(a);
                            //print(b);
                            //System.out.println("******************************");
                            //break;
                        }
                    }
                    if (!found)
                    {
                        all3.add(a);
                    }
                }

                Set<List<Integer>> tmp = all1;
                all1 = all2;
                all2 = tmp;
            }
            res = null;
            int max2 = 0;
            for (List<Integer> a: all3)
            {
                int s = add(x, a);
                //print(x, a);
                if (max2 < s)
                {
                    max2 = s;
                    res = a;
                }
            }

            for (int i = 0; i < N; i++)
            {
                if (res.get(i) == ONE)
                {
                    System.out.print(x[i] + " ");
                }
            }

            System.out.println("= " + max2);
        }
    }

    private static void print(int[] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    private static void print(int[] x, int[] a)
    {
        int s = 0;
        for (int i = 0; i < x.length; i++)
        {
            if (a[i] == 1)
            {
                System.out.print(x[i] + " ");
                s += x[i];
            }
        }
        System.out.println("-> " + s);
    }
    private static int add(int[] x, List<Integer> a)
    {
        int s = 0;
        for (int i = 0; i < x.length; i++)
        {
            if (a.get(i) == 1)
            {
                s += x[i];
            }
        }
        return s;
    }
    private static List<Integer> getDigits(int x)
    {
        List<Integer> digits = new ArrayList<>();
        do
        {
            digits.add(x % 10);
            x /= 10;
        }
        while (x > 0);
        return digits;
    }

    private static boolean hasCommonDigit(List<Integer> x, List<Integer> y)
    {
        for (int a : x)
        {
            for (int b : y)
            {
                if (a == b)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
