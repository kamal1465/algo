package com.kamals.algo.algos.util;

import java.util.Arrays;

public class Util
{
    public static void printArr(int[] arr)
    {
        System.out.print('[');
        int i = 0;
        for (int x : arr)
        {
            if (i == 0)
            {
                System.out.print(x);
            }
            else
            {
                System.out.print("  " + x);
            }
            i = 1;
        }
        System.out.println(']');
    }

    public static void printArr2(int[] arr)
    {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArr2(int[][] arr)
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int[] a : arr)
        {
            sb.append(Arrays.toString(a)).append(", ");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);
        sb.append(']');
        System.out.println(sb);
    }

    public static void main(String[] args)
    {
        int[] a = new int[]{1, 2, 3, 4};
        printArr2(a);
        int[][] b = new int[][]{{1, 2}, {3, 4}, {5, 6}};
        printArr2(b);
    }
}
