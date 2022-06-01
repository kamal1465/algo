package com.kamals.algo.leetcode;

public class MaxAreaOfIsland
{
    public int maxAreaOfIsland(int[][] grid)
    {
        int m = grid.length;
        int n = grid[0].length;

        int[][] visited = new int[m][n];

        int max = 0;

        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (grid[i][j] == 1 && visited[i][j] == 0)
                {
                    max = Math.max(maxAreaOfIsland(grid, visited, i, j), max);
                }
            }
        }
        return max;
    }

    public int maxAreaOfIsland(int[][] grid, int[][] visited, int i, int j)
    {
        int x = 1;
        int m = grid.length;
        int n = grid[0].length;
        visited[i][j] = 1;
        if (i > 0 && grid[i - 1][j] == 1 && visited[i - 1][j] == 0)
        {
            x += maxAreaOfIsland(grid, visited, i - 1, j);
        }
        if (i < m - 1 && grid[i + 1][j] == 1 && visited[i + 1][j] == 0)
        {
            x += maxAreaOfIsland(grid, visited, i + 1, j);
        }
        if (j > 0 && grid[i][j - 1] == 1 && visited[i][j - 1] == 0)
        {
            x += maxAreaOfIsland(grid, visited, i, j - 1);
        }
        if (j < n - 1 && grid[i][j + 1] == 1 && visited[i][j + 1] == 0)
        {
            x += maxAreaOfIsland(grid, visited, i, j + 1);
        }

        return x;
    }
}
