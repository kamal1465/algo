package com.kamals.algo.design.parkinglot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParkingTicket
{
    private final ParkingType parkingType;

    private final Long ticketNumber;

    private final VehicleType vehicleType;

    private final Date entryTime;

    private final Integer spotNumber;

    private final String errorMsg;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("d-MMM h:mm a");

    public ParkingTicket(ParkingType parkingType, Long ticketNumber, VehicleType vehicleType, Date entryTime, Integer spotNumber)
    {
        this.parkingType = parkingType;
        this.ticketNumber = ticketNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.spotNumber = spotNumber;
        this.errorMsg = null;
    }

    public ParkingTicket(ParkingType parkingType, VehicleType vehicleType, Date entryTime, String errorMsg)
    {
        this.parkingType = parkingType;
        this.ticketNumber = null;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.spotNumber = null;
        this.errorMsg = errorMsg;
    }

    public long getTicketNumber()
    {
        return ticketNumber;
    }

    public VehicleType getVehicleType()
    {
        return vehicleType;
    }

    public Date getEntryTime()
    {
        return entryTime;
    }

    public Integer getSpotNumber()
    {
        return spotNumber;
    }

    @Override
    public String toString()
    {
        return parkingType + " Parking Ticket [" + vehicleType + "]" +
                "\n Ticket No: " + (ticketNumber != null ? ticketNumber : errorMsg) +
                "\n Spot No: " + spotNumber +
                "\n Entry: " + sdf.format(entryTime) + "\n";
    }
}
