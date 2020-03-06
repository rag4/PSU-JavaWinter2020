package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineDumper;
import edu.pdx.cs410J.AirportNames;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PrettyPrinter implements AirlineDumper {

    @Override
    public void dump(AbstractAirline airline) throws IOException {
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

    public void dumpSearch(Airline airline, String src, String dest) {
        ArrayList<Flight> flightArray  = (ArrayList<Flight>) airline.getFlights();
        int found = 0;
        System.out.println("\n\n********************************************************************************************\n");
        System.out.println("Welcome to our catalogue viewer for " + airline.getName() + " Airlines. \n");
        for(Flight f : flightArray){

            if (f.getSource().equals(src) && f.getDestination().equals(dest)) {
                String departureDate = f.getDepartureString().replace(",", "");
                String arrivalDate = f.getArrivalString().replace(",", "");
                System.out.println("FLIGHT NUMBER " + f.getNumber() + ":\n" +
                        "\tYou are departing from airport " + f.getSource() + " in " + f.getSRCName() + " and your flight departs at: " + departureDate + "\n" +
                        "\tYou will arrive at airport " + f.getDestination() + " in " + f.getDESTName() + " and you will arrive at: " + arrivalDate + "\n" +
                        "\tYour flight duration will take " + f.getDifference() + " minutes.\n");
                found = 1;
            }
        }
        System.out.println("********************************************************************************************\n\n");
        if (found == 0) {
            System.out.println("We could not find any results of direct flights from " + AirportNames.getName(src) +
                    " to " + AirportNames.getName(src));
        }
    }
}
