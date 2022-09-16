package com.kamals.algo.design.parkinglot;

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
        if (CHAINING && RANGE.exceeds(hours))
        {
            int applicableHours = RANGE.applicableHours(hours);
            int numPeriods = (int) Math.ceil(applicableHours / DURATION);
            return numPeriods * RATE;
        }
        if (!CHAINING && RANGE.belongs(hours))
        {
            int numPeriods = (int) Math.ceil(hours / DURATION);
            return numPeriods * RATE;
        }
        return 0;
    }
}
