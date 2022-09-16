package com.kamals.algo.algos.util;

import java.util.Arrays;

public class Util
{
    public static String mapToString(int[] a, int[] b)
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int N = Math.max(a.length, b.length);
        for (int i = 0; i < N; i++)
        {
            sb.append(i < a.length ? a[i] : "").append("=").append(i < b.length ? b[i] : "").append(", ");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(']');
        return sb.toString();
    }

    public static void printArr2(int[] arr)
    {
        System.out.println(Arrays.toString(arr));
    }

    public static void printArr2(int[][] arr)
    {
        StringBuilder sb = new StringBuilder();
//        sb.append('[');
        for (int[] a : arr)
        {
            sb.append(Arrays.toString(a)).append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
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
