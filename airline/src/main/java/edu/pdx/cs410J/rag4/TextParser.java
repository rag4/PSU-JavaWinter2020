package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;

import java.io.*;
import java.util.ArrayList;

public class TextParser implements AirlineParser {
    private final String content;

    public TextParser(String content){

        if(!content.matches("([a-z]|[A-Z]|[0-9]|[.])*")){
            throw new IllegalArgumentException("File name contains NON ALPHANUMERICAL char. Can't Parse.");
        }
        File check = new File(content);
        if (!check.exists()){
            throw new IllegalArgumentException("FILE DOES NOT EXIST.");
        }
        this.content = content;
    }


   @Override
    public AbstractAirline parse() throws ParserException {
        String [] newCommandArgs;

        try {
            FileReader file = new FileReader(this.content);
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();

            ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>(); // new Abstract FLight Array List
            Airline airline = new Airline(line, flightArray); // new Airline object
            line = reader.readLine();
            //System.out.println("AIRLINE: " + airline.getName());

            while (line!=null){
                newCommandArgs = line.split(" ");

                Flight flight = new Flight(Integer.parseInt(newCommandArgs[0]), newCommandArgs[1], newCommandArgs[2] + " " + newCommandArgs[3],
                        newCommandArgs[4], newCommandArgs[5] + " " + newCommandArgs[6]);
                airline.addFlight(flight); // add this newly created flight with initialized values into airline

                line = reader.readLine();
            }
            reader.close();
            return airline;
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void checkIfEqual(String airlineUser, String airlineText){
        if (!airlineUser.equals(airlineText)){
            throw new IllegalArgumentException(" YOU CANNOT HAVE MORE THAN ONE AIRLINES ON THIS FILE");
        }
    }
}
