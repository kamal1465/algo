package com.kamals.algo.algos.backtracking;

public class NQueens
{
    public static void main(String[] args)
    {
        int n = 4;
        int[] num = new int[1];

		//2.10 start
		int[] queens = new int[n];
		long t1 = System.nanoTime();
		solve(queens, 0, n, num, new boolean[n]);
		t1 = System.nanoTime() - t1;
		t1 /= 1000000;
		System.out.println("Solutions: " + num[0] + " Time: " + t1);
    }

    private static void solve(int[] queens, int i, int n, int[] num, boolean[] pos)
    {
    	if (i == n)
    	{
            num[0]++;
    		//System.out.println("Solution: " + num[0]);
    		//printBoard(queens, n);
    		return;
    	}
    	for (int k = 0; k < n; k++)
    	{
    		if (!pos[k] && isViable(queens, i, k))
    		{
    			queens[i] = k;
				pos[k] = true;
    			solve(queens, i + 1, n, num, pos);
				pos[k] = false;
    		}
    	}
    }

    private static boolean isViable(int[] queens, int i, int k)
    {
    	for (int j = 0; j < i; j++)
    	{
    		int l = queens[j];
    		if (l == k)
    		{
    			return false;
    		}
    		int x = i - j;
    		int y = k - l;
    		if (x == y || x == -y)
    		{
    			return false;
    		}
    	}
    	return true;
    }

    private static void printBoard(int[] queens, int n)
    {
    	for (int i = 0; i < n; i++)
    	{
    		for (int j = 0; j < n; j++)
    		{
    			if (queens[i] == j)
    			{
    				System.out.print(" 1 ");
    			}
    			else
    			{
    				System.out.print(" 0 ");	
    			}
    		}
    		System.out.println();
    	}
    }
}
