package com.kamals.algo.design.parkinglot;

import java.util.Date;

public class ParkingTicket
{
    private final Long ticketNumber;

    private final VehicleType vehicleType;

    private final Date entryTime;

    private final Integer spotNumber;

    public ParkingTicket(Long ticketNumber, VehicleType vehicleType, Date entryTime, Integer spotNumber)
    {
        this.ticketNumber = ticketNumber;
        this.vehicleType = vehicleType;
        this.entryTime = entryTime;
        this.spotNumber = spotNumber;
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
        return "Parking Ticket [" + vehicleType + "]" +
                "\n Ticket Number: " + ticketNumber +
                "\n Spot Number: " + spotNumber +
                "\n Entry Date-Time: " + entryTime + "\n";
    }
}
