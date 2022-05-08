package com.kamals.algo.algos.util;

import java.util.Arrays;
import java.util.Scanner;

public class PhasesStates2
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();

        boolean[] output = new boolean[T];

        for (int k = 0; k < T; k++)
        {
            int P = in.nextInt();
            int S = in.nextInt();

            int[] x = new int[P];
            int[] y = new int[S];

            int sum1 = 0, sum2 = 0;

            for (int i = 0; i < P; i++)
            {
                x[i] = in.nextInt();
                sum1 += x[i];
            }
            for (int i = 0; i < S; i++)
            {
                y[i] = in.nextInt();
                sum2 += y[i];
            }

            if (sum1 != sum2)
            {
                System.out.println("NO");
                continue;
            }

            Arrays.sort(x);
            Arrays.sort(y);

            int[][] M = new int[P][S];
            for (int i = 0; i < P; i++)
            {
                for (int j = 0; j < S; j++)
                {
                    M[i][j] = 0;
                }
            }

            for (int i = 0; i < P; i++)
            {
                for (int j = 0; j < x[i]; j++)
                {
                    M[i][j] = 1;
                }
            }

            boolean flag = true;
            for (int j = 0; j < S; j++)
            {
                int count = 0;
                for (int i = 0; i < P; i++)
                {
                    count += M[i][j];
                }
                if (y[S-j-1] != count)
                {
                    flag = false;
                    break;
                }
            }

            System.out.println(flag ? "YES" : "NO");
        }
    }


}
