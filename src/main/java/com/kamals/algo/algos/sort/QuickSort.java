package com.kamals.algo.algos.sort;

import com.kamals.algo.algos.util.Util;

import java.util.Arrays;
import java.util.Random;

public class QuickSort
{
    public static void main(String[] args)
    {
        int n = 20;
        int[] arr = Util.genRandomArray(0, 1000, n);
        Util.printArr2(arr);
        int[] copy = Arrays.copyOf(arr, n);
        //Util.printArr2(copy);
        QuickSort quickSort = new QuickSort();
        Random rand = new Random();
        long time1 = System.nanoTime();
        quickSort.sort(arr, 0, n - 1, rand);
        long time2 = System.nanoTime();
        Arrays.sort(copy);
        long time3 = System.nanoTime();
        long d1 = (time2 - time1) / 1000;
        long d2 = (time3 - time2) / 1000;
        System.out.println("QuickSort: Time taken: " + d1);
        Util.printArr2(arr);
        System.out.println("Arrays.sort(): Time taken: " + d2);
        Util.printArr2(copy);
        System.out.println(Arrays.compare(arr, copy));
    }

    public void sort(int[] arr, int i, int j, Random rand)
    {
        if (i >= j)
        {
            return;
        }

        int pivot = partition3(arr, i, j, rand);

        sort(arr, i, pivot - 1, rand);
        sort(arr, pivot + 1, j, rand);
    }

    private int partition(int[] arr, int lo, int high, Random rand)
    {
        int p = high;
        int firstHigh = lo;

        for (int i = lo; i < high; i++)
        {
            if (arr[i] < arr[p])
            {
                swap(arr, i, firstHigh);
                firstHigh++;
            }
        }
        swap(arr, firstHigh, p);

        return firstHigh;
    }

    private int partition2(int[] arr, int lo, int high, Random rand)
    {
        int p = lo;
        int firstHigh = lo + 1;

        for (int i = lo + 1; i <= high; i++)
        {
            if (arr[i] < arr[p])
            {
                swap(arr, i, firstHigh);
                firstHigh++;
            }
        }
        swap(arr, --firstHigh, p);

        return firstHigh;
    }

    private int partition3(int[] arr, int lo, int high, Random rand)
    {
        int p = lo + rand.nextInt(high - lo + 1);
        swap(arr, p, high);
        p = high;
        int firstHigh = lo;

        for (int i = lo; i < high; i++)
        {
            if (arr[i] < arr[p])
            {
                swap(arr, i, firstHigh);
                firstHigh++;
            }
        }
        swap(arr, firstHigh, p);
        return firstHigh;
    }

    private void swap(int[] arr, int i, int j)
    {
        if (i != j)
        {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }
}
