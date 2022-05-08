package com.kamals.algo.algos.dynamic;

import java.util.Random;

/**
 * Sequence Alignment
 * ?? Sequence Matching
 * ?? Find longest matching sub-sequence
 */
public class SequenceMatching
{
    private static final int GAP_COST = 1;
    private static final int DELTA_COST = 3;

    public static void main(String[] args)
    {
        char[] alpha = new char[]{'a', 'b', 'c'};
        int L = 5;
        String seq1 = generateSequence(alpha, L);
        String seq2 = generateSequence(alpha, L);
        System.out.println("Seq1 = " + seq1);
        System.out.println("Seq2 = " + seq2);

        int dist = performSequenceAlignment(seq1, seq2);

        System.out.println("Distance = " + dist);
    }

    private static int performSequenceAlignment(String seq1, String seq2)
    {
        int l1 = seq1.length();
        int l2 = seq2.length();

        int[][] ds = new int[l1 + 1][l2 + 1];

        for (int i = 0; i <= l1; i++)
        {
            ds[i][0] = i * GAP_COST;
        }

        for (int i = 0; i <= l2; i++)
        {
            ds[0][i] = i * GAP_COST;
        }

        for (int i = 1; i <= l1; i++)
        {
            for (int j = 1; j <= l2; j++)
            {
                if (seq1.charAt(i - 1) == seq2.charAt(j - 1))
                {
                    ds[i][j] = ds[i - 1][j - 1];
                }
                else
                {
                    ds[i][j] = DELTA_COST + ds[i - 1][j - 1];
                }
                ds[i][j] = Math.min(ds[i][j], Math.min(ds[i - 1][j] + GAP_COST, ds[i][j - 1] + GAP_COST));
            }
        }

        return ds[l1][l2];
    }

    private static String generateSequence(char[] alpha, int L)
    {
        int x = alpha.length;

        Random random = new Random();

        char[] seq = new char[L];
        for (int i = 0; i < L; i++)
        {
            seq[i] = alpha[random.nextInt(x)];
        }
        return String.valueOf(seq);
    }
}
