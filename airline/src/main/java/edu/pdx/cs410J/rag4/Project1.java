package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

public class Project1 {

  //MAIN FUNCTION
  public static void main(String[] args) {

    int printFlag = 0;
    int departFlag = 0;
    ArrayList<String> scanAirlineFlight = new ArrayList<String>();

    if(args.length <= 0) {
      System.err.println("There are missing command line arguments.\n");
      commandLineInterface();
    }
    for (int i = 0; i < args.length; i++){
      if (args[i].equals("-README")){
        printReadMe();
        System.exit(1);
      }
    }
    for (int i = 0; i < args.length; i++){
      if (args[i].equals("-print")){
        printFlag = 1;
        continue;
      }
      if (args[i].indexOf('/') == 2 || args[i].indexOf('/') == 1) {
        if(args[i+1].equals("-print")){
          scanAirlineFlight.add(args[i] + " " + args[i + 2]);
          printFlag = 1;
          if (i + 3 == args.length) {
            break;
          }
          i += 3;
        }
        else {
          scanAirlineFlight.add(args[i] + " " + args[i + 1]);
          if (i + 2 == args.length) {
            break;
          }
          i += 2;
        }
      }
      scanAirlineFlight.add(args[i]);
    }

    for (int i = 0; i < scanAirlineFlight.size(); i++){
      System.out.println("Content " + i + ": " + scanAirlineFlight.get(i));
    }

    if (scanAirlineFlight.size() < 6){
      System.err.println("You have too FEW command line arguments. \n");
      commandLineInterface();
      System.exit(1);
    }
    if (scanAirlineFlight.size() > 6){
      System.err.println("You have far too MANY command line arguments. \n");
      commandLineInterface();
      System.exit(1);
    }

    ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
    Airline airline = new Airline(scanAirlineFlight.get(0), flightArray);
    Flight flight = new Flight(Integer.parseInt(scanAirlineFlight.get(1)), scanAirlineFlight.get(2), scanAirlineFlight.get(3), scanAirlineFlight.get(4), scanAirlineFlight.get(5));
    airline.addFlight(flight);
    if (printFlag == 1) {
      System.out.println("AIRLINE:" + airline.getName());
      System.out.println(flight.toString()); //since -print option exists, print the flight description
    }
    System.exit(1);
  }

  //COMMAND LINE INTERFACE

  /**
   * prints out the command line interface, should be used when improper usage of the command line arguments in the main function occurs
   */
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

  /**
   * prints out the README, should be used when -README option exists as the first command line argument in the main function
   */
  public static void printReadMe(){
    System.out.println("PROJECT 1: DESIGNING AN AIRLINE APPLICATION\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 22, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 1, my objective is to extend the AbstractAirline class with the class Airline, \n" +
            "extend the AbstractFlight class with the class flight, implement assigned functionality for the main function \n" +
            "within Project1.java, and to create test suites for all of these classes/files. \n\n" +
            "In the Airline class, aside from error handling, I implemented a constructor to consider the airline's name, and a \n" +
            "list of available flights the airline contains. In the Flight class, aside from error handling, I implemented a constructor\n" +
            "to consider the flight's unique number, three-letter source code, departure date and time, three-letter destination code, \n" +
            "and arrival date and time. Within the main function, the purpose of this function is to create an airline and flight to add\n" +
            "to the airline using the values of the user's input command line arguments. The user also has the option to print the descriptions\n" +
            "of the flight, as well as to be able to view this README.");
  }



}
