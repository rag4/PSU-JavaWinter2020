package edu.pdx.cs410J.rag4.androidcalculator;

import edu.pdx.cs410J.AbstractFlight;

public class Flight extends AbstractFlight {

    private final int number;
    private final String source;
    private final String departure;
    private final String destination;
    private final String arrival;

    public Flight(int number, String source, String departure, String destination, String arrival) {
        this.number = number;
        this.source = source;
        this.departure = departure;
        this.destination = destination;
        this.arrival = arrival;
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public String getSource() {
        return this.source;
    }

    @Override
    public String getDepartureString() { 
        return this.departure;
    }

    @Override
    public String getDestination() { 
        return this.destination;
    }

    @Override
    public String getArrivalString() { 
        return this.arrival;
    }
}