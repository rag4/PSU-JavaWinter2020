package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight implements Comparable<Flight>{

    private int flightNumber; // the flight number
    private String src = ""; // the source airport three-letter code
    private Date depart; // the departure date and time
    private String dest = ""; // the destination airport three-letter code
    private Date arrive; // the arrival date and time

    /**
     * Class implementation for Flight / Constructor
     * @param flightNumber
     * @param src
     * @param depart
     * @param dest
     * @param arrive
     */
    public Flight(int flightNumber, String src, String depart, String dest, String arrive) throws IllegalArgumentException {
        String error = "";
        this.flightNumber = flightNumber;

        //SRC
        try {
            if (src.length() < 3) { // if src has no or too few letters
                error = "THE LENGTH IS TOO SMALL.";
                throw new IllegalArgumentException();
            }
            if (src.length() > 3) { // if src has too many letters
                error = "THE LENGTH IS TOO BIG.";
                throw new IllegalArgumentException();
            }
            if (Pattern.compile("[^a-zA-Z]").matcher(src).find()) { // if src contains something other than a letter
                error = "IT  CONTAINS AN ILLEGAL CHARACTER.";
                throw new IllegalArgumentException();
            }
            this.src = src; // initialize
        } catch (IllegalArgumentException e) {
            System.err.print("The SRC you have inputted is not valid. " + error);
            throw new IllegalArgumentException();
        }

        //DEPART
        try {
            if (depart.length() < 16) { // if depart is smaller than expected
                error = "THE LENGTH IS TOO SMALL.";
                throw new IllegalArgumentException();
            }
            if (depart.length() > 16) { // if depart is bigger than expected
                error = "THE LENGTH IS TOO BIG.";
                throw new IllegalArgumentException();
            }
            if (depart.contains("[a-zA-Z]+")) { // if depart contains letters
                error = "IT SHOULDN'T CONTAIN LETTERS.";
                throw new IllegalArgumentException();
            }
            for (int i = 0; i <= 15; i++) { // check validity of certain strings
                if (i == 2 || i == 5) { // check for proper backslash
                    if (depart.charAt(i) != '/') {
                        error = "FORMAT IS WRONG. CANNOT FIND BACKSLASH IN CORRECT POSITION";
                        throw new IllegalArgumentException();
                    } else {
                        i++;
                    }
                }
                if (i == 10) { // check for proper whitespace
                    if (!Character.isWhitespace(depart.charAt(i))) {
                        error = "FORMAT IS WRONG. CANNOT FIND WHITESPACE IN CORRECT POSITION";
                        throw new IllegalArgumentException();
                    } else {
                        i++;
                    }
                }
                if (i == 13) { // check for proper colon
                    if (depart.charAt(i) != ':') {
                        error = "FORMAT IS WRONG. CANNOT FIND COLON IN CORRECT POSITION";
                        throw new IllegalArgumentException();
                    } else {
                        i++;
                    }
                }
                if (!Character.isDigit(depart.charAt(i))) { // check if digit
                    error = "ILLEGAL CHARACTER, IT MUST BE A DIGIT.";
                    throw new IllegalArgumentException();
                }
            }
            int year = Integer.parseInt(depart.substring(6,10));
            int month = Integer.parseInt(depart.substring(0,2));
            int day = Integer.parseInt(depart.substring(3,5));
            int hour = Integer.parseInt(depart.substring(11,13));
            int min = Integer.parseInt(depart.substring(14,16));
            this.depart = new Date(year, month, day, hour, min); // initialize

        } catch (IllegalArgumentException e) {
            System.err.println("The DEPART DATE AND TIME you have inputted is not valid. " + error);
            throw new IllegalArgumentException();
        }

        //DEST
        try {
            if (dest.length() < 3) { // if dest has no or too few letters
                error = "THE LENGTH IS TOO SMALL.";
                throw new IllegalArgumentException("dest: airport three-letter code is too SMALL");
            }
            if (dest.length() > 3) { // if dest has too many letters
                error = "THE LENGTH IS TOO BIG.";
                throw new IllegalArgumentException("dest: airport three-letter code is too BIG");
            }
            if (Pattern.compile("[^a-zA-Z]").matcher(dest).find()) { // if dest contains something other than a letter
                error = "IT  CONTAINS AN ILLEGAL CHARACTER.";
                throw new IllegalArgumentException("dest: airport three-letter code contains invalid character, IT MUST BE LETTERS");
            }
            this.dest = dest; // initialize
        } catch (IllegalArgumentException e) {
            System.err.print("The DEST you have inputted is not valid. " + error);
            throw new IllegalArgumentException();
        }

        //ARRIVE
        try {
            if (arrive.length() < 16) { // if depart is smaller than expected
                error = "THE LENGTH IS TOO SMALL.";
                throw new IllegalArgumentException("arrive: wrong format: too small (mm/dd/yyyy hh:mm)");

            }
            if (arrive.length() > 16) { // if depart is bigger than expected
                error = "THE LENGTH IS TOO BIG.";
                throw new IllegalArgumentException("arrive: wrong format: too big (mm/dd/yyyy hh:mm)");
            }
            if (arrive.contains("[a-zA-Z]+")) { // if depart contains letters
                error = "IT SHOULDN'T CONTAIN LETTERS.";
                throw new IllegalArgumentException("arrive: wrong format: contains letters (##/##/#### ##:##)");
            }
            for (int i = 0; i <= 15; i++) { // check validity of certain strings
                if (i == 2 || i == 5) { // check for proper backslash
                    if (arrive.charAt(i) != '/') {
                        error = "FORMAT IS WRONG. CANNOT FIND BACKSLASH IN CORRECT POSITION";
                        throw new IllegalArgumentException("arrive: wrong format: no slash (mm/dd/yyyy hh:mm)");
                    } else {
                        i++;
                    }
                }

                if (i == 10) { // check for proper whitespace
                    if (!Character.isWhitespace(arrive.charAt(i))) {
                        error = "FORMAT IS WRONG. CANNOT FIND WHITESPACE IN CORRECT POSITION";
                        throw new IllegalArgumentException("arrive: wrong format: no white space (mm/dd/yyyy hh:mm)");
                    } else {
                        i++;
                    }
                }
                if (i == 13) { // check for proper colon
                    if (arrive.charAt(i) != ':') {
                        error = "FORMAT IS WRONG. CANNOT FIND COLON IN CORRECT POSITION";
                        throw new IllegalArgumentException("arrive: wrong format: no colon (mm/dd/yyyy hh:mm)");
                    } else {
                        i++;
                    }
                }
                if (!Character.isDigit(arrive.charAt(i))) { // check if digit
                    error = "ILLEGAL CHARACTER, IT MUST BE A DIGIT.";
                    throw new IllegalArgumentException("arrive: wrong format not a digit or incorrect placement (##/##/#### ##:##)");
                }
            }
            int year = Integer.parseInt(depart.substring(6,10));
            int month = Integer.parseInt(depart.substring(0,2));
            int day = Integer.parseInt(depart.substring(3,5));
            int hour = Integer.parseInt(depart.substring(11,13));
            int min = Integer.parseInt(depart.substring(14,16));
            Date check = new Date(year, month, day, hour, min);
            if(!check.after(this.depart)) {
                error = "ARRIVAL DATE AND TIME CANNOT BE BEFORE DEPART DATE AND TIME.";
                throw new IllegalArgumentException();
            }
            this.arrive = check; // initialize
        } catch (IllegalArgumentException e) {
            System.err.println("The ARRIVE DATE AND TIME you have inputted is not valid. " + error);
            throw new IllegalArgumentException();
        }

    }

    /**
     * returns the flightNumber of this class
     * @return
     */
    @Override
    public int getNumber() {
        //return 42;
        return this.flightNumber;
    }

    /**
     * returns the src of this class
     * @return
     */
    @Override
    public String getSource() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        return this.src;
    }

    /**
     * returns the depart of this class
     * @return
     */
    @Override
    public String getDepartureString() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        DateFormat dateFormat;
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        return dateFormat.format(this.depart);
    }

    /**
     * returns the dest of this class
     * @return
     */
    @Override
    public String getDestination() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        return this.dest;
    }

    /**
     * returns the arrive of this class
     * @return
     */
    @Override
    public String getArrivalString() {
        //throw new UnsupportedOperationException("This method is not implemented yet");
        DateFormat dateFormat;
        dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        return dateFormat.format(this.arrive);
    }

    @Override
    public int compareTo(Flight flight) {
        int i = this.src.compareTo(flight.src);
        if (i != 0) return i;

        return Long.compare(depart.getTime(), flight.depart.getTime());
    }
}