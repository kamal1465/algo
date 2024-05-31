package com.kamals.algo.leetcode;

import com.kamals.algo.algos.util.Util;

public class Sudoku
{
    public static void main(String[] args)
    {
        char[][] board = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        Util.printArr2(board);
        System.out.println();
        Sudoku sudoku = new Sudoku();
        sudoku.solveSudoku2(board);
        Util.printArr2(board);
    }

    public void solveSudoku2(char[][] board)
    {
        solveSudoku(board, 0, 0);
    }

    private boolean solveSudoku2(char[][] board, int i, int j)
    {
        for (int p = i; p < 9; p++)
        {
            for (int q = j; q < 9; q++)
            {
                if (board[p][q] == '.')
                {
                    for (char k = '1'; k <= '9'; k++)
                    {
                        if (!collide(board, p, q, k))
                        {
                            System.out.println(p + " " + q + " " + k);
                            board[p][q] = k;
                            if (solveSudoku(board, p, q + 1))
                            {
                                return true;
                            }
                            board[p][q] = '.';
                        }
                    }
                    return false;
                }
            }
            j = 0;
        }
        return true;
    }

    public void solveSudoku(char[][] board)
    {
        int[] next = nextSlot(board, 0, 0);
        if (next != null)
        {
            solveSudoku(board, next[0], next[1]);
        }
    }

    private boolean solveSudoku(char[][] board, int i, int j)
    {
        for (char k = '1'; k <= '9'; k++)
        {
            if (!collide(board, i, j, k))
            {
                System.out.println(i + " " + j + " " + k);
                board[i][j] = k;
                int[] next = nextSlot(board, i, j + 1);
                if (next == null)
                {
                    return true;
                }
                if (solveSudoku(board, next[0], next[1]))
                {
                    return true;
                }
                board[i][j] = '.';
            }
        }
        return false;
    }

    private boolean collide(char[][] board, int i, int j, char k)
    {
        for (int p = 0; p < 9; p++)
        {
            if (board[p][j] == k || board[i][p] == k)
            {
                return true;
            }
        }
        i = (i / 3) * 3;
        j = (j / 3) * 3;
        for (int p = 0; p < 3; p++)
        {
            for (int q = 0; q < 3; q++)
            {
                if (board[i + p][j + q] == k)
                {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] nextSlot(char[][] board, int i, int j)
    {
        for (int p = i; p < 9; p++)
        {
            for (int q = j; q < 9; q++)
            {
                if (board[p][q] == '.')
                {
                    return new int[]{p, q};
                }
            }
            j = 0;
        }
        return null;
    }

}
