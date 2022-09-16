package com.kamals.algo.design.parkinglot;

public class FlatFeeModel implements FeeModel
{
    private final Range RANGE;
    private final int FEE;
    private final boolean CHAINING;

    public FlatFeeModel(int min, Integer max, int fee, boolean chain)
    {
        this.RANGE = new Range(min, max);
        this.FEE = fee;
        this.CHAINING = chain;
    }

    @Override
    public int calculateFees(double hours)
    {
        if (CHAINING && RANGE.exceeds(hours))
        {
            return FEE;
        }
        else if (!CHAINING && RANGE.belongs(hours))
        {
            return FEE;
        }
        return 0;
    }
}
