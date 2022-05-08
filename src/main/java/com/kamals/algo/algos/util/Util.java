package com.kamals.algo.algos.util;

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
}
