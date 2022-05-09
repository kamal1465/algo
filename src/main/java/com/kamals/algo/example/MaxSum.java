package com.kamals.algo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MaxSum
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();

        for (int k = 0; k < T; k++)
        {
            int N = in.nextInt();
            int[] x = new int[N];
            for (int j = 0; j < N; j++)
            {
                x[j] = in.nextInt();
            }

            /*Random random = new Random();
            int[] x = new int[10];
            for (int j = 0; j < 10; j++)
            {
                x[j] = random.nextInt(20) - 10;
                System.out.print(x[j] + " ");
            }
            System.out.println();*/

            int max0 = 0, max1 = 0;
            List<Integer> ml0 = new ArrayList<>(), ml1 = new ArrayList<>();
            List<Integer> tmp;
            for (int l = x.length-1; l >= 0; l--)
            {
                int i = x[l];

                int max0_2, max1_2;

                if (max0 > max1)
                {
                    max0_2 = max0;
                    max1_2 = i == 0 ? -1 : max0 + i;
                    ml1 = new ArrayList<>(ml0);
                    ml1.add(i);
                }
                else if (max0 < max1 || (max0 == 0 && max1 == 0))
                {
                    max0_2 = max1;
                    max1_2 = i == 0 ? -1 : max0 + i;
                    ml0.add(i);
                    tmp = ml1;
                    ml1 = ml0;
                    ml0 = tmp;
                }
                else
                {
                    max0_2 = max0;
                    max1_2 = i == 0 ? -1 : max0 + i;

                    int j = 0;
                    while (ml0.size() > j && ml1.size() > j && ml0.get(j).intValue() == ml1.get(j).intValue())
                    {
                        j++;
                    }
                    if (ml0.size() == j || ml1.size() == j || ml0.get(j) < ml1.get(j))
                    {
                        ml0.add(i);
                        tmp = ml1;
                        ml1 = ml0;
                        ml0 = tmp;
                    }
                    else
                    {
                        ml1 = new ArrayList<>(ml0);
                        ml1.add(i);
                    }
                }
                max0 = max0_2;
                max1 = max1_2;
            }

            List<Integer> res;
            /*if (max0 <= 0 && max1 <= 0)
            {
                res = new ArrayList<>();
                if (x.length > 0)
                {
                    Integer max = null;
                    for (int i : x)
                    {
                        if ((max == null || i > max) && i != 0)
                        {
                            max = i;
                        }
                    }
                    if (max != null)
                    res.add(max);
                }
            }
            else*/ if (max0 > max1)
            {
                //System.out.println(max0 + " --> " + ml0);
                res = ml0;
            }
            else if (max0 < max1)
            {
                //System.out.println(max1 + " --> " + ml1);
                res = ml1;
            }
            else
            {
                int j = 0;
                while (ml0.size() > j && ml1.size() > j && ml0.get(j).intValue() == ml1.get(j).intValue())
                {
                    j++;
                }
                if (ml0.size() == j || ml1.size() == j || ml0.get(j) < ml1.get(j))
                {
                    //System.out.println(max1 + " --> " + ml1);
                    res = ml1;
                }
                else
                {
                    //System.out.println(max0 + " --> " + ml0);
                    res = ml0;
                }
            }
            System.out.println(res.stream().map(e -> e.toString()).collect(Collectors.joining("")));
        }
    }
}
