package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight {

    private final int flightNumber;
    private final String src;
    private final String depart;
    private final String dest;
    private final String arrive;

    //Class implementation for Flight
    public Flight(int flightNumber, String src, String depart, String dest, String arrive){
        this.flightNumber = flightNumber;

        //SRC
        if (src.length() < 3) {
            throw new IllegalArgumentException("src: three-letter code is too SMALL");
        }
        if (src.length() > 3) {
            throw new IllegalArgumentException("src: three-letter code is too BIG");
        }
        if (Pattern.compile("[0-9]").matcher(src).find()){
            throw new IllegalArgumentException("src: three-letter code contains a NUMBER");
        }
        this.src = src;

        //DEPART
        this.depart = depart;

        //DEST
        if (dest.length() < 3) {
            throw new IllegalArgumentException("dest: three-letter code is too SMALL");
        }
        if (dest.length() > 3) {
            throw new IllegalArgumentException("dest: three-letter code is too BIG");
        }
        if (Pattern.compile("[0-9]").matcher(dest).find()){
            throw new IllegalArgumentException("dest: three-letter code contains a NUMBER");
        }
        this.dest = dest;

        //ARRIVE
        this.arrive = arrive;
    }

    //returns the flightNumber
    @Override
    public int getNumber() {
        return this.flightNumber;
    }

    //returns the src
    @Override
    public String getSource() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        return this.src;
    }

    @Override
    public String getDepartureString() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        return this.depart;
    }

    //returns the dest
    @Override
    public String getDestination() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        return this.dest;
    }

    //returns the arrive
    @Override
    public String getArrivalString() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        return this.arrive;
    }
}
