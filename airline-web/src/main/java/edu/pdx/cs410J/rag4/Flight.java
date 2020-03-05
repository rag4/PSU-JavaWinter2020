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

public class Flight extends AbstractFlight {

    public Flight(int flightNumber) {
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public String getSource() {
        return null;
    }

    @Override
    public String getDepartureString() {
        return null;
    }

    @Override
    public String getDestination() {
        return null;
    }

    @Override
    public String getArrivalString() {
        return null;
    }
}