package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight extends AbstractFlight {

    private final int flightNumber; // the flight number
    private final String src; // the source airport three-letter code
    private final String depart; // the departure date and time
    private final String dest; // the destination airport three-letter code
    private final String arrive; // the arrival date and time

    /**
     * Class implementation for Flight / Constructor
     * @param flightNumber
     * @param src
     * @param depart
     * @param dest
     * @param arrive
     */
    public Flight(int flightNumber, String src, String depart, String dest, String arrive){
        this.flightNumber = flightNumber;

        //SRC
        if (src.length() < 3) { // if src has no or too few letters
            throw new IllegalArgumentException("src: airport airport three-letter code is too SMALL");
        }
        if (src.length() > 3) { // if src has too many letters
            throw new IllegalArgumentException("src: airport three-letter code is too BIG");
        }
        if (Pattern.compile("[^a-zA-Z]").matcher(src).find()){ // if src contains something other than a letter
            throw new IllegalArgumentException("src: airport three-letter code contains invalid character, IT MUST BE LETTERS");
        }
        this.src = src; // initialize

        //DEPART
        if (depart.length() < 16){ // if depart is smaller than expected
            throw new IllegalArgumentException("depart: wrong format: too small (##/##/#### ##:##)");
        }
        if (depart.length() > 16){ // if depart is bigger than expected
            throw new IllegalArgumentException("depart: wrong format: too big (##/##/#### ##:##)");
        }
        if (depart.contains("[a-zA-Z]+")){ // if depart contains letters
            throw new IllegalArgumentException("depart: wrong format: contains letters (##/##/#### ##:##)");
        }
        for (int i = 0; i <= 15; i++){ // check validity of certain strings
            if (i == 2 || i == 5){ // check for proper backslash
                if (depart.charAt(i) != '/'){
                    throw new IllegalArgumentException("depart: wrong format: no slash (##/##/#### ##:##)");
                }
                else{
                    i++;
                }
            }
            if (i == 10){ // check for proper whitespace
                if (!Character.isWhitespace(depart.charAt(i))){
                    throw new IllegalArgumentException("depart: wrong format: no white space (##/##/#### ##:##)");
                }
                else{
                    i++;
                }
            }
            if (i == 13){ // check for proper colon
                if (depart.charAt(i) != ':'){
                    throw new IllegalArgumentException("depart: wrong format: no colon (##/##/#### ##:##)");
                }
                else{
                    i++;
                }
            }
            if (!Character.isDigit(depart.charAt(i))) { // check if digit
                throw new IllegalArgumentException("depart: wrong format: not a digit (##/##/#### ##:##)");
            }
        }
        this.depart = depart; // initialize

        //DEST
        if (dest.length() < 3) { // if dest has no or too few letters
            throw new IllegalArgumentException("dest: airport three-letter code is too SMALL");
        }
        if (dest.length() > 3) { // if dest has too many letters
            throw new IllegalArgumentException("dest: airport three-letter code is too BIG");
        }
        if (Pattern.compile("[^a-zA-Z]").matcher(dest).find()){ // if dest contains something other than a letter
            throw new IllegalArgumentException("dest: airport three-letter code contains invalid character, IT MUST BE LETTERS");
        }
        this.dest = dest; // initialize

        //ARRIVE
        if (arrive.length() < 16){ // if depart is smaller than expected
            throw new IllegalArgumentException("arrive: wrong format: too small (mm/dd/yyyy hh:mm)");

        }
        if (arrive.length() > 16){ // if depart is bigger than expected
            throw new IllegalArgumentException("arrive: wrong format: too big (mm/dd/yyyy hh:mm)");
        }
        if (arrive.contains("[a-zA-Z]+")){ // if depart contains letters
            throw new IllegalArgumentException("arrive: wrong format: contains letters (mm/dd/yyyy hh:mm)");
        }
        for (int i = 0; i <= 15; i++) { // check validity of certain strings
            if (i == 2 || i == 5) { // check for proper backslash
                if (arrive.charAt(i) != '/') {
                    throw new IllegalArgumentException("arrive: wrong format: no slash (mm/dd/yyyy hh:mm)");
                } else {
                    i++;
                }
            }

            if (i == 10) { // check for proper whitespace
                if (!Character.isWhitespace(arrive.charAt(i))) {
                    throw new IllegalArgumentException("arrive: wrong format: no white space (mm/dd/yyyy hh:mm)");
                } else {
                    i++;
                }
            }
            if (i == 13) { // check for proper colon
                if (arrive.charAt(i) != ':') {
                    throw new IllegalArgumentException("arrive: wrong format: no colon (mm/dd/yyyy hh:mm)");
                } else {
                    i++;
                }
            }
            if (!Character.isDigit(arrive.charAt(i))) { // check if digit
                throw new IllegalArgumentException("arrive: wrong format no digit (mm/dd/yyyy hh:mm)");
            }
        }
        this.arrive = arrive; // initialize
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
        return this.depart;
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
        return this.arrive;
    }

}
