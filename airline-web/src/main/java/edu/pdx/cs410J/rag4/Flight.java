package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.AirportNames;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight implements Comparable<Flight> {

    private final int flightNumber;
    private final String src;
    private final DateFormat departFormat;
    private final Date depart;
    private final String dest;
    private final DateFormat arriveFormat;
    private final Date arrive;

    public Flight(int flightNumber, String src, String depart, String dest, String arrive) throws ParseException {
        String error = "";
        try {
            this.flightNumber = flightNumber;

            this.src = src;

            this.departFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
            this.depart = departFormat.parse(depart);

            this.dest = dest;

            this.arriveFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
            this.arrive = arriveFormat.parse(arrive);

        } catch (IllegalArgumentException ex) {
            System.err.println(error);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getNumber() {
        return this.flightNumber;
    }

    @Override
    public String getSource() {
        return this.src;
    }

    @Override
    public String getDepartureString() {
        return departFormat.format(this.depart).replace("AM", "am").replace("PM", "pm");
    }

    public Date getDepart() {
        return this.depart;
    }

    @Override
    public String getDestination() {
        return this.dest;
    }

    @Override
    public String getArrivalString() {
        return arriveFormat.format(this.arrive).replace("AM", "am").replace("PM", "pm");
    }


    public Date getArrive() {
        return this.arrive;
    }

    @Override
    public int compareTo(Flight flight) {
        int i = this.src.compareToIgnoreCase(flight.src);
        if(i != 0) return i;
        return Long.compare(this.depart.getTime(), flight.depart.getTime());
    }

    public long getDifference(){
        long milliseconds = this.arrive.getTime() - this.depart.getTime();
        long minutes = milliseconds / 60000;
        return minutes;
    }

    public String getSRCName(){
        return AirportNames.getName(this.src);
    }

    public String getDESTName(){
        return AirportNames.getName(this.dest);
    }
}