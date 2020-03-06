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
                this.src = src;
            } catch (IllegalArgumentException e) {
                System.err.print("The SRC you have inputted is not valid. " + error);
                throw new IllegalArgumentException();
            }

            try {
                // insert a 0 if date format: m/dd/yyyy
                if(depart.charAt(1) == ('/')){
                    depart = "0" + depart;
                }
                // insert a 0 if date format: mm/d/yyyy OR m/d/yyyy --> mm/d/yyyy
                if(depart.charAt(4) == ('/')){
                    depart =  depart.substring(0,3) + "0" + (depart.substring(3));
                }
                // insert a 0 if time format: h:mm
                if(depart.charAt(12) == ':'){
                    depart = depart.substring(0,11) + "0" + (depart.substring(11));
                }
                if(Integer.parseInt(depart.substring(11,13)) > 12){
                    error = "PLEASE DO NOT PUT 24-HOUR TIME.";
                    throw new IllegalArgumentException();
                }

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
                this.departFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                this.depart = departFormat.parse(depart);
            } catch ( IllegalArgumentException e) {
                System.err.println("The DEPART DATE AND TIME you have inputted is not valid. " + error);
                throw new IllegalArgumentException();
            }

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
                this.dest = dest;
            } catch (IllegalArgumentException e) {
                System.err.print("The DEST you have inputted is not valid. " + error);
                throw new IllegalArgumentException();
            }

            try {
                // insert a 0 if date format: m/dd/yyyy
                if(arrive.charAt(1) == ('/')){
                    arrive = "0" + arrive;
                }
                // insert a 0 if date format: mm/d/yyyy OR m/d/yyyy --> mm/d/yyyy
                if(arrive.charAt(4) == ('/')){
                    arrive =  arrive.substring(0,3) + "0" + (arrive.substring(3));
                }
                // insert a 0 if time format: h:mm
                if(arrive.charAt(12) == ':'){
                    arrive = arrive.substring(0,11) + "0" + (arrive.substring(11));
                }

                if(Integer.parseInt(arrive.substring(11,13)) > 12){
                    error = "PLEASE DO NOT PUT 24-HOUR TIME.";
                    throw new IllegalArgumentException("arrive: time is 24-hour");
                }
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
                this.arriveFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
                this.arrive = arriveFormat.parse(arrive);
                if(!this.arrive.after(this.depart)) {
                    error = "ARRIVAL DATE AND TIME CANNOT BE BEFORE DEPART DATE AND TIME.";
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.err.println("The ARRIVE DATE AND TIME you have inputted is not valid. " + error);
                throw new IllegalArgumentException();
            }

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