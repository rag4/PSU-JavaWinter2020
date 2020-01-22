package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

public class Project1 {

  //MAIN FUNCTION
  public static void main(String[] args) {

    if(args.length == 0) { // if there are no command line arguments, print error message, print command line interface, then exit
      System.err.println("Missing command line arguments");
      commandLineInterface();
      System.exit(1);
    }

    if(args[0].equals("-README") || args[1].equals("-README")){ // if there exists a -README option as the first command line argument, print out the README function, then exit
      printReadMe();
      System.exit(1);
    }

    int printOption = 0; // printOption flag should stay 0 if -print option is NOT the first command line argument

    if(args[0].equals("-print")){ // printOption flag changes to 1 if -print option exist as the first command line argument
      printOption = 1;
    }

    if(args.length < 6 && printOption == 0){ // if far too LESS arguments in command line (w/o -print), print error message, print command line interface, then exit
      System.err.println("Missing command line arguments");
      commandLineInterface();
      System.exit(1);
    }
    if(args.length > 6 && printOption == 0){ // if far too MANY arguments in command line (w/o -print), print error message, print command line interface, then exit
      System.err.println("Too many command line arguments");
      commandLineInterface();
      System.exit(1);
    }
    if(args.length < 7 && printOption == 1){ // if far too LESS arguments in command line (w/ -print), print error message, print command line interface, then exit
      System.err.println("Missing command line arguments");
      commandLineInterface();
      System.exit(1);
    }
    if(args.length > 7 && printOption == 1){ // if far too MANY arguments in command line (w/ -print), print error message, print command line interface, then exit
      System.err.println("Too many command line arguments");
      commandLineInterface();
      System.exit(1);
    }

    if (printOption == 0) { // if -print exists, create a new flight class using the respective command line arguments, create a new airline class using the respective command line arguments, add flight into airline's flightArray
      Flight flight = new Flight(Integer.parseInt(args[1]), args[2], args[3], args[4], args[5]);
      ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
      Airline airline = new Airline(args[0], flightArray);
      airline.addFlight(flight);
    }

    if (printOption == 1) { // if -print exists, create a new flight class using the respective command line arguments, create a new airline class using the respective command line arguments, add flight into airline's flightArray
      Flight flight = new Flight(Integer.parseInt(args[2]), args[3], args[4], args[5], args[6]);
      ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
      Airline airline = new Airline(args[1], flightArray);
      airline.addFlight(flight);
      System.out.println("AIRLINE:" + airline.getName());
      System.out.println(flight.toString()); //since -print option exists, print the flight description
    }

    /*for (String arg : args) {
      System.out.println(arg);
    }*/

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
    System.out.println("PROJECT 1: DEESIGNING AN AIRLINE APPLICATION\n" +
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
