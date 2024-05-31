package com.kamals.algo.interview.google;

import java.util.TreeSet;

/**
 * Given an array of integers: A[0,1,..,n], k and delta
 * Return true if exists (i, j) such that, abs(i - j) <= k, and abs(A[i] - A[j]) <= delta
 */
public class CloserNumbersExist
{
    private static boolean closeNumbersExist3(int[] a, int k, int delta)
    {
        int n = a.length;

        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++)
        {
            Integer x = treeSet.floor(a[i]);
            if (x != null && a[i] - x <= delta)
            {
                System.out.println("Found i = " + x + " j = " + a[i]);
                return true;
            }
            x = treeSet.ceiling(a[i]);
            if (x != null && x - a[i] <= delta)
            {
                System.out.println("Found i = " + x + " j = " + a[i]);
                return true;
            }
            treeSet.add(a[i]);
            if (i >= k)
            {
                treeSet.remove(a[i - k]);
            }
        }

        return false;
    }

    private static boolean closeNumbersExist2(int[] a, int k, int delta)
    {
        int n = a.length;
        for (int i = 0; i < n - k; i++)
        {
            for (int j = i + 1; j <= i + k; j++)
            {
                int x = Math.abs(a[i] - a[j]);
                if (x <= delta)
                {
                    System.out.println("Found i = " + a[i] + " j = " + a[j]);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean closeNumbersExist(int[] a, int k, int delta)
    {
        int n = a.length;
        for (int i = 1; i <= k; i++)
        {
            for (int j = 0; j < n - i; j++)
            {
                int x = Math.abs(a[j] - a[j + i]);
                if (x <= delta)
                {
                    System.out.println("Found i = " + a[j] + " j = " + a[j + i]);
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        int[] a = new int[]{1, 5, 9, 13, 17, 21, 14};
        int k = 2;
        int d = 3;
        System.out.println(closeNumbersExist(a, k, d));

        System.out.println(closeNumbersExist3(a, k, d));
    }


    private static boolean closeNumbersExist4(int[] a, int k, int delta)
    {
        TreeSet<Integer> bst = new TreeSet<>();
        int rem = 0;
        for (int i = 0; i < a.length; i++)
        {
            Integer x = bst.ceiling(a[i]);
            if (x != null && x - a[i] <= delta)
            {
                return true;
            }
            x = bst.floor(a[i]);
            if(x != null && a[i] - x <= delta)
            {
                return true;
            }
            bst.add(a[i]);
            /*if (bst.size() > k)
            {
                bst.remove(a[rem++]);
            }*/
            if (i >= k)
            {
                bst.remove(a[i - k]);
            }
        }

        return false;
    }
}
