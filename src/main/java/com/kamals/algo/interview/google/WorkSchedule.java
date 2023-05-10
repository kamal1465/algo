package com.kamals.algo.interview.google;

/**
 * Compute work schedule to maximize earnings.
 * 1 Day travel time will be needed to switch cities.
 * <p>
 * Array A[] - Earnings day wise for city A
 * <p>
 * S(i, k) = Max(S(i-1, k), Max(i-2, ~k)) + A[i]
 */
public class WorkSchedule
{
    private static String computeSchedule2(int[] A, int[] B)
    {
        if (A == null)
        {
            A = new int[0];
        }
        if (B == null)
        {
            B = new int[0];
        }
        int m = A.length;
        int n = B.length;

        int x = Math.max(m, n);

        int[][] S = new int[x][2];

        for (int i = 0; i < x; i++)
        {
            if (i < m)
            {
                S[i][0] = A[i];
                if (i == 1)
                {
                    S[i][0] += S[i - 1][0];
                }
                else if (i > 1)
                {
                    S[i][0] += Math.max(S[i - 1][0], S[i - 2][1]);
                }
            }
            if (i < n)
            {
                S[i][1] = B[i];
                if (i == 1)
                {
                    S[i][1] += S[i - 1][1];
                }
                else if (i > 1)
                {
                    S[i][1] += Math.max(S[i - 1][1], S[i - 2][0]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        char[] c = new char[]{'A', 'B'};
        int[][] d = new int[2][];
        d[0] = A;
        d[1] = B;

        int k = S[x - 1][0] > S[x - 1][1] ? 0 : 1;

        for (int i = x - 1; i >= 0; i--)
        {
            sb.insert(0, c[k]);

            int y = S[i][k] - d[k][i];

            int j = (k + 1) % 2;
            if (i > 1 && y == S[i - 2][j])
            {
                sb.insert(0, 'T');
                i--;
                k = j;
            }
        }

        return sb.toString();
    }

    private static String computeSchedule(int[] A, int[] B)
    {
        if (A == null)
        {
            A = new int[0];
        }
        if (B == null)
        {
            B = new int[0];
        }
        int m = A.length;
        int n = B.length;

        int x = Math.max(m, n);

        int[][] S = new int[x + 1][2];

        for (int i = 1; i <= x; i++)
        {
            if (i <= m)
            {
                S[i][0] = A[i - 1];
                if (i > 1)
                {
                    S[i][0] += Math.max(S[i - 1][0], S[i - 2][1]);
                }
            }
            if (i <= n)
            {
                S[i][1] = B[i - 1];
                if (i > 1)
                {
                    S[i][1] += Math.max(S[i - 1][1], S[i - 2][0]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        int k = S[x][0] > S[x][1] ? 0 : 1;

        for (int i = x; i > 0; i--)
        {
            sb.insert(0, k == 0 ? 'A' : 'B');

            int y = S[i][k] - (k == 0 ? A[i - 1] : B[i - 1]);

            int j = (k + 1) % 2;
            if (i > 1 && y == S[i - 2][j])
            {
                sb.insert(0, 'T');
                i--;
                k = j;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args)
    {
        int[] a = new int[]{23, 4, 5, 10};
        int[] b = new int[]{21, 1, 10, 100};
        int[] c = new int[]{25, 10, 15, 10, 70};
        int[] d = new int[]{5, 5, 50, 5, 30};
        int[] e = new int[0];//{23};
        int[] f = new int[]{24};
        System.out.println(computeSchedule(e, d));
        System.out.println(computeSchedule(a, b));
        System.out.println(computeSchedule(c, d));
        System.out.println();
        System.out.println(computeSchedule2(e, d));
        System.out.println(computeSchedule2(a, b));
        System.out.println(computeSchedule2(c, d));
    }
}
