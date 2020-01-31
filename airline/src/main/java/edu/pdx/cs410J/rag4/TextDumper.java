package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/***
 *  TEXT DUMPER CLASS THAT IMPLEMENTS THE AIRLINEDUMPER CLASS
 */
public class TextDumper implements AirlineDumper {
    private final String content; // file's name

    /***
     * Constructor
     * @param content
     */
    public TextDumper(String content){
        // make sure it doesn't contain special characters
        /*if(!content.matches("([a-z]|[A-Z]|[0-9]|[.][/])*")){
            throw new IllegalArgumentException("File name contains NON ALPHANUMERICAL char. Can't Dump.");
        }*/
        this.content = content;
    }

    /***
     * dumps contents of passed in airline into a text file
     * @param airline
     * @throws IOException
     */
    @Override
    public void dump(AbstractAirline airline) throws IOException {
        String airlineName = airline.getName(); //get the airlineName to put in as the first line of the text filie
        String line;
        ArrayList<AbstractFlight> flightArray = (ArrayList<AbstractFlight>) airline.getFlights();

        try{
            File file = new File(this.content);
            //if there is no file, create a brand new file and dump to it
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
            //if file exists, update lists of flights to add a brand new flight
            }else{
                FileWriter toWrite = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(toWrite);

                System.out.println("\n\nFILE EXISTS, ATTEMPTING TO DUMP...");

                writer.write(airlineName);
                writer.newLine();

                //write down all the flights of the airline, line by line
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
            File file = new File(this.content);
            //if there is no file, create a brand new file and dump to it
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
        }
    }
}
