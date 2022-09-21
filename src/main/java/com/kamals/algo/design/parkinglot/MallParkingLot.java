package com.kamals.algo.design.parkinglot;

import java.util.*;

/**
 * @author Kamal.Sultania
 */
public class MallParkingLot extends ParkingLot
{
    private MallParkingLot(ParkingType parkingType, Map<VehicleType, Set<Integer>> capacity, Map<VehicleType, FeeCalculator> feeCalculator)
    {
        super(parkingType, capacity, feeCalculator);
    }

    static class Builder
    {
        private Map<VehicleType, Integer> cap = new HashMap<>();

        private Map<VehicleType, FeeCalculator> feeCalculator = new HashMap<>();

        private void setVehicleCapacity(VehicleType vehicleType, int capacity)
        {
            cap.put(vehicleType, capacity);
        }

        public Builder(int scooterCap, int carCap, int busCap)
        {
            setVehicleCapacity(VehicleType.SCOOTER_BIKE, scooterCap);
            setVehicleCapacity(VehicleType.CAR_SUV, carCap);
            setVehicleCapacity(VehicleType.BUS_TRUCK, busCap);
        }

        public ParkingLot create()
        {
            Map<VehicleType, Set<Integer>> capacity = new HashMap<>();
            int spot = 1;
            for (Map.Entry<VehicleType, Integer> c : cap.entrySet())
            {
                capacity.put(c.getKey(), new LinkedHashSet<>());
                for (int i = 0; i < c.getValue(); i++)
                {
                    capacity.get(c.getKey()).add(spot++);
                }
            }
            feeCalculator.put(VehicleType.SCOOTER_BIKE,
                    new FeeCalculator.Builder()
                            .addSlabFlatRate(0, null, 10, 1, false)
                            .create());
            feeCalculator.put(VehicleType.CAR_SUV,
                    new FeeCalculator.Builder()
                            .addSlabFlatRate(0, null, 20, 1, false)
                            .create());
            feeCalculator.put(VehicleType.BUS_TRUCK,
                    new FeeCalculator.Builder()
                            .addSlabFlatRate(0, null, 50, 1, false)
                            .create());

            ParkingLot parkingLot = new MallParkingLot(ParkingType.MALL, capacity, feeCalculator);
            cap = null;
            feeCalculator = null;
            return parkingLot;
        }
    }
}
