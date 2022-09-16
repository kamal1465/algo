package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

import java.util.Random;

/**
 * 542. 01 Matrix
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 * <p>
 * Example 1:
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 * <p>
 * Example 2:
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 * <p>
 * Constraints:
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * There is at least one 0 in mat.
 */
public class Matrix
{
    private int countOps;

    public static void main(String[] args)
    {
        int m = 3, n = 3;

        int[][] matrix = new int[m][n];
        Random random = new Random();
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                matrix[i][j] = random.nextInt(2);
            }
        }
        Matrix matrix1 = new Matrix();
        matrix1.updateMatrixImproved(matrix);
        System.out.println(matrix1.countOps);
        Util.printArr2(matrix);
    }

    /**
     * Slow, took 291ms faster than only 5%
     */
    public int[][] updateMatrix(int[][] mat)
    {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mat[i][j] == 1)
                {
                    mat[i][j] = -1;
                }
            }
        }
        boolean done = false;
        int x = 0;
        while (!done)
        {
            done = true;
            for (int i = 0; i < m; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (mat[i][j] == -1)
                    {
                        if (i > 0 && mat[i - 1][j] == x)
                        {
                            mat[i][j] = x + 1;
                        }
                        else if (i < m - 1 && mat[i + 1][j] == x)
                        {
                            mat[i][j] = x + 1;
                        }
                        else if (j > 0 && mat[i][j - 1] == x)
                        {
                            mat[i][j] = x + 1;
                        }
                        else if (j < n - 1 && mat[i][j + 1] == x)
                        {
                            mat[i][j] = x + 1;
                        }
                        else
                        {
                            done = false;
                        }
                    }
                }
            }
            x++;
        }
        return mat;
    }

    public int[][] updateMatrixImproved(int[][] mat)
    {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mat[i][j] == 1)
                {
                    mat[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mat[i][j] == 0)
                {
                    updateMatrixRecursive(mat, i, j, m, n);
                }
            }
        }
        return mat;
    }

    public void updateMatrixRecursive(int[][] mat, int i, int j, int m, int n)
    {
        System.out.println("calling for " + (i + 1) + " " + (j + 1));
        countOps++;
        int x = mat[i][j] + 1;
        boolean b1 = false, b2 = false, b3 = false, b4 = false;
        if (i > 0 && (mat[i - 1][j] == -1 || mat[i - 1][j] > x))
        {
            b1 = true;
            mat[i - 1][j] = x;
        }
        if (i < m - 1 && (mat[i + 1][j] == -1 || mat[i + 1][j] > x))
        {
            b2 = true;
            mat[i + 1][j] = x;
        }
        if (j > 0 && (mat[i][j - 1] == -1 || mat[i][j - 1] > x))
        {
            b3 = true;
            mat[i][j - 1] = x;
        }
        if (j < n - 1 && (mat[i][j + 1] == -1 || mat[i][j + 1] > x))
        {
            b4 = true;
            mat[i][j + 1] = x;
        }
        if (b1)
        {
            updateMatrixRecursive(mat, i - 1, j, m, n);
        }
        if (b2)
        {
            updateMatrixRecursive(mat, i + 1, j, m, n);
        }
        if (b3)
        {
            updateMatrixRecursive(mat, i, j - 1, m, n);
        }
        if (b4)
        {
            updateMatrixRecursive(mat, i, j + 1, m, n);
        }
    }

    public int[][] updateMatrixDp(int[][] mat)
    {
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mat[i][j] == 1)
                {
                    mat[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (mat[i][j] == -1)
                {
                    if (i > 0 && mat[i - 1][j] != -1)
                    {
                        mat[i][j] = mat[i - 1][j] + 1;
                    }
                    if (j > 0 && mat[i][j - 1] != -1)
                    {
                        mat[i][j] = mat[i][j] == -1 ? mat[i][j - 1] + 1 : Math.min(mat[i][j], mat[i][j - 1] + 1);
                    }
                }
            }
        }
        for (int i = m - 1; i >= 0; i--)
        {
            for (int j = n - 1; j >= 0; j--)
            {
                if (mat[i][j] == -1 || mat[i][j] > 1)
                {
                    if (i < m - 1 && mat[i + 1][j] != -1)
                    {
                        mat[i][j] = mat[i][j] == -1 ? mat[i + 1][j] + 1 : Math.min(mat[i][j], mat[i + 1][j] + 1);
                    }
                    if (j < n - 1 && mat[i][j + 1] != -1)
                    {
                        mat[i][j] = mat[i][j] == -1 ? mat[i][j + 1] + 1 : Math.min(mat[i][j], mat[i][j + 1] + 1);
                    }
                }
            }
        }
        return mat;
    }
}
