package com.kamals.algo.concurrent;

import com.kamals.algo.algos.util.Util;

import java.util.Random;
import java.util.stream.IntStream;

public class MatrixMultiplication
{
    public static void main(String[] args)
    {
        int n = 4;
        int[][] A = new int[n][n];
        int[] x = new int[n];

        Random random = new Random();

        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (i == n)
                {
                    x[j] = random.nextInt(10);
                }
                else
                {
                    A[i][j] = random.nextInt(10);
                }
            }
        }

        Util.printArr2(x);
        System.out.println();
        Util.printArr2(A);

        int[] y = new int[n];

        long t1 = System.nanoTime();

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                y[i] += (A[i][j] * x[j]);
            }
        }
        long t2 = System.nanoTime();

        System.out.println();
        Util.printArr2(y);
        System.out.println(t2 - t1);

        long t3 = System.nanoTime();

        long[] z = IntStream.range(0, n)
                .parallel() // comment this line to check the sequential stream
                .mapToLong(i -> IntStream.range(0, n)
                        .mapToLong(k -> A[i][k] * x[k])
                        .sum())
                .toArray();

        long t4 = System.nanoTime();

        System.out.println();
        Util.printArr2(z);
        System.out.println(t4- t3);
    }
}
