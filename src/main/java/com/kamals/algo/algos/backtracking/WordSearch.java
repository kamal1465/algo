package com.kamals.algo.algos.backtracking;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Example 1:
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 */
public class WordSearch
{
    public static void main(String[] args)
    {
        char[][] board = new char[][]{{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        String word = "AAB";

        WordSearch wordSearch = new WordSearch();
        System.out.println(wordSearch.exist(board, word));
    }

    public boolean exist(char[][] board, String word)
    {
        int m = board.length;
        int n = board[0].length;
        int[][] occ = new int[m][n];
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] == word.charAt(0))
                {
                    System.out.println("Starting...");
                    if (backtrack(i, j, occ, board, word, 0))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean backtrack(int x, int y, int[][] occ, char[][] board, String word, int id)
    {
        System.out.println("Indexx..." + id + " x=" + x + " y=" + y);
        if (id == word.length() - 1)
        {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        occ[x][y] = 1;
        id++;
        if (x < m - 1 && occ[x + 1][y] == 0 && board[x + 1][y] == word.charAt(id))
        {
            if (backtrack(x + 1, y, occ, board, word, id))
            {
                return true;
            }
        }
        if (x > 0 && occ[x - 1][y] == 0 && board[x - 1][y] == word.charAt(id))
        {
            if (backtrack(x - 1, y, occ, board, word, id))
            {
                return true;
            }
        }
        if (y < n - 1 && occ[x][y + 1] == 0 && board[x][y + 1] == word.charAt(id))
        {
            if (backtrack(x, y + 1, occ, board, word, id))
            {
                return true;
            }
        }
        if (y > 0 && occ[x][y - 1] == 0 && board[x][y - 1] == word.charAt(id))
        {
            if (backtrack(x, y - 1, occ, board, word, id))
            {
                return true;
            }
        }
        occ[x][y] = 0;
        return false;
    }
}
