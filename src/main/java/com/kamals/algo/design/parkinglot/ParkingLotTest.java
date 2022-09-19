package com.kamals.algo.design.parkinglot;

import java.util.Calendar;

public class ParkingLotTest
{
    public static void main(String[] args)
    {
        testMall();
        testStadium();
        testAirport();
    }

    private static void testMall()
    {
        ParkingLot parkingLot = new ParkingLot.Builder().mall(10, 10, 10).create();

        ParkingTicket parkingTicket1 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        ParkingTicket parkingTicket2 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket parkingTicket3 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);

        ParkingReceipt parkingReceipt1 = parkingLot.exitVehicle(parkingTicket1);
        ParkingReceipt parkingReceipt2 = parkingLot.exitVehicle(parkingTicket1);
        ParkingReceipt parkingReceipt3 = parkingLot.exitVehicle(parkingTicket2);
        ParkingReceipt parkingReceipt4 = parkingLot.exitVehicle(parkingTicket3);

        System.out.print(parkingTicket1);
        System.out.print(parkingTicket2);
        System.out.print(parkingTicket3);
        System.out.print(parkingReceipt1);
        System.out.print(parkingReceipt2);
        System.out.print(parkingReceipt3);
        System.out.print(parkingReceipt4);
        testFare(parkingLot, VehicleType.CAR_SUV, 1);
    }

    private static void testAirport()
    {
        ParkingLot parkingLot = new ParkingLot.Builder().airport(10, 10).create();

        ParkingTicket parkingTicket1 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        ParkingTicket parkingTicket2 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket parkingTicket3 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);

        ParkingReceipt parkingReceipt1 = parkingLot.exitVehicle(parkingTicket1);
        ParkingReceipt parkingReceipt2 = parkingLot.exitVehicle(parkingTicket1);
        ParkingReceipt parkingReceipt3 = parkingLot.exitVehicle(parkingTicket2);
        ParkingReceipt parkingReceipt4 = parkingLot.exitVehicle(parkingTicket3);

        System.out.print(parkingTicket1);
        System.out.print(parkingTicket2);
        System.out.print(parkingTicket3);
        System.out.print(parkingReceipt1);
        System.out.print(parkingReceipt2);
        System.out.print(parkingReceipt3);
        System.out.print(parkingReceipt4);
        testFare(parkingLot, VehicleType.CAR_SUV, 10);
    }

    private static void testStadium()
    {
        ParkingLot parkingLot = new ParkingLot.Builder().stadium(10, 10).create();

        ParkingTicket parkingTicket1 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        ParkingTicket parkingTicket2 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket parkingTicket3 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);

        ParkingReceipt parkingReceipt1 = parkingLot.exitVehicle(parkingTicket1);
        ParkingReceipt parkingReceipt2 = parkingLot.exitVehicle(parkingTicket1);
        ParkingReceipt parkingReceipt3 = parkingLot.exitVehicle(parkingTicket2);
        ParkingReceipt parkingReceipt4 = parkingLot.exitVehicle(parkingTicket3);

        System.out.print(parkingTicket1);
        System.out.print(parkingTicket2);
        System.out.print(parkingTicket3);
        System.out.print(parkingReceipt1);
        System.out.print(parkingReceipt2);
        System.out.print(parkingReceipt3);
        System.out.print(parkingReceipt4);

        testFare(parkingLot, VehicleType.CAR_SUV, 10);
    }

    private static void testFare(ParkingLot parkingLot, VehicleType vehicleType, int hours)
    {
        ParkingTicket parkingTicket = parkingLot.admitVehicle(vehicleType);
        System.out.print(parkingTicket);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, hours);
        ParkingReceipt parkingReceipt = parkingLot.exitVehicle(parkingTicket, cal.getTime());
        System.out.print(parkingReceipt);
    }
}
