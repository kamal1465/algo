package com.kamals.algo.algos.backtracking;

import com.kamals.algo.algos.util.Util;

import java.util.*;

public class Sudoku
{

    private static final boolean BIT_VECTOR = true;

    public static void main(String[] args)
    {
        int[][] board = new int[][]{
                /*{5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}};*/

                {0, 0, 0, 0, 0, 0, 0, 1, 2},
                {0, 0, 0, 0, 3, 5, 0, 0, 0},
                {0, 0, 0, 6, 0, 0, 0, 7, 0},
                {7, 0, 0, 0, 0, 0, 3, 0, 0},
                {0, 0, 0, 4, 0, 0, 8, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 2, 0, 0, 0, 0},
                {0, 8, 0, 0, 0, 0, 0, 4, 0},
                {0, 5, 0, 0, 0, 0, 6, 0, 0}};

        Util.printArr2(board);
        Sudoku sudoku = new Sudoku();
        List<List<Integer>> moves = new ArrayList<>();
        long t1 = System.nanoTime();
        sudoku.solve(board, 0, 0, moves);
        long t2 = System.nanoTime();
        t2 = (t2 - t1) / 1000;
        for (List<Integer> move : moves)
        {
            System.out.println("[" + move.get(0) + ", " + move.get(1) + "] = " + move.get(2));
        }
        System.out.println("Solution: " + t2);
        Util.printArr2(board);

    }

    private boolean solve(int[][] board, int i, int j, List<List<Integer>> moves)
    {
        int[] next = findNext(board, i, j);

        if (next == null)
        {
            return true;
        }

        i = next[0];
        j = next[1];

        //System.out.println("Found empty " + i + " " + j);

        boolean[] bitVector = null;

        if (BIT_VECTOR)
        {
            bitVector = getBitVector(board, next[0], j);
        }

        for (int k = 1; k < 10; k++)
        {
            if (BIT_VECTOR ? !bitVector[k] : !collide(board, i, j, k))
            {
//                System.out.println("Putting " + k + " at " + i + " " + j);
                board[i][j] = k;
                moves.add(Arrays.asList(i, j, k));
                if (solve(board, i, j + 1, moves))
                {
                    return true;
                }
                moves.remove(moves.size() - 1);
                board[i][j] = 0;
            }
        }

        return false;
    }

    private int[] findNext(int[][] board, int i, int j)
    {
        for (int x = i; x < board.length; x++)
        {
            for (int y = j; y < board[x].length; y++)
            {
                if (board[x][y] == 0)
                {
                    return new int[]{x, y};
                }
            }
            j = 0;
        }
        return null;
    }

    private boolean[] getBitVector(int[][] board, int i, int j)
    {
        boolean[] flag = new boolean[10];

        for (int x = 0; x < board.length; x++)
        {
            flag[board[x][j]] = true;
            flag[board[i][x]] = true;
        }

        i = (i / 3) * 3;
        j = (j / 3) * 3;

        for (int x = i; x < i + 3; x++)
        {
            for (int y = j; y < j + 3; y++)
            {
                flag[board[x][y]] = true;
            }
        }

        return flag;
    }

    private boolean collide(int[][] board, int i, int j, int k)
    {
        //boolean[] flag = new boolean[10];
        //System.out.println("Checking " + k + " [" + i + " " + j + "]");
        for (int x = 0; x < board.length; x++)
        {
            if (k == board[x][j] || k == board[i][x])
            {
                return true;
            }
        }

        i = (i / 3) * 3;
        j = (j / 3) * 3;

        for (int x = i; x < i + 3; x++)
        {
            for (int y = j; y < j + 3; y++)
            {
                //System.out.println("Checking [" + x + " " + y + "]");
                if (k == board[x][y])
                {
                    return true;
                }
            }
        }

        return false;
    }

}
