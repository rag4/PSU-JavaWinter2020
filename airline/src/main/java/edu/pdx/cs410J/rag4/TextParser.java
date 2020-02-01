package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.ArrayList;

/***
 * TEXT PARSER CLASS THAT IMPLEMENTS AIRLINEPARSER CLASS
 */
public class TextParser implements AirlineParser {

    private final String content; // file's name

    /***
     * Constructor
     * @param content
     */
    public TextParser(String content) {
        // make sure it doesn't contain special characters
       /* if(!content.matches("([a-z]|[A-Z]|[0-9]|[.])*")){
            throw new IllegalArgumentException("File name contains NON ALPHANUMERICAL char. Can't Parse.");
        }*/

        // make sure to parse only if the file exists
        /*File check = new File(content);
        try {
            if (!check.exists()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("FILE DOES NOT EXIST.");
        }*/

        this.content = content;
    }

    /***
     * parses contents of a file, and returns it as an airline
     * @return
     * @throws ParserException
     */
    @Override
    public AbstractAirline parse() throws ParserException {
        String[] newCommandArgs; // parse words into text file, and classify them into this String array

        try {
            FileReader file = new FileReader(this.content);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine(); // get first line, which should be the Airline name

            ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
            Airline airline = new Airline(line, flightArray);
            line = reader.readLine();

            //in this while loop, go through each flight, parse into string array, then append information one at a time into airline's flightArray
            while (line != null) {
                newCommandArgs = line.split(" ");


                // if flight number contains anything other than numbers, throw an illegal argument exception
                try {
                    String flightNumberCheck = newCommandArgs[0]; //easy string variable to get the flight number
                    if (!flightNumberCheck.matches("[0-9]+")) {
                        throw new IllegalArgumentException();
                    }
                    Flight flight = new Flight(Integer.parseInt(newCommandArgs[0]), newCommandArgs[1], newCommandArgs[2] + " " + newCommandArgs[3],
                            newCommandArgs[4], newCommandArgs[5] + " " + newCommandArgs[6]);
                    airline.addFlight(flight); // add this newly created flight with initialized values into airline
                } catch (IllegalArgumentException e){
                    throw new IllegalArgumentException();
                }
                line = reader.readLine();
            }
            reader.close();
            return airline;
        } catch (IOException e) {
            System.err.println("FILE DOES NOT EXIST. CANNOT PARSE.");
            throw new IllegalArgumentException();
        }
    }

    /***
     * checks if two string are equal, in this case, check if file's airline equals to the new airline
     * @param airlineUser
     * @param airlineText
     */
    public void checkIfEqual(String airlineUser, String airlineText) throws IllegalArgumentException {
        try {
            if (!airlineUser.equals(airlineText)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.err.println("AIRLINE INPUTTED AND AIRLINE IN TEXT FILE DO NOT MATCH");
            throw new IllegalArgumentException();
        }
    }
}
