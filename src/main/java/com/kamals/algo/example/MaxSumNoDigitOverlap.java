package com.kamals.algo.example;

import java.util.Scanner;

public class MaxSumNoDigitOverlap
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int T = in.nextInt();

        int[] output = new int[T];

        for (int k = 0; k < T; k++)
        {
            int N = in.nextInt();
            int[] x = new int[N];
            for (int j = 0; j < N; j++)
            {
                x[j] = in.nextInt();
            }

            output[k] = 0;
        }

        for (int k = 0; k < T; k++)
        {
            System.out.println(output[k]);
        }
    }
}
