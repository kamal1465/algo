package com.kamals.algo.design.parkinglot;

public class ParkingLotTest
{
    public static void main(String[] args) throws Exception
    {
        long l = 5;
        double d = (double) l / 3600;
        System.out.println(d);

        ParkingLot.Builder builder = new ParkingLot.Builder();
        builder.setFeeModel(ParkingType.MALL);
        builder.setVehicleCapacity(VehicleType.SCOOTER_BIKE, 10);
        builder.setVehicleCapacity(VehicleType.CAR_SUV, 100);
        ParkingLot parkingLot = builder.create();

        ParkingTicket parkingTicket1 = parkingLot.admitVehicle(VehicleType.CAR_SUV);
        ParkingTicket parkingTicket2 = parkingLot.admitVehicle(VehicleType.SCOOTER_BIKE);
        ParkingTicket parkingTicket3 = parkingLot.admitVehicle(VehicleType.BUS_TRUCK);

        //Thread.sleep(5000);

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
    }
}
