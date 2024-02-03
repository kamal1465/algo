package com.kamals.algo.algos.sort;

import com.kamals.algo.algos.util.Util;

import java.util.Arrays;

public class HeapSort
{
    public static void main(String[] args)
    {
        int n = 1000;
        int[] arr = Util.genRandomArray(0, 1000, n);
        Util.printArr2(arr);
        int[] copy = Arrays.copyOf(arr, n);
        //Util.printArr2(copy);
        HeapSort heapSort = new HeapSort();
        long time1 = System.nanoTime();
        heapSort.sort(arr);
        long time2 = System.nanoTime();
        Arrays.sort(copy);
        long time3 = System.nanoTime();
        long d1 = (time2 - time1) / 1000;
        long d2 = (time3 - time2) / 1000;
        System.out.println("HeapSort: Time taken: " + d1);
        //Util.printArr2(arr);
        System.out.println("Arrays.sort(): Time taken: " + d2);
        //Util.printArr2(copy);
        System.out.println(Arrays.compare(arr, copy));
    }

    public void sort(int[] arr)
    {
        int n = arr.length;
        for (int i = (n - 2) / 2; i >= 0; i--)
        {
            bubbleDown(arr, i, n);
        }

        while (n > 1)
        {
            int max = arr[0];
            arr[0] = arr[n - 1];
            arr[n - 1] = max;
            n--;
            bubbleDown(arr, 0, n);
        }
    }

    private void bubbleDown(int[] arr, int i, int n)
    {
        int max = i;
        int j = getYoungChild(i);
        for (int k = j; k <= j + 1 && k < n; k++)
        {
            if (arr[k] > arr[max])
            {
                max = k;
            }
        }
        if (max != i)
        {
            int tmp = arr[i];
            arr[i] = arr[max];
            arr[max] = tmp;
            bubbleDown(arr, max, n);
        }
    }

    private int getYoungChild(int i)
    {
        return 2 * i + 1;
    }
}
