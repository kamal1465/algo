package com.kamals.algo.design.parkinglot;

/**
 * @author Kamal.Sultania
 */
public class FlatRateFeeModel implements FeeModel
{
    private final Range RANGE;
    private final int RATE;
    private final int DURATION;
    private final boolean CHAINING;

    public FlatRateFeeModel(int min, Integer max, int rate, int duration, boolean chain)
    {
        this.RANGE = new Range(min, max);
        this.RATE = rate;
        this.DURATION = duration;
        this.CHAINING = chain;
    }

    public int calculateFees(double hours)
    {
        if (RANGE.belongs(hours))
        {
            double applicableHours = CHAINING ? RANGE.applicableHours(hours) : hours;
            int numPeriods = (int) Math.ceil(applicableHours / DURATION);
            return numPeriods * RATE;
        }
        return 0;
    }
}
