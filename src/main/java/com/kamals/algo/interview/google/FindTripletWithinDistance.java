package com.kamals.algo.interview.google;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

/**
 * 16-02-2024 First Round
 * Given a distance d, and stream of float values, all of which are distinct
 * Store float values in memory
 * Return first triplet a, b, c, such that |a - b| <= d, and |b - c| <= d, and |a - c| <= d
 * and remove them from memory
 */
public class FindTripletWithinDistance
{
    public static void main(String[] args)
    {
        float d = 5.0f;
        float[] arr = new float[]{1, 5, 10, 15, 20, 25, 30, 45, 22};
        System.out.println(find(arr, d));
    }

    private static List<Float> find(float[] arr, float d)
    {
        TreeSet<Float> bst = new TreeSet<>();

        for (float f : arr)
        {
            Float f1 = bst.floor(f);
            if (f1 != null)
            {
                Float f2 = bst.lower(f1);
                if (f2 != null && f - f2 <= d)
                {
                    bst.remove(f1);
                    bst.remove(f2);
                    return Arrays.asList(f2, f1, f);
                }
            }
            Float c1 = bst.ceiling(f);
            if (c1 != null)
            {
                Float c2 = bst.higher(c1);
                if (c2 != null && c2 - f <= d)
                {
                    bst.remove(c1);
                    bst.remove(c2);
                    return Arrays.asList(f, c1, c2);
                }
            }
            if (f1 != null && c1 != null && c1 - f1 <= d)
            {
                bst.remove(f1);
                bst.remove(c1);
                return Arrays.asList(f1, f, c1);
            }
            bst.add(f);
        }
        return null;
    }
}
