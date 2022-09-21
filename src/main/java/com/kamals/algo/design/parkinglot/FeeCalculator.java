package com.kamals.algo.design.parkinglot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Kamal.Sultania
 */
public class FeeCalculator
{
    private final Collection<FeeModel> feeModels;

    private FeeCalculator(Collection<FeeModel> feeModels)
    {
        this.feeModels = feeModels;
    }

    public int calculateFees(double hours)
    {
        int fees = 0;
        for (FeeModel feeModel : feeModels)
        {
            fees += feeModel.calculateFees(hours);
        }
        return fees;
    }

    static class Builder
    {
        private Collection<FeeModel> feeModels;

        public Builder()
        {
            feeModels = new ArrayList<>();
        }

        public Builder addSlabFlatFee(int min, Integer max, int fee, boolean chaining)
        {
            feeModels.add(new FlatFeeModel(min, max, fee, chaining));
            return this;
        }

        public Builder addSlabFlatRate(int min, Integer max, int rate, int duration, boolean chaining)
        {
            feeModels.add(new FlatRateFeeModel(min, max, rate, duration, chaining));
            return this;
        }

        public FeeCalculator create()
        {
            FeeCalculator feeCalculator = new FeeCalculator(Collections.unmodifiableCollection(feeModels));
            feeModels = null;
            return feeCalculator;
        }
    }
}
