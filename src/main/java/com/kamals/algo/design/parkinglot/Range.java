package com.kamals.algo.design.parkinglot;

/**
 * @author Kamal.Sultania
 */
public class Range
{
    private final Integer min;
    private final Integer max;

    public Range(Integer min, Integer max)
    {
        this.min = min;
        this.max = max;
    }

    boolean belongs(double d)
    {
        boolean ir = true;
        if (min != null && d < (double) min)
        {
            ir = false;
        }
        if (max != null && d >= (double) max)
        {
            ir = false;
        }
        return ir;
    }

    boolean exceeds(double d)
    {
        return max != null && d >= (double) max;
    }

    double applicableHours(double d)
    {
        if (min != null && d >= (double) min)
        {
            return d - (double) min;
        }
        return -1;
    }
}
