package com.kamals.algo.design.parkinglot;

import java.util.Date;

public class ParkingReceipt
{
    private final String receiptNumber;

    private final VehicleType vehicleType;

    private final Date entryTime;

    private final Date exitTime;

    private final Integer fees;

    public ParkingReceipt(String receiptNumber, VehicleType vehicleType, Date entryTime, Date exitTime, Integer fees)
    {
        this.receiptNumber = receiptNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.fees = fees;
    }

    public String getReceiptNumber()
    {
        return receiptNumber;
    }

    public VehicleType getVehicleType()
    {
        return vehicleType;
    }

    public Date getEntryTime()
    {
        return entryTime;
    }

    public Date getExitTime()
    {
        return exitTime;
    }

    public Integer getFees()
    {
        return fees;
    }

    @Override
    public String toString()
    {
        return "Parking Receipt [" + vehicleType + "]" +
                "\n Receipt Number: " + receiptNumber +
                "\n Entry Date-Time: " + entryTime +
                "\n Exit Date-Time: " + exitTime +
                "\n Fees: " + fees + "\n";
    }
}
