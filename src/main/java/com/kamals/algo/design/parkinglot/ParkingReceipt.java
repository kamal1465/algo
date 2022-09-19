package com.kamals.algo.design.parkinglot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingReceipt
{
    private final ParkingType parkingType;

    private final String receiptNumber;

    private final VehicleType vehicleType;

    private final Date entryTime;

    private final Date exitTime;

    private final Integer fees;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("d-MMM h:mm a");

    public ParkingReceipt(ParkingType parkingType, String receiptNumber, VehicleType vehicleType, Date entryTime, Date exitTime, Integer fees)
    {
        this.parkingType = parkingType;
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
        return parkingType + " Parking Receipt [" + vehicleType + "]" +
                "\n Receipt No: " + receiptNumber +
                "\n Entry: " + (entryTime != null ? sdf.format(entryTime) : entryTime) +
                "\n Exit: " + (exitTime != null ? sdf.format(exitTime) : exitTime) +
                "\n Fees: " + fees + "\n";
    }
}
