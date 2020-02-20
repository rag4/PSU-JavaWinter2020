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

public class Flight extends AbstractFlight implements Comparable<Flight>{

    private int flightNumber; // the flight number
    private String src = ""; // the source airport three-letter code
    private DateFormat departFormat; // the departure date and time
    private Date depart;
    private String dest = ""; // the destination airport three-letter code
    private DateFormat arriveFormat; // the arrival date and time
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
            if(AirportNames.getName(src.toUpperCase()) == null){
                error = "THE SOURCE CODE DOES NOT CORRESPOND TO AN EXISTING AIRPORT. ";
                throw new IllegalArgumentException();
            }

            this.src = src; // initialize
        } catch (IllegalArgumentException e) {
            System.err.print("The SRC you have inputted is not valid. " + error);
            throw new IllegalArgumentException();
        }

        //DEPART
        try {

            if (depart.length() < 19) { // if depart is smaller than expected
                error = "THE LENGTH IS TOO SMALL.";
                throw new IllegalArgumentException();
            }
            if (depart.length() > 19) { // if depart is bigger than expected
                error = "THE LENGTH IS TOO BIG.";
                throw new IllegalArgumentException();
            }
            if (depart.substring(0,15).contains("[a-zA-Z]+")) { // if depart contains letters
                error = "IT SHOULDN'T CONTAIN LETTERS.";
                throw new IllegalArgumentException();
            }
            for (int i = 0; i <= 18; i++) { // check validity of certain strings
                if (i == 2 || i == 5) { // check for proper backslash
                    if (depart.charAt(i) != '/') {
                        error = "FORMAT IS WRONG. CANNOT FIND BACKSLASH IN CORRECT POSITION";
                        throw new IllegalArgumentException();
                    } else {
                        i++;
                    }
                }
                if (i == 10 || i == 16) { // check for proper whitespace
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
                if (i == 17) {
                    if (depart.toUpperCase().charAt(i) != 'A' && depart.toUpperCase().charAt(i) != 'P'){
                        error = "FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED.";
                        throw new IllegalArgumentException();
                    } else {
                        i++;
                    }
                }
                if (i == 18) {
                    if (depart.toUpperCase().charAt(i) != 'M'){
                        error = "FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED.";
                        throw new IllegalArgumentException();
                    } else {
                        break;
                    }
                }
                if (!Character.isDigit(depart.charAt(i))) { // check if digit
                    error = "ILLEGAL CHARACTER, IT MUST BE A DIGIT.";
                    throw new IllegalArgumentException();
                }
            }
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            Date date = format.parse(depart);
            this.depart = date;
            this.departFormat = format; // initialize

        } catch (IllegalArgumentException | ParseException e) {
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
            if(AirportNames.getName(dest.toUpperCase()) == null){
                error = "THE SOURCE CODE DOES NOT CORRESPOND TO AN EXISTING AIRPORT. ";
                throw new IllegalArgumentException();
            }
            this.dest = dest; // initialize
        } catch (IllegalArgumentException e) {
            System.err.print("The DEST you have inputted is not valid. " + error);
            throw new IllegalArgumentException();
        }

        //ARRIVE
        try {
            if (arrive.length() < 19) { // if depart is smaller than expected
                error = "THE LENGTH IS TOO SMALL.";
                throw new IllegalArgumentException("arrive: wrong format: too small (mm/dd/yyyy hh:mm)");

            }
            if (arrive.length() > 19) { // if depart is bigger than expected
                error = "THE LENGTH IS TOO BIG.";
                throw new IllegalArgumentException("arrive: wrong format: too big (mm/dd/yyyy hh:mm)");
            }
            if (arrive.substring(0,15).contains("[a-zA-Z]+")) { // if depart contains letters
                error = "IT SHOULDN'T CONTAIN LETTERS.";
                throw new IllegalArgumentException("arrive: wrong format: contains letters (##/##/#### ##:##)");
            }
            for (int i = 0; i <= 18; i++) { // check validity of certain strings
                if (i == 2 || i == 5) { // check for proper backslash
                    if (arrive.charAt(i) != '/') {
                        error = "FORMAT IS WRONG. CANNOT FIND BACKSLASH IN CORRECT POSITION";
                        throw new IllegalArgumentException("arrive: wrong format: no slash (mm/dd/yyyy hh:mm)");
                    } else {
                        i++;
                    }
                }

                if (i == 10 || i == 16) { // check for proper whitespace
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
                if (i == 17) {
                    if (arrive.toUpperCase().charAt(i) != 'A' && arrive.toUpperCase().charAt(i) != 'P'){
                        error = "FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED.";
                        throw new IllegalArgumentException();
                    } else {
                        i++;
                    }
                }
                if (i == 18) {
                    if (arrive.toUpperCase().charAt(i) != 'M'){
                        error = "FORMAT IS WRONG. YOUR AM/PM ARE MALFORMATTED.";
                        throw new IllegalArgumentException();
                    } else {
                        break;
                    }
                }
                if (!Character.isDigit(arrive.charAt(i))) { // check if digit
                    error = "ILLEGAL CHARACTER, IT MUST BE A DIGIT.";
                    throw new IllegalArgumentException("arrive: wrong format not a digit or incorrect placement (##/##/#### ##:##)");
                }
            }
            DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            Date departDate = format.parse(depart);
            Date arriveDate = format.parse(arrive);
            if(!arriveDate.after(departDate)) {
                error = "ARRIVAL DATE AND TIME CANNOT BE BEFORE DEPART DATE AND TIME.";
                throw new IllegalArgumentException();
            }
            this.arrive = arriveDate;
            this.arriveFormat = format; // initialize
        } catch (IllegalArgumentException | ParseException e) {
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

    public String getLongDeparture() {
        return departFormat.format(this.depart);
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

    public String getLongArrival() {
        return arriveFormat.format(this.arrive);
    }

    @Override
    public int compareTo(Flight flight) {
        int i = this.src.compareToIgnoreCase(flight.src);
        if (i != 0) return i;
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