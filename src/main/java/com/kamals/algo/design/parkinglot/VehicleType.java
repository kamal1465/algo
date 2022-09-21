package com.kamals.algo.design.parkinglot;

/**
 * @author Kamal.Sultania
 */
public enum VehicleType
{
    SCOOTER_BIKE("Scooter/Bike"),
    CAR_SUV("Car/SUV"),
    BUS_TRUCK("Bus/Truck");

    String name;

    VehicleType(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
