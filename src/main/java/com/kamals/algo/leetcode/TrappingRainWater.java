package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.PriorityQueue;

public class TrappingRainWater
{
    public int trapRainWater(int[][] heightMap)
    {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0)
        {
            return 0;
        }

        int total = 0;
        int m = heightMap.length, n = heightMap[0].length;

        Util.printArr2(heightMap);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++)
        {
            minHeap.add(new int[]{i, 0, heightMap[i][0]});
            minHeap.add(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int j = 0; j < n; j++)
        {
            minHeap.add(new int[]{0, j, heightMap[0][j]});
            minHeap.add(new int[]{m - 1, j, heightMap[m - 1][j]});
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int d = 4;
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while (!minHeap.isEmpty())
        {
            int[] x = minHeap.poll();

            for (int i = 0; i < d; i++)
            {
                int xnew = x[0] + dx[i];
                int ynew = x[1] + dy[i];

                if (xnew >= 0 && xnew < m && ynew >= 0 && ynew < n && !visited[xnew][ynew])
                {
                    visited[xnew][ynew] = true;
                    total += Math.max(0, x[2] - heightMap[xnew][ynew]);
                    minHeap.offer(new int[]{xnew, ynew, Math.max(x[2], heightMap[xnew][ynew])});
                }
            }
        }

        return total;
    }

    public static void main(String[] args)
    {
        int[][] i = new int[][]{{12, 13, 1, 12}, {13, 4, 13, 12}, {13, 8, 10, 12}, {12, 13, 12, 12}, {13, 13, 13, 13}};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trapRainWater(i));
    }
}
