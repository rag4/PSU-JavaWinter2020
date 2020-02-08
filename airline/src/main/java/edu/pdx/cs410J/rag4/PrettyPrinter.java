package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrettyPrinter implements AirlineDumper {

    private final String content;

    public PrettyPrinter(String content) {
        this.content = content;
    }


    @Override
    public void dump(AbstractAirline airline) throws IOException {
        String line;
        ArrayList<Flight> flightArray  = (ArrayList<Flight>) airline.getFlights();

        try{
            File file = new File(this.content);
            //if there is no file, create a brand new file and dump to it
            if (!file.exists()){
                FileWriter toWrite = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(toWrite);
                file.createNewFile();
                System.out.println("\n\nFILE DOESN'T EXIST, CREATING NEW FILE, THEN ATTEMPTING TO PRETTY PRINT...");
                writer.write("\n\n********************************************************************************************\n");
                writer.newLine();
                writer.write("Welcome to our catalogue viewer for " + airline.getName() + " Airlines. \n");
                writer.newLine();
                for(Flight f : flightArray){
                    String departureDate = f.getDepartureString().replace(",", "");
                    String arrivalDate = f.getArrivalString().replace(",", "");
                    writer.write("FLIGHT NUMBER " + f.getNumber() + ":\n" +
                            "\tYou are departing from airport " + f.getSource() + " in " + f.getSRCName() + " and your flight departs at: " + departureDate + "\n" +
                            "\tYou will arrive at airport " + f.getDestination() + " in " + f.getDESTName() + " and you will arrive at: " + arrivalDate +"\n" +
                            "\tYour flight duration will take " + f.getDifference() + " minutes.\n");
                    writer.newLine();
                }
                writer.write("********************************************************************************************\n\n");
                writer.close();
                toWrite.close();
                System.out.println("SUCCESS: FILE CREATED -- Airline Contents PRETTY PRINTED.\n\n");
                //if file exists, update lists of flights to add a brand new flight
            }else{
                FileWriter toWrite = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(toWrite);

                System.out.println("\n\nFILE EXISTS, ATTEMPTING TO DUMP...");

                writer.write("\n\n********************************************************************************************\n");
                writer.newLine();
                writer.write("Welcome to our catalogue viewer for " + airline.getName() + " Airlines. \n");
                writer.newLine();

                //write down all the flights of the airline, line by line
                for(Flight f : flightArray){
                    String departureDate = f.getDepartureString().replace(",", "");
                    String arrivalDate = f.getArrivalString().replace(",", "");
                    writer.write("FLIGHT NUMBER " + f.getNumber() + ":\n" +
                            "\tYou are departing from airport " + f.getSource() + " in " + f.getSRCName() + " and your flight departs at: " + departureDate + "\n" +
                            "\tYou will arrive at airport " + f.getDestination() + " in " + f.getDESTName() + " and you will arrive at: " + arrivalDate +"\n" +
                            "\tYour flight duration will take " + f.getDifference() + " minutes.\n");
                    writer.newLine();
                }
                writer.write("********************************************************************************************\n\n");
                writer.close();
                toWrite.close();
                System.out.println("SUCCESS: Airline Contents PRETTY PRINTED.\n\n");
            }
        } catch (IOException e) {
            File file = new File(this.content);
            //if there is no file, create a brand new file and dump to it
            FileWriter toWrite = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(toWrite);
            file.createNewFile();
            System.out.println("\n\nFILE DOESN'T EXIST, CREATING NEW FILE, THEN ATTEMPTING TO PRETTY PRINT...");
            writer.write("\n\n********************************************************************************************\n");
            writer.newLine();
            writer.write("Welcome to our catalogue viewer for " + airline.getName() + " Airlines.");
            writer.newLine();
            for(Flight f : flightArray){
                String departureDate = f.getDepartureString().replace(",", "");
                String arrivalDate = f.getArrivalString().replace(",", "");
                writer.write("FLIGHT NUMBER " + f.getNumber() + ":\n" +
                        "\tYou are departing from airport " + f.getSource() + " in " + f.getSRCName() + " and your flight departs at: " + departureDate + "\n" +
                        "\tYou will arrive at airport " + f.getDestination() + " in " + f.getDESTName() + " and you will arrive at: " + arrivalDate +"\n" +
                        "\tYour flight duration will take " + f.getDifference() + " minutes.\n");
                writer.newLine();
            }
            writer.write("********************************************************************************************\n\n");
            writer.close();
            toWrite.close();
            System.out.println("SUCCESS: FILE CREATED -- Airline Contents PRETTY PRINTED.\n\n");
        }
    }

    public void dumpOut(AbstractAirline airline){
        ArrayList<Flight> flightArray  = (ArrayList<Flight>) airline.getFlights();
        System.out.println("\n\n********************************************************************************************\n");
        System.out.println("Welcome to our catalogue viewer for " + airline.getName() + " Airlines. \n");
        for(Flight f : flightArray){
            String departureDate = f.getDepartureString().replace(",", "");
            String arrivalDate = f.getArrivalString().replace(",", "");
            System.out.println("FLIGHT NUMBER " + f.getNumber() + ":\n" +
                    "\tYou are departing from airport " + f.getSource() + " in " + f.getSRCName() + " and your flight departs at: " + departureDate + "\n" +
                    "\tYou will arrive at airport " + f.getDestination() + " in " + f.getDESTName() + " and you will arrive at: " + arrivalDate +"\n" +
                    "\tYour flight duration will take " + f.getDifference() + " minutes.\n");
        }
        System.out.println("********************************************************************************************\n\n");
    }
}
