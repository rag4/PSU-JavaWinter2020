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

public class Project4 {

  /***
   * MAIN FUNCTION
   * @param args
   * @throws IOException
   * @throws ParserException
   */
  public static void main(String[] args) throws IOException, ParserException {

    int printFlag = 0; //flag to tell if the -print option exists
    int textFileFlag = 0; //flag to tell if -textFile file option exists
    int prettyFileFlag = 0; //flag to tell if -pretty option exists (print to text file)
    int prettyOutFlag = 0; //flag to tell if -pretty option exists (print out)
    int xmlFileFlag = 0; //flag to tell if -xmlFile file option exists
    int numberOfOptions = 0; //count of how many options are in the command line args minus -README
    int invalidOption = 0; //flag to tell if option proceeding '-' does not exist or is bad
    String [] options = {"-README", "-print", "-textFile", "-pretty", "-", "-xmlFile"}; //String array of possible options

    int airlineFlightsCommand = 10;  //command list will always have 10 finals: airlineName, flightNumber, src, depart_date, depart_time, depart_symbol
                                    //dest, arrive_date, arrive_time, arrive_symbol
    String textfileName = ""; //String to input file name, located after -textFile
    String xmlfileName = "";
    String prettyfileName = ""; //String to input file name, located after -textFile

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
        textfileName = args[i+1];
        textFileFlag = 1;
        numberOfOptions += 2;
        continue;
      }
      //if -xmlFile, turn xml file flag on, also, store the next argument as the xml file name
      if (args[i].equals("-xmlFile")){
        xmlfileName = args[i+1];
        xmlFileFlag = 1;
        numberOfOptions += 2;
        continue;
      }
      //cannot have both -xmlFile and -textFile flag on
      if(textFileFlag == 1 && xmlFileFlag == 1){
        System.err.println("YOU CANNOT HAVE BOTH -textFile and -xmlFile OPTIONS SELECTED");
        commandLineInterface();
        System.exit(1);
      }
      //if -pretty, turn pretty flag on, also, store the next argument depending on its purpose
      if (args[i].equals("-pretty")){
        if(args[i+1].equals("-")){
          prettyOutFlag = 1;
          numberOfOptions += 2;
        }else {
          prettyfileName = args[i + 1];
          prettyFileFlag = 1;
          numberOfOptions += 2;
        }
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
    for (int i = 0; i < args.length; ++i){
      //if -print option here, go to next for loop iteration
      if (args[i].equals("-print")){
        continue;
      }

      //if -textFile option here, iterate plus 2
      if (args[i].equals("-textFile") || args[i].equals("-pretty") || args[i].equals("-xmlFile")){
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
          args[i] =  args[i].substring(0,3) + "0" + (args[i].substring(3,args[i].length()));
        }
        // insert a 0 if time format: h:mm
        if(args[i+1].indexOf(':') == 1){
          args[i+1] = "0" + args[i+1];
        }

        if(Integer.parseInt(args[i+1].substring(0,2)) > 12){
          System.err.println("PLEASE DO NOT PUT 24-HOUR TIME.");
          System.exit(1);
        }

        // CONCATENATION
        newCommandArgs.add(args[i] + " " + args[i + 1] + " " + args[i + 2]);
        // if it's already on arrival, end the for loop
        if (i + 3 == args.length) {
          break;
        }
        i += 3;
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

    Flight flight = new Flight(Integer.parseInt(newCommandArgs.get(1)), newCommandArgs.get(2), newCommandArgs.get(3), newCommandArgs.get(4), newCommandArgs.get(5));
    String fileName = null;
    if(textFileFlag == 1){
      fileName = textfileName;
    }
    if(xmlFileFlag == 1){
      fileName = xmlfileName;
    }
    // initialize flight values according to finals arraylist:
    try {
      if(textFileFlag == 1 || xmlFileFlag == 1) {
        File exists = new File(fileName); //file to check if it exists
        //TEXT FILE EXISTS
        if (textFileFlag == 1 && exists.isFile()) {
          TextParser toParse = new TextParser(fileName);
          Airline airline = (Airline) toParse.parse();
          toParse.checkIfEqual(newCommandArgs.get(0), airline.getName());
          airline.addFlight(flight);

          // if printFlag is on, print new flight description
          printers(printFlag, prettyFileFlag, prettyOutFlag, prettyfileName, flight, airline);
          // dump updated contents into file
          TextDumper toDump = new TextDumper(fileName);
          toDump.dump(airline);
          System.exit(1);
        }
        // XML FILE EXISTS
        if (xmlFileFlag == 1 && exists.isFile()) {
          XmlParser toParse = new XmlParser(fileName);
          Airline airline = (Airline) toParse.parse();
          toParse.checkIfEqual(newCommandArgs.get(0), airline.getName());
          airline.addFlight(flight);

          // if printFlag is on, print new flight description
          printers(printFlag, prettyFileFlag, prettyOutFlag, prettyfileName, flight, airline);
          // dump updated contents into file
          XmlDumper toDump = new XmlDumper(fileName);
          toDump.dump(airline);
          System.exit(1);
        }
      }
    } catch (IllegalArgumentException e) {
      commandLineInterface();
      System.exit(1);
    }

    //NO EXISTING FILE
    try {
      // initialize flight values according to finals arraylist:
      ArrayList<Flight> flightArray = new ArrayList<Flight>();
      Airline airline = new Airline(newCommandArgs.get(0), flightArray);
      airline.addFlight(flight);
      // if textFileFlag is on, dump this new airline and flight into a newly created file
      if (textFileFlag == 1) {
        TextDumper toDump = new TextDumper(textfileName);
        toDump.dump(airline);
      }
      if (xmlFileFlag == 1) {
        XmlDumper toDump = new XmlDumper(xmlfileName);
        toDump.dump(airline);
      }
      printers(printFlag, prettyFileFlag, prettyOutFlag, prettyfileName, flight, airline);
    } catch (IllegalArgumentException e) {
      commandLineInterface();
      System.exit(1);
    }
    System.exit(1);
  }

  private static void printers(int printFlag, int prettyFileFlag, int prettyOutFlag, String prettyfileName, Flight flight, Airline airline) throws IOException {
    // if printFlag is on, print new flight description
    if (printFlag == 1) {
      System.out.println("\nAIRLINE: " + airline.getName());
      System.out.println(flight.toString());
    }
    // if prettyFlag is on (output)
    if (prettyOutFlag == 1) {
      PrettyPrinter toPretty = new PrettyPrinter(prettyfileName);
      toPretty.dumpOut(airline);
    }
    // if prettyFlag is on (file)
    if (prettyFileFlag == 1) {
      PrettyPrinter toPretty = new PrettyPrinter(prettyfileName);
      toPretty.dump(airline);
    }
  }

  /**Command Line Interface
   * prints out the command line interface, should be used when improper usage of the command line arguments in the main function occurs
   */
  public static void commandLineInterface(){
    System.out.println("usage: java edu.pdx.cs410J.<login-id>.Project4 [options] <args>\n" +
            "args are (in this order):\n" +
            "name The name of the airline\n" +
            "flightNumber The flight number\n" +
            "src Three-letter code of departure airport\n" +
            "depart Departure date time am/pm\n" +
            "dest Three-letter code of arrival airport\n" +
            "arrive Arrival date time am/pm\n" +
            "options are (options may appear in any order):\n" +
            "-xmlFile file Where to read/write the airline info\n" +
            "-textFile file Where to read/write the airline info\n" +
            "-pretty file Pretty print the airline’s flights to\n" +
            "a text file or standard out (file -)\n" +
            "-print Prints a description of the new flight\n" +
            "-README Prints a README for this project and exits");
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
    /*System.out.println("PROJECT 2: STORING AN AIRLINE IN A TEXT FILE\n" +
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
            "\n2) It will parse the airline in an existing text file, add a new flight only if the flight is from the same airline , then dump it back in \n\n");*/
    /*System.out.println("PROJECT 3: PRETTY PRINTING YOUR AIRLINE\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 5, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 3, my job is to represent our departure and arrival times as instances of java.util.Date, instead of our\n" +
            "prior String values. Our get methods however, for these will return String Formats of these instances, so a conversion is needed.\n" +
            "We will also need to implement sorting for our flights, where our flights should be sorted alphabetically by their code of their source.\n" +
            "If they depart from the same airport, however, we will need to sort by their departure time in chronological order. We can achieve our\n" +
            "sorting methods by having our Flight class implment java.lang.Comparable. Our next step is to create a class called the PrettyPrinter class.\n" +
            "The PrettyPrinterClass implements the AirlineDumper interface, and its purpose is to create a nicely formatted textual presentation of our\n" +
            "airline's flights. We will need to use DateFormat to make our dates look nice, and we are also recommended to use the AirportNames class.\n\n");*/
    System.out.println("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n");
  }
}
