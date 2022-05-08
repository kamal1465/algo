package com.kamals.algo.algos.dnc;

public class CountInversions
{
    private static int inv_dnc = 0;

    public static void main(String[] args)
    {
        int n = 1000;

        int[] arr = new int[n];

        int[] flag = new int[n];

        int pos = 0, count = 0;

        while (pos < n)
        {
            count++;
            double rand = Math.random();

            int next = (int) Math.ceil(rand * n);

            if (flag[next-1] == 0)
            {
                arr[pos++] = next;
                flag[next-1] = 1;
            }
        }

        System.out.println("Took " + count + " iterations. Generated randon seq = ");
        printArr(arr);

        long start = System.nanoTime();
        int inv = countInversions(arr);
        long end = System.nanoTime();
        long taken = end - start;
        System.out.println("Brute force time taken in ms = " + taken);
        System.out.println("Brute force inversions = " + inv);

        start = System.nanoTime();
        int[] in = countInversionsDnc(arr, 0, n-1);
        end = System.nanoTime();
        long takenDnc = end - start;
        printArr(in);
        System.out.println("DnC time taken in ms = " + takenDnc);
        System.out.println("DnC inversions = " + inv_dnc);

        double percent = (taken - takenDnc) * 100 / taken;
        System.out.println("Dnc improvement% = " + percent);
    }

    private static int[] countInversionsDnc(int[] arr, int i, int j)
    {
        if (i == j)
        {
            return new int[]{arr[i]};
        }
        int mid = (i + j)/2;

        int[] in1 = countInversionsDnc(arr, i, mid);
        int[] in2 = countInversionsDnc(arr, mid+1, j);

        return mergeAndCount(in1, in2);
    }

    private static int[] mergeAndCount(int[] a1, int[] a2)
    {
        int n1 = a1.length;
        int n2 = a2.length;

        int n = n1 + n2;

        int[] in = new int[n];

        int i = 0, j = 0, pos = 0;

        while (i < n1 || j < n2)
        {
//            System.out.println("N1 = " + n1 + " N2 = " + n2 + " i = " + i + " j = " + j);
            if (j >= n2)
            {
                in[pos++] = a1[i++];
            }
            else if (i >= n1)
            {
                in[pos++] = a2[j++];
            }
            else if (a1[i] <= a2[j])
            {
                in[pos++] = a1[i++];
            }
            else if (a1[i] > a2[j])
            {
                in[pos++] = a2[j++];
                inv_dnc += (n1 - i);
            }
        }
        return in;
    }

    private static int countInversions(int[] arr)
    {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n-1; i++)
        {
            for (int j = i+1; j < n; j++)
            {
                if (arr[i] > arr[j])
                {
                    count++;
                }
            }
        }
        return count;
    }

    private static void printArr(int[] arr)
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
