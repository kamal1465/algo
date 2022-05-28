package com.kamals.algo.algos.dynamic;

import com.kamals.algo.algos.util.Util;

import java.util.*;

/**
 * Start time - 7:54 PM Friday 27 May
 * End time - 8:35 PM
 */
public class AdvertisingBoards
{
    /**
     * Kleinberg & Tardos page 308 Solved exercise 1
     * <p>
     * OPT(j) = MAX(Rj + OPT(e(j)), OPT(j-1))
     * Where e(j) is the maximum pos < j which is compatible with j
     * <p>
     * O(n) Solution
     */
    public static int maxRevenue3(int[] advBoardPos, int[] revenue, int minGap)
    {
        int countOps = 0;
        int N = advBoardPos.length;
        int[] OPT = new int[N];
        OPT[0] = revenue[0];
        int k = -1;
        int optLast = 0;
        for (int i = 1; i < N; i++)
        {
            while (advBoardPos[i] - advBoardPos[k + 1] >= minGap)
            {
                countOps++;
                optLast = OPT[k + 1];
                k++;
            }
            OPT[i] = Math.max(revenue[i] + optLast, OPT[i - 1]);
        }
        deducePosFromOpt(advBoardPos, revenue, OPT);
        System.out.println("Count Ops = " + countOps);
        return OPT[N - 1];
    }

    public static int maxRevenue2(int[] advBoardPos, int[] revenue, int minGap)
    {
        int countOps = 0;
        int N = advBoardPos.length;
        int[] OPT = new int[N];
        OPT[0] = revenue[0];
        for (int i = 1; i < N; i++)
        {
            int optLast = 0;
            for (int j = i - 1; j >= 0; j--)
            {
                countOps++;
                if (Math.abs(advBoardPos[i] - advBoardPos[j]) >= minGap)
                {
                    optLast = OPT[j];
                    break;
                }
            }
            OPT[i] = Math.max(revenue[i] + optLast, OPT[i - 1]);
        }
        deducePosFromOpt(advBoardPos, revenue, OPT);
        System.out.println("Count Ops = " + countOps);
        return OPT[N - 1];
    }

    public static int maxRevenue(int[] advBoardPos, int[] revenue, int minGap)
    {
        int countOps = 0;
        int N = advBoardPos.length;
        int[] maxRevenue = new int[N];
        int[] prevIndex = new int[N];
        Arrays.fill(prevIndex, -1);
        int max = 0, maxI = -1;
        for (int i = 0; i < N; i++)
        {
            maxRevenue[i] = revenue[i];
            for (int j = i - 1; j >= 0; j--)
            {
                countOps++;
                if (Math.abs(advBoardPos[i] - advBoardPos[j]) >= minGap)
                {
                    if (maxRevenue[j] + revenue[i] > maxRevenue[i])
                    {
                        maxRevenue[i] = maxRevenue[j] + revenue[i];
                        prevIndex[i] = j;
                    }
                }
            }
            if (maxRevenue[i] > max)
            {
                max = maxRevenue[i];
                maxI = i;
            }
        }
        List<Integer> pos = new ArrayList<>();
        pos.add(maxI);
        while (prevIndex[maxI] != -1)
        {
            pos.add(0, prevIndex[maxI]);
            maxI = prevIndex[maxI];
        }
        Util.printArr2(maxRevenue);
        //        Util.printArr2(prevIndex);
        System.out.println(pos);
        System.out.println("Count Ops = " + countOps);
        return max;
    }

    private static void deducePosFromOpt(int[] advBoardPos, int[] revenue, int[] OPT)
    {
        List<Integer> pos = new ArrayList<>();
        int N = advBoardPos.length;
        int x = OPT[N - 1];
        for (int j = N - 1; j >= 0 && x > 0; j--)
        {
            if (OPT[j] == x && (j == 0 || OPT[j] != OPT[j - 1]))
            {
                pos.add(0, j);
                x -= revenue[j];
            }
        }
        int[] a = pos.stream().mapToInt(o -> advBoardPos[o]).toArray();
        int[] b = pos.stream().mapToInt(o -> revenue[o]).toArray();
        System.out.println(Util.mapToString(a, b));
    }

    public static void main(String[] args)
    {
        int N = 10;
        int[] advBoardPos = new int[N];
        int[] revenue = new int[N];
        int minGap = 10;
        Random random = new Random();
        for (int i = 0; i < N; i++)
        {
            advBoardPos[i] = random.nextInt(N * minGap) + 1;
            revenue[i] = random.nextInt(10) + 1;
        }
        Arrays.sort(advBoardPos);
        System.out.println(Util.mapToString(advBoardPos, revenue));
        System.out.println(maxRevenue2(advBoardPos, revenue, minGap));
        System.out.println(maxRevenue3(advBoardPos, revenue, minGap));
    }
}
