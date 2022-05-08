package com.kamals.algo.algos.util;

public class InterestCalculation
{
    public static void main(String[] args)
    {
        System.out.println(savingInterest());
    }

    private static double savingInterest()
    {
        long[][] amt = new long[][]{
                {76891, 2},
                {58167, 2},
                {10167, 11},
                {26, 15},
                {185940, 1},
                {65940, 11},
                {64940, 1},
                {59940, 6},
                {8865, 9},
                {194204, 3},
                {185204, 15},
                {185408, 1},
                {147219, 2},
                {141219, 6},
                {396993, 2},
                {196993, 1},
                {186993, 2}
        };

        double total = 0.0;
        for (int i = 0; i < amt.length; i++)
        {
            total += (amt[i][0] * amt[i][1]);
        }

        System.out.println(total);
        System.out.println("Average = " + total/90);

        double nt = (total * 3.5)/36500;

        return nt;
    }
}
