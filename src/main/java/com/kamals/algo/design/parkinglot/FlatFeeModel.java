package com.kamals.algo.design.parkinglot;

/**
 * @author Kamal.Sultania
 */
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
        if (RANGE.belongs(hours))
        {
            return FEE;
        }
        if (CHAINING && RANGE.exceeds(hours))
        {
            return FEE;
        }
        return 0;
    }
}
