package com.kamals.algo.algos.dynamic;

import java.util.Random;

public class KnapsackProblem
{
    public static void main(String[] args)
    {
        int N = 10, W = 10;

        int MAX_WEIGHT = 10;
        int MAX_VALUE = 10;

        int[] weights = new int[N + 1];
        //int[] values = new int[N + 1];

        Random random = new Random();

        for (int i = 1; i <= N; i++)
        {
            weights[i] = random.nextInt(MAX_WEIGHT - 1) + 1;
            //values[i] = random.nextInt(MAX_VALUE);
        }

        int[][] M = new int[N + 1][W + 1];

        for (int i = 0; i <= N; i++)
        {
            for (int j = 0; j <= W; j++)
            {
                M[i][j] = i == 0 || j == 0 ? 0 : -1;
            }
        }

        printArray(weights);
        //printArray(values);

        int opt = optimum(N, W, M, weights);

        System.out.println("Optimum = " + opt);

        printArray(M);
    }

    private static int optimum(int i, int w, int[][] M, int[] weights)
    {
        if (M[i][w] != -1)
        {
            return M[i][w];
        }
        int v1 = optimum(i - 1, w, M, weights);
        int v2 = 0;
        if (weights[i] <= w)
        {
            v2 = optimum(i - 1, w - weights[i], M, weights) + weights[i];
        }

        M[i][w] = Math.max(v1, v2);
        return M[i][w];
    }

    private static void printArray(int[] M)
    {
        for (int i : M)
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void printArray(int[][] M)
    {
        for (int[] i : M)
        {
            for (int j : i)
            {
                System.out.print(j + " ");
            }
            System.out.println();
        }
/*
        for (int i = 0; i < M.length; i++)
        {
            for (int j = 0; j < M[i].length; j++)
            {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }*/
    }
}
