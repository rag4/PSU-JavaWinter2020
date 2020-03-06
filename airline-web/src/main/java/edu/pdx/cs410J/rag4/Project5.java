package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Airline server using REST.
 */
public class Project5 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) {
        String hostName = null;
        String portString = null;
        String airlineName = null;
        String flightNumber = null;
        String src = null;
        String depart = null;
        String dest = null;
        String arrive = null;

        int printFlag = 0;
        int searchFlag = 0;


        for (String arg : args) {
            if (arg.equals("-README")) {
                readMe();
            }

            if (arg.equals("-print")){
                printFlag = 1;
            }

            if (arg.equals("-search")){
                searchFlag = 1;
            }
        }

        if (printFlag == 1 && searchFlag == 1) {
            error("-print and -search cannot both be called");
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-host") && hostName == null) {
                i++;
                hostName = args[i];

            } else if (args[i].equals("-port") && portString == null) {
                i++;
                portString = args[i];

            } else if (args[i].equals("-print")) {
                continue;

            } else if (args[i].equals("-search")) {
                continue;

            } else if (airlineName == null) {
                airlineName = args[i];

            } else if (flightNumber == null && searchFlag == 0) {
                flightNumber = args[i];

            } else if (src == null) {
                src = args[i];

            } else if (depart == null && searchFlag == 0) {
                depart = args[i];
                i++;
                depart += " " + args[i];
                i++;
                depart += " " + args[i];

            } else if (dest == null) {
                dest = args[i];

            } else if (arrive == null && searchFlag == 0) {
                arrive = args[i];
                i++;
                arrive += " " + args[i];
                i++;
                arrive += " " + args[i];

            } else {
                usage("Extraneous command line argument: " + args[i]);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );
        }

        int port;
        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        AirlineRestClient client = new AirlineRestClient(hostName, port);

        String message;
        try {
            if (airlineName == null) {
                usage("Missing airline name");

            } else if (flightNumber == null && searchFlag == 0) {
                try {
                    String xml = client.getAirlineAsXml(airlineName);
                    XmlParser parser = new XmlParser(xml);
                    Airline parsedAirline = (Airline) parser.parse();
                    PrettyPrinter toPretty = new PrettyPrinter();
                    toPretty.dump(parsedAirline);

                } catch (ParserException ex) {
                    error("Parser error");
                }

            } else if (searchFlag == 1) {
                try {
                    String xml = client.searchAirline(airlineName, src, dest);
                    XmlParser parser = new XmlParser(xml);
                    Airline parsedAirline = (Airline) parser.parse();
                    PrettyPrinter toPretty = new PrettyPrinter();
                    toPretty.dumpSearch(parsedAirline, src, dest);

                } catch (ParserException ex) {
                    error("Parser error");
                }

            } else {
                // Post the airlineName/flightNumber pair
                client.addFlight(airlineName, Integer.parseInt(flightNumber), src, depart, dest, arrive);
            }

        } catch ( IOException ex ) {
            error("While contacting server: " + ex);
            return;
        }

        if (printFlag == 1) {
            System.out.println("\nAIRLINE: " + airlineName);
            System.out.println("FLIGHT: " + Integer.parseInt(flightNumber) + " " + src + " " + depart + " " + dest + " " + arrive);
        }

        System.exit(0);
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project5 host port [word] [definition]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  word         Word in dictionary");
        err.println("  definition   Definition of word");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no definition is specified, then the word's definition");
        err.println("is printed.");
        err.println("If no word is specified, all dictionary entries are printed");
        err.println();

        System.exit(1);
    }

    private static void readMe(){
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

        System.exit(1);
    }
}