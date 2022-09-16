package com.kamals.algo.interview.amazon;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given portion sizes of a Pizza and three persons will consume it with rule that, A will choose first slice,
 * then B will get left slice of that and C will get right slice.
 * Find out number of ways in which A can consume more Pizza that both B and C.
 * <p>
 * Backtracking - Iterate over all possible ways, A, B, C can consume Pizza. When Pizza is finished, calculate their total
 * consumptions and increment counter if CA > CB & CA > CC.
 * <p>
 * Recursion - will branch out like a Tree and every leaf node is one way in which Pizza can be consumed.
 * Root node will have N child nodes, since A can pick any of N slices.
 * First level node will have N-3 child nodes, since 3 slices are consumed and A can pick any of N-3 slices.
 */
public class MaxPizzaConsumed
{

    public static void main(String[] args)
    {
        int[] pizza = new int[]{4, 6, 3, 4, 5, 2};
        List<int[]> ways = new ArrayList<>();
        System.out.println(Arrays.toString(pizza));
        int maxPizzaWaysCount = maxPizza(pizza, ways);
        System.out.println(maxPizzaWaysCount);
        for (int[] w : ways)
            System.out.println(Arrays.toString(w));

        StringBuilder sb = new StringBuilder();
//        sb.dele
    }

    static int maxPizza(int[] pizza, List<int[]> ways)
    {
        // 0 - free
        // 1 - A consumed
        // 2 - B consumed
        // 3 - C consumed
        int[] consumed = new int[pizza.length];
        int[] count = new int[1];
        for (int i = 0; i < pizza.length; i++)
        {
            pizza(pizza, consumed, i, count, ways);
        }
        return count[0];
    }

    static void pizza(int[] pizza, int[] consumed, int i, int[] count, List<int[]> ways)
    {
        boolean found = false;
        consumed[i] = 1;
        int b = findNext(consumed, i, -1);
        int c = -1;
        if (b != -1)
        {
            consumed[b] = 2;
            c = findNext(consumed, i, 1);
            if (c != -1)
            {
                consumed[c] = 3;
                for (int j = 0; j < pizza.length; j++)
                {
                    if (consumed[j] == 0)
                    {
                        found = true;
                        pizza(pizza, consumed, j, count, ways);
                    }
                }
            }
        }
        if (!found && compute(pizza, consumed))
        {
            count[0]++;
            ways.add(consumed.clone());
        }
        consumed[i] = 0;
        if (b != -1)
        {
            consumed[b] = 0;
        }
        if (c != -1)
        {
            consumed[c] = 0;
        }
    }

    static boolean compute(int[] pizza, int[] consumed)
    {
        int ca = 0, cb = 0, cc = 0;
        for (int i = 0; i < pizza.length; i++)
        {
            switch (consumed[i])
            {
                case 1:
                    ca += pizza[i];
                    break;
                case 2:
                    cb += pizza[i];
                    break;
                case 3:
                    cc += pizza[i];
            }
        }
        return ca > cb && ca > cc;
    }

    static int findNext(int[] consumed, int i, int j)
    {
        int n = consumed.length;
        int k = i;
        while (consumed[i] != 0)
        {
            i += j;
            if (i < 0)
            {
                i += n;
            }
            else if (i >= n)
            {
                i -= n;
            }
            if (i == k)
            {
                return -1;
            }
        }
        return i;
    }

}
