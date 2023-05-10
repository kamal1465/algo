package com.kamals.algo.design.parkinglot;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;

class MallParkingLotTest
{
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp()
    {
        parkingLot = new ParkingLot.Builder().mall(1, 1, 1).create();
    }

    @Test
    void testCapacity()
    {
        ParkingTicket car1 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        ParkingTicket car2 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        ParkingTicket bike1 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket bike2 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket bus1 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);
        ParkingTicket bus2 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);

        Assert.assertNotEquals(null, car1.getSpotNumber());
        Assert.assertNotEquals(null, bike1.getSpotNumber());
        Assert.assertNotEquals(null, bus1.getSpotNumber());
        Assert.assertEquals(null, car2.getSpotNumber());
        Assert.assertEquals(null, bike2.getSpotNumber());
        Assert.assertEquals(null, bus2.getSpotNumber());
    }

    private static int[][] carData()
    {
        return new int[][]{{0, 20}, {1, 40}, {5, 120}, {12, 260}};
    }

    private static int[][] bikeData()
    {
        return new int[][]{{0, 10}, {1, 20}, {5, 60}, {12, 130}};
    }

    private static int[][] busData()
    {
        return new int[][]{{0, 50}, {1, 100}, {5, 300}, {12, 650}};
    }

    @ParameterizedTest
    @MethodSource(value = "carData")
    void testCarFare(int[] data)
    {
        int hour = data[0];

        ParkingTicket car1 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        cal.add(Calendar.HOUR, hour);
        ParkingReceipt carReceipt1 = parkingLot.exitVehicle(car1, cal.getTime());

        int expected = data[1];
        Assert.assertEquals(expected, carReceipt1.getFees().intValue());
    }

    @ParameterizedTest
    @MethodSource(value = "bikeData")
    void testBikeFare(int[] data)
    {
        int hour = data[0];

        ParkingTicket bike = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        cal.add(Calendar.HOUR, hour);
        ParkingReceipt bikeReceipt = parkingLot.exitVehicle(bike, cal.getTime());

        int expected = data[1];
        Assert.assertEquals(expected, bikeReceipt.getFees().intValue());
    }

    @ParameterizedTest
    @MethodSource(value = "busData")
    void testBusFare(int[] data)
    {
        int hour = data[0];

        ParkingTicket bus = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        cal.add(Calendar.HOUR, hour);
        ParkingReceipt busReceipt = parkingLot.exitVehicle(bus, cal.getTime());

        int expected = data[1];
        Assert.assertEquals(expected, busReceipt.getFees().intValue());
    }
}
