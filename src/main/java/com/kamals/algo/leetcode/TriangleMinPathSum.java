package com.kamals.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. Triangle
 * Given a triangle array, return the minimum path sum from top to bottom.
 * <p>
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * <p>
 * Example 1:
 * <p>
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * Example 2:
 * <p>
 * Input: triangle = [[-10]]
 * Output: -10
 */
public class TriangleMinPathSum
{
    public static void main(String[] args)
    {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(1));
        triangle.add(Arrays.asList(2, 3));
        System.out.println(minimumTotal(triangle));
    }

    public static Integer minimumTotal(List<List<Integer>> triangle)
    {
        if (triangle.size() == 1)
        {
            return triangle.get(0).get(0);
        }
        Integer min = null;
        for (int i = 1; i < triangle.size(); i++)
        {
            int l = triangle.get(i).size();
            for (int j = 0; j < l; j++)
            {
                Integer a = null, b = null;
                if (j < l - 1)
                {
                    a = triangle.get(i - 1).get(j);
                }
                if (j > 0)
                {
                    b = triangle.get(i - 1).get(j - 1);
                }
                int x = triangle.get(i).get(j) + (a == null ? b : b == null ? a : Math.min(a, b));
                triangle.get(i).set(j, x);
                if (i == triangle.size() - 1)
                {
                    min = min == null ? x : Math.min(min, x);
                }
            }
        }
        return min;
    }
}
