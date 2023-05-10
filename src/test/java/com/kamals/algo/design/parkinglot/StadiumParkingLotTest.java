package com.kamals.algo.design.parkinglot;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Calendar;
import java.util.stream.Stream;

import static com.kamals.algo.design.parkinglot.VehicleType.CAR_SUV;
import static com.kamals.algo.design.parkinglot.VehicleType.SCOOTER_BIKE;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StadiumParkingLotTest
{
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp()
    {
        parkingLot = new ParkingLot.Builder().stadium(1, 1).create();
    }

    @Test
    void testCapacity()
    {
        ParkingTicket car1 = parkingLot.admitVehicle(CAR_SUV);
        ParkingTicket car2 = parkingLot.admitVehicle(CAR_SUV);
        ParkingTicket bike1 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket bike2 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket bus1 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);
        ParkingTicket bus2 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);

        Assert.assertNotEquals(null, car1.getSpotNumber());
        Assert.assertNotEquals(null, bike1.getSpotNumber());
        Assert.assertEquals(null, bus1.getSpotNumber());
        Assert.assertEquals(null, car2.getSpotNumber());
        Assert.assertEquals(null, bike2.getSpotNumber());
        Assert.assertEquals(null, bus2.getSpotNumber());
    }

    private static Stream<Arguments> data()
    {
        return Stream.of(
                arguments((Object) new Object[]{CAR_SUV, 0, 60}),
                arguments((Object) new Object[]{CAR_SUV, 1, 60}),
                arguments((Object) new Object[]{CAR_SUV, 4, 180}),
                arguments((Object) new Object[]{CAR_SUV, 12, 380}),
                arguments((Object) new Object[]{CAR_SUV, 20, 1980}),
                arguments((Object) new Object[]{SCOOTER_BIKE, 0, 30}),
                arguments((Object) new Object[]{SCOOTER_BIKE, 1, 30}),
                arguments((Object) new Object[]{SCOOTER_BIKE, 4, 90}),
                arguments((Object) new Object[]{SCOOTER_BIKE, 12, 190}),
                arguments((Object) new Object[]{SCOOTER_BIKE, 20, 990})
        );
    }

    @ParameterizedTest
    @MethodSource(value = "data")
    void testFare(Object[] data)
    {
        VehicleType vehicleType = (VehicleType) data[0];
        int hour = (int) data[1];

        ParkingTicket parkingTicket = parkingLot.admitVehicle(vehicleType);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        cal.add(Calendar.HOUR, hour);
        ParkingReceipt parkingReceipt = parkingLot.exitVehicle(parkingTicket, cal.getTime());

        int expected = (int) data[2];
        Assert.assertEquals(expected, parkingReceipt.getFees().intValue());
    }
}
