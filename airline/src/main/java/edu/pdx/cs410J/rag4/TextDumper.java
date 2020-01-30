package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TextDumper implements AirlineDumper {
    private final String content;

    public TextDumper(String content){
        if(!content.matches("([a-z]|[A-Z]|[0-9]|[.])*")){
            throw new IllegalArgumentException("File name contains NON ALPHANUMERICAL char. Can't Dump.");
        }
        this.content = content;
    }

    @Override
    public void dump(AbstractAirline airline) throws IOException {
        String airlineName = airline.getName();
        String line;
        ArrayList<AbstractFlight> flightArray = (ArrayList<AbstractFlight>) airline.getFlights();

        try{
            File file = new File(this.content);
            if (file.exists() == false){
                FileWriter toWrite = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(toWrite);
                file.createNewFile();
                System.out.println("\n\nFILE DOESN'T EXIST, CREATING NEW FILE, THEN ATTEMPTING TO DUMP...");
                writer.write(airlineName);
                writer.newLine();
                for(int i = 0; i < flightArray.size(); i++) {
                    writer.write(flightArray.get(i).getNumber() + " " + flightArray.get(i).getSource() + " "
                            + flightArray.get(i).getDepartureString() + " " + flightArray.get(i).getDestination() + " "
                            + flightArray.get(i).getArrivalString() + " ");
                    writer.newLine();
                }
                writer.close();
                toWrite.close();
                System.out.println("SUCCESS: FILE CREATED -- Airline Contents DUMPED.\n\n");
            }else{
                FileWriter toWrite = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(toWrite);

                System.out.println("\n\nFILE EXISTS, ATTEMPTING TO DUMP...");

                writer.write(airlineName);
                writer.newLine();
                for(int i = 0; i < flightArray.size(); i++) {
                    writer.write(flightArray.get(i).getNumber() + " " + flightArray.get(i).getSource() + " "
                            + flightArray.get(i).getDepartureString() + " " + flightArray.get(i).getDestination() + " "
                            + flightArray.get(i).getArrivalString() + " ");
                    writer.newLine();
                }
                writer.close();
                toWrite.close();
                System.out.println("SUCCESS: Airline Contents DUMPED.\n\n");
            }
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }

    public void checkIfEqual(String airlineUser, String airlineText){
        if (!airlineUser.equals(airlineText)){
            throw new IllegalArgumentException(" YOU CANNOT HAVE MORE THAN ONE AIRLINES ON THIS FILE");
        }
    }

}
