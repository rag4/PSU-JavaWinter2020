package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;

public class Project3 {

  /***
   * MAIN FUNCTION
   * @param args
   * @throws IOException
   * @throws ParserException
   */
  public static void main(String[] args) throws IOException, ParserException {

    int printFlag = 0; //flag to tell if the -print option exists
    int textFileFlag = 0; //flag to tell if -textFile file option exists
    int numberOfOptions = 0; //count of how many options are in the command line args minus -README
    int invalidOption = 0; //flag to tell if option proceeding '-' does not exist or is bad
    String [] options = {"-README", "-print", "-textFile"}; //String array of possible options

    int airlineFlightsCommand = 8;  //command list will always have 8 finals: airlineName, flightNumber, src, depart_date, depart_time,
                                    //dest, arrive_date, arrive_time
    String fileName = ""; //String to input file name, located after -textFile
    ArrayList<String> newCommandArgs = new ArrayList<String>(); //new arraylist to get 8 finals as said above

    //error if there are NO command line arguments
    if(args.length <= 0) {
      System.err.println("There are NO command line arguments.\n");
      commandLineInterface();
      System.exit(1);
    }

    //checks for -options for flag switches, or invalid options, also counts how many objects
    for (int i = 0; i < args.length; i++){
      // if -README , print README and exit
      if (args[i].equals("-README")){
        printReadMe();
        System.exit(1);
      }

      // if option proceeding '-' doesn't exist or bad, error than exit
      if (args[i].charAt(0) == '-'){
        for (int j = 0; j < options.length; j++){
          if (args[i].equals(options[j])){
            invalidOption = 0;
            break;
          }else{
            invalidOption = 1;
          }
        }
        if (invalidOption == 1){
          System.err.println("THE OPTION: " + args[i] + " DOES NOT EXIST. \n");
          commandLineInterface();
          System.exit(1);
        }
      }

      //if -print, turn print flag on
      if (args[i].equals("-print")){ // if -print option exists, turn on printFlag then go to next for loop iteration
        printFlag = 1;
        numberOfOptions += 1;
        continue;
      }

      //if -textFile, turn text file flag on, also, store the next argument as the file name
      if (args[i].equals("-textFile")){
        fileName = args[i+1];
        textFileFlag = 1;
        numberOfOptions += 2;
        continue;
      }
    }

    // CHECKS FOR TOO MANY OR TOO LITTLE ARGUMENTS
    //if command line arguments has too few contents, print error then exit
    if (args.length < airlineFlightsCommand + numberOfOptions){
      System.err.println("You have too FEW command line arguments. \n");
      commandLineInterface();
      System.exit(1);
    }
    //if command line arguments has too many contents, print error then exit
    if (args.length > airlineFlightsCommand + numberOfOptions){
      System.err.println("You have far too MANY command line arguments. \n");
      commandLineInterface();
      System.exit(1);
    }

    //for loop to find and put finals in an arraylist
    for (int i = 0; i < args.length; i++){
      //if -print option here, go to next for loop iteration
      if (args[i].equals("-print")){
        continue;
      }

      //if -textFile option here, iterate plus 2
      if (args[i].equals("-textFile")){
        i++;
        continue;
      }

      // if you found date here, concatenate the date and time command line arguments into one string
      if (args[i].indexOf('/') == 2 || args[i].indexOf('/') == 1) {
        // insert a 0 if date format: m/dd/yyyy
        if(args[i].charAt(1) == ('/')){
          args[i] = "0" + args[i];
        }
        // insert a 0 if date format: mm/d/yyyy OR m/d/yyyy --> mm/d/yyyy
        if(args[i].charAt(4) == ('/')){
          args[i] =  args[i].substring(0,3) + "0" + (args[i].substring(3,args.length));
        }
        // this considers if -textFile option exists between date and time
        if(args[i+1].equals("-textFile")){
          // insert a 0 if time format: h:mm
          if(args[i+3].indexOf(':') == 1){
            args[i+3] = "0" + args[i+3];
          }
          // CONCATENATION
          newCommandArgs.add(args[i] + " " + args[i+3]);
          // if it's already on arrival, end the for loop
          if (i + 4 == args.length){
            break;
          }
          i += 3;
          continue;
        }
        // this considers if -print option exists between date and time
        if(args[i+1].equals("-print")){
          // insert a 0 if time format: h:mm
          if(args[i+2].indexOf(':') == 1){
            args[i+2] = "0" + args[i+2];
          }
          // CONCATENATION
          newCommandArgs.add(args[i] + " " + args[i + 2]);
          // if it's already on arrival, end the for loop
          if (i + 3 == args.length) {
            break;
          }
          i += 2;
          continue;
        }
        else {
          // insert a 0 if time format: h:mm
          if(args[i+1].indexOf(':') == 1){
            args[i+1] = "0" + args[i+1];
          }
          // CONCATENATION
          newCommandArgs.add(args[i] + " " + args[i + 1]);
          // if it's already on arrival, end the for loop
          if (i + 2 == args.length) {
            break;
          }
          i += 1;
          continue;
        }
      }
      //appends command line argument into arraylist
      newCommandArgs.add(args[i]);
    }

    String flightNumberCheck = newCommandArgs.get(1); //easy string variable to get the flight number
    // if flight number contains anything other than numbers, throw an illegal argument exception
    try {
      if (!flightNumberCheck.matches("[0-9]+")) {
        throw new IllegalArgumentException();
      }
    } catch (IllegalArgumentException e){
      System.err.println("Flight number contains illegal character. Flight number must be INTEGER. \n");
      System.exit(1);
    }

    try {
      File exists = new File(fileName); //file to check if it exists
      //if file exists, parse file contents as new airline, check if new flight has same airline, if it does: dump it back to the file
      if (textFileFlag == 1 && exists.isFile()) {
        TextParser toParse = new TextParser(fileName);
        Airline airline = (Airline) toParse.parse();
        // initialize flight values according to finals arraylist:
        try {
          toParse.checkIfEqual(newCommandArgs.get(0), airline.getName());
          Flight flight = new Flight(Integer.parseInt(newCommandArgs.get(1)), newCommandArgs.get(2), newCommandArgs.get(3), newCommandArgs.get(4), newCommandArgs.get(5));
          airline.addFlight(flight);
          // if printFlag is on, print new flight description
          if (printFlag == 1) {
            System.out.println("AIRLINE: " + airline.getName());
            System.out.println(flight.toString());
          }
          // dump updated contents into file
          TextDumper toDump = new TextDumper(fileName);
          toDump.dump(airline);
          System.exit(1);
        } catch (IllegalArgumentException e) {
          System.err.println("Flight number contains illegal character. Flight number must be INTEGER. \n");
          System.exit(1);
        }
      }
    } catch (IllegalArgumentException e){
      System.err.println("FILE MALFORMATED. EXITING");
      System.exit(1);
    }

    // initialize flight values according to finals arraylist:
    ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
    Airline airline = new Airline(newCommandArgs.get(0), flightArray);

    try {
      Flight flight = new Flight(Integer.parseInt(newCommandArgs.get(1)), newCommandArgs.get(2), newCommandArgs.get(3), newCommandArgs.get(4), newCommandArgs.get(5));
      airline.addFlight(flight);
      // if printFlag is on, print new flight description
      if (printFlag == 1) {
        System.out.println("AIRLINE: " + airline.getName());
        System.out.println(flight.toString());
      }
      // if textFileFlag is on, dump this new airline and flight into a newly created file
      if (textFileFlag == 1) {
        TextDumper toDump = new TextDumper(fileName);
        toDump.dump(airline);
      }
    } catch (IllegalArgumentException e) {
      System.exit(1);
    }


    System.exit(1);
  }

  /**Command Line Interface
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
            "\t\t-textFile file Where to read/write the airline info\n" +
            "\t\t-print Prints a description of the new flight\n" +
            "\t\t-README Prints a README for this project and exits\n" +
            "\tDate and time should be in the format: mm/dd/yyyy hh:mm\n");
  }

  /**README
   * prints out the README, should be used when -README option exists as the first command line argument in the main function
   */
  public static void printReadMe(){
        /*System.out.println("PROJECT 1: DESIGNING AN AIRLINE APPLICATION\n" +
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
                "of the flight, as well as to be able to view this README.");*/
    System.out.println("PROJECT 2: STORING AN AIRLINE IN A TEXT FILE\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 29, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 2, my objective is to implement the AirlineDumper interface with new java class TextDumper, \n" +
            "implement the AirlineParser interface with new java class TextParser, and to refactor Project 1 --> Project 2 for new \n" +
            "option: -textFile file. The purpose of the TextDumper class is to take an airline and its flights, and dump those contents \n" +
            "into a file. The purpose of the TextParser class is to take a text file that holds an airline's contents and its flights, \n" +
            "and to create and return a new airline from those contents. In the Project 2 class, when the user puts the option -textFile file \n" +
            "in the command line, it can do one of two things: " +
            "\n1) It will create a new file to dump an airline in " +
            "\n2) It will parse the airline in an existing text file, add a new flight only if the flight is from the same airline , then dump it back in \n\n");
  }
}
