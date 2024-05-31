package com.kamals.algo.algos.recursive;

/**
 * Tower of Hanoi
 * <p>
 * Three Bars - N discs -> 1 to N numbered
 */
public class TowerOfHanoi
{
    public static void main(String[] args)
    {
        int N = 4;

        shift(N, 'A', 'C', 'B');
    }

    private static void shift(int n, char source, char dest, char spare)
    {
        if (n == 0)
        {
            return;
        }
        shift(n - 1, source, spare, dest);
        System.out.println("Move " + n + " from " + source + " to " + dest);
        shift(n - 1, spare, dest, source);
    }
}
