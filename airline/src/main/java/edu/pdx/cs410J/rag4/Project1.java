package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

public class Project1 {

  //MAIN
  public static void main(String[] args) {

    if(args.length == 0) {
      System.err.println("Missing command line arguments");
      commandLineInterface();
      System.exit(1);
    }

    int printOption = 0;
    int readmeOption = 0;
    if(args[0].equals("-print")){
      printOption = 1;
    }
    if(args[0].equals("-README")){
      readmeOption = 1;
    }

    if(args.length < 6 && printOption == 0){
      System.err.println("Missing command line arguments");
      commandLineInterface();
      System.exit(1);
    }
    if(args.length > 6 && printOption == 0){
      System.err.println("Too many command line arguments");
      commandLineInterface();
      System.exit(1);
    }
    if(args.length < 7 && printOption == 1){
      System.err.println("Missing command line arguments");
      commandLineInterface();
      System.exit(1);
    }
    if(args.length > 7 && printOption == 1){
      System.err.println("Too many command line arguments");
      commandLineInterface();
      System.exit(1);
    }

    if (printOption == 0 && readmeOption == 0) {
      Flight flight = new Flight(Integer.parseInt(args[1]), args[2], args[3], args[4], args[5]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
      ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
      Airline airline = new Airline(args[0], flightArray);
      airline.addFlight(flight);
    }

    if (printOption == 1 || readmeOption == 1) {
      Flight flight = new Flight(Integer.parseInt(args[2]), args[3], args[4], args[5], args[6]);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
      ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
      Airline airline = new Airline(args[1], flightArray);
      airline.addFlight(flight);
      if (printOption == 1){
        System.out.println(flight.toString());
        System.out.println();
      }
      if (readmeOption == 1){

      }
    }

    /*for (String arg : args) {
      System.out.println(arg);
    }*/
    System.exit(1);
  }

  //COMMAND LINE INTERFACE
  public static void commandLineInterface(){
    System.out.println("usage: java edu.pdx.cs410J.rag4.Project1 [options] <args>\n" +
            "\targs are (in this order):\n" +
            "\t\tairline The name of the airline\n" +
            "\t\tflightNumber The flight number\n" +
            "\t\tsrc Three-letter code of departure airport\n" +
            "\t\tdepart Departure date and time (24-hour time)\n" +
            "\t\tdest Three-letter code of arrival airport\n" +
            "\t\tarrive Arrival date and time (24-hour time)\n" +
            "\toptions are (options may appear in any order):\n" +
            "\t\t-print Prints a description of the new flight\n" +
            "\t\t-README Prints a README for this project and exits\n" +
            "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n");
  }



}
