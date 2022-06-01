package com.kamals.algo.leetcode;

/**
 * 733. Flood Fill (Easy)
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 * You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 * <p>
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color
 * as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with newColor.
 * <p>
 * Return the modified image after performing the flood fill.
 * <p>
 * Example 1:
 * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
 * Output: [[2,2,2],[2,2,0],[2,0,1]]
 * Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
 * Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
 * <p>
 * Example 2:
 * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
 * Output: [[2,2,2],[2,2,2]]
 * <p>
 * Constraints:
 * m == image.length
 * n == image[i].length
 * 1 <= m, n <= 50
 * 0 <= image[i][j], newColor < 216
 * 0 <= sr < m
 * 0 <= sc < n
 */
public class FloodFill
{
    public static void main(String[] args)
    {

    }

    //Recursion Solution
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor)
    {
        int oldColor = image[sr][sc];
        image[sr][sc] = newColor;
        if (oldColor == newColor)
        {
            return image;
        }
        int row = image.length;
        int col = image[0].length;
        if (sr > 0 && image[sr - 1][sc] == oldColor)
        {
            floodFill(image, sr - 1, sc, newColor);
        }
        if (sr < row - 1 && image[sr + 1][sc] == oldColor)
        {
            floodFill(image, sr + 1, sc, newColor);
        }
        if (sc > 0 && image[sr][sc - 1] == oldColor)
        {
            floodFill(image, sr, sc - 1, newColor);
        }
        if (sc < col - 1 && image[sr][sc + 1] == oldColor)
        {
            floodFill(image, sr, sc + 1, newColor);
        }
        return image;
    }
}
