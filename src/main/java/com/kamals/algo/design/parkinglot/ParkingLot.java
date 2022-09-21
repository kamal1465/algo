package com.kamals.algo.design.parkinglot;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Kamal.Sultania
 */
public class ParkingLot
{
    protected final ParkingType parkingType;

    protected final Map<VehicleType, Set<Integer>> capacity;

    protected final Map<VehicleType, FeeCalculator> feeCalculator;

    private final AtomicLong TICKET_NUMBER = new AtomicLong(0);

    private final AtomicLong RECEIPT_NUMBER = new AtomicLong(0);

    private static final String RECEIPT_PREFIX = "R-";

    protected ParkingLot(ParkingType parkingType, Map<VehicleType, Set<Integer>> capacity, Map<VehicleType, FeeCalculator> feeCalculator)
    {
        this.parkingType = parkingType;
        this.capacity = capacity;
        this.feeCalculator = feeCalculator;
    }

    ParkingTicket admitVehicle(VehicleType vehicleType)
    {
        Date entryTime = new Date();
        Integer spotNo = getFreeSpot(vehicleType);
        if (spotNo != null)
        {
            return new ParkingTicket(parkingType, TICKET_NUMBER.incrementAndGet(), vehicleType, entryTime, spotNo);
        }
        return new ParkingTicket(parkingType, vehicleType, entryTime, "Parking not available");
    }

    ParkingReceipt exitVehicle(ParkingTicket parkingTicket)
    {
        Date exitTime = new Date();
        return exitVehicle(parkingTicket, exitTime);
    }

    //Overloaded method for testing
    ParkingReceipt exitVehicle(ParkingTicket parkingTicket, Date exitTime)
    {
        if (freeSpot(parkingTicket))
        {
            String receiptNo = RECEIPT_PREFIX + RECEIPT_NUMBER.incrementAndGet();
            int fees = calculateFees(parkingTicket, exitTime);
            return new ParkingReceipt(parkingType, receiptNo, parkingTicket.getVehicleType(), parkingTicket.getEntryTime(), exitTime, fees);
        }
        return new ParkingReceipt(parkingType, null, parkingTicket.getVehicleType(), null, exitTime, null);
    }

    private Integer getFreeSpot(VehicleType vehicleType)
    {
        if (capacity.get(vehicleType) != null && !capacity.get(vehicleType).isEmpty())
        {
            int s = capacity.get(vehicleType).iterator().next();
            capacity.get(vehicleType).remove(s);
            return s;
        }
        return null;
    }

    private boolean freeSpot(ParkingTicket parkingTicket)
    {
        VehicleType vehicleType = parkingTicket.getVehicleType();
        Integer spot = parkingTicket.getSpotNumber();
        if (spot != null && !capacity.get(vehicleType).contains(spot))
        {
            capacity.get(vehicleType).add(spot);
            return true;
        }
        return false;
    }

    private int calculateFees(ParkingTicket parkingTicket, Date exitTime)
    {
        Date entryTime = parkingTicket.getEntryTime();
        VehicleType vehicleType = parkingTicket.getVehicleType();
        double hours = (double) (exitTime.getTime() - entryTime.getTime()) / (3600 * 1000);
        return feeCalculator.get(vehicleType).calculateFees(hours);
    }

    @Override
    public String toString()
    {
        return parkingType + " Parking Lot" + "\n  Capacity = " + capacity;
    }

    static class Builder
    {
        private ParkingType pType;

        private Map<VehicleType, Integer> cap = new HashMap<>();

        private Map<VehicleType, FeeCalculator> feeCalculator = new HashMap<>();

        private void setVehicleCapacity(VehicleType vehicleType, int capacity)
        {
            cap.put(vehicleType, capacity);
        }

        public Builder mall(int scooterCap, int carCap, int busCap)
        {
            pType = ParkingType.MALL;
            setVehicleCapacity(VehicleType.SCOOTER_BIKE, scooterCap);
            setVehicleCapacity(VehicleType.CAR_SUV, carCap);
            setVehicleCapacity(VehicleType.BUS_TRUCK, busCap);

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
            return this;
        }

        public Builder stadium(int scooterCap, int carCap)
        {
            pType = ParkingType.STADIUM;
            setVehicleCapacity(VehicleType.SCOOTER_BIKE, scooterCap);
            setVehicleCapacity(VehicleType.CAR_SUV, carCap);

            feeCalculator.put(VehicleType.SCOOTER_BIKE,
                    new FeeCalculator.Builder()
                            .addSlabFlatFee(0, 4, 30, true)
                            .addSlabFlatFee(4, 12, 60, true)
                            .addSlabFlatRate(12, null, 100, 1, true)
                            .create());
            feeCalculator.put(VehicleType.CAR_SUV,
                    new FeeCalculator.Builder()
                            .addSlabFlatFee(0, 4, 60, true)
                            .addSlabFlatFee(4, 12, 120, true)
                            .addSlabFlatRate(12, null, 200, 1, true)
                            .create());
            return this;
        }

        public Builder airport(int scooterCap, int carCap)
        {
            pType = ParkingType.AIRPORT;
            setVehicleCapacity(VehicleType.SCOOTER_BIKE, scooterCap);
            setVehicleCapacity(VehicleType.CAR_SUV, carCap);

            feeCalculator.put(VehicleType.SCOOTER_BIKE,
                    new FeeCalculator.Builder()
                            .addSlabFlatFee(0, 1, 0, false)
                            .addSlabFlatFee(1, 8, 40, false)
                            .addSlabFlatFee(8, 24, 60, false)
                            .addSlabFlatRate(24, null, 80, 24, false)
                            .create());
            feeCalculator.put(VehicleType.CAR_SUV,
                    new FeeCalculator.Builder()
                            .addSlabFlatFee(0, 12, 60, false)
                            .addSlabFlatFee(12, 24, 80, false)
                            .addSlabFlatRate(24, null, 100, 24, false)
                            .create());
            return this;
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

            ParkingLot parkingLot = new ParkingLot(pType, capacity, feeCalculator);
            pType = null;
            cap = null;
            feeCalculator = null;
            return parkingLot;
        }
    }
}
