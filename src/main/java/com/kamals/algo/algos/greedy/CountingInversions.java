package com.kamals.algo.algos.greedy;

import com.kamals.algo.algos.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountingInversions
{
    public static void main(String[] args)
    {
        int N = 5;
        int[] arr = randomPermutation(N);
        Util.printArr2(arr);

        int[] count = new int[1];

    }

    public static int[] randomPermutation(int N)
    {
        int[] arr = new int[N];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++)
        {
            list.add(i);
        }
        Random random = new Random();
        int k = 0;
        while (k < N)
        {
            int x = random.nextInt(list.size());
            arr[k++] = list.remove(x);
        }
        return arr;
    }

    public static void countInversions(int[] arr, int[] count)
    {

    }
}
