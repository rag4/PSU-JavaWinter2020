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

    // when there are missing arguments
    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) {
        String hostName = null;         // host name
        String portString = null;       // port name
        String airlineName = null;      // airline name
        String flightNumber = null;     // flight number
        String src = null;              // source code
        String depart = null;           // depart date and time
        String dest = null;             // destination code
        String arrive = null;           // arrive date and time

        int printFlag = 0;              // if -print, turn flag on
        int searchFlag = 0;             // if -search, turn flag on
        int hostFlag = 0;               // if -host, turn flag on
        int portFlag = 0;               // if -port, turn flag on

        String [] options = {"-README", "-print", "-search", "-host", "-port"}; // list of options

        // error if no args
        if (args.length <= 0) {
            usage( MISSING_ARGS);
        }

        // find options README, print, search
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

            if (arg.equals("-host")){
                hostFlag = 1;
            }

            if (arg.equals("-port")){
                portFlag = 1;
            }

            // find option that is bad / doesn't exist
            if (arg.contains("-")){
                int badFlag = 1;
                for (String op : options){
                    if (arg.equals(op)){
                        badFlag = 0;
                        break;
                    }
                }
                if (badFlag == 1) {
                    usage("Bad option: " + arg);
                }
            }
        }

        // cannot have print and search
        if (printFlag == 1 && searchFlag == 1) {
            error("-print and -search cannot both be called");
        }

        // initialize variables
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
                if (hostFlag == 0 && portFlag == 0){
                    error("Cannot search without host name and port number");
                }
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

        if (hostFlag == 1 && portFlag == 1) {

            int port = 0;       // port number as actual integer

            // error if host name is null
            if (hostName == null) {
                usage(MISSING_ARGS);

                // error if port name is null
            } else if (portString == null) {
                usage("Missing port");

                // error if port is not integer
            } else {
                try {
                    port = Integer.parseInt(portString);

                } catch (NumberFormatException ex) {
                    usage("Port \"" + portString + "\" must be an integer");
                    return;
                }
            }


            // new airline REST client with specified host name and port number
            AirlineRestClient client = new AirlineRestClient(hostName, port);

            try {
                // if no airline name, automatic error
                if (airlineName == null) {
                    usage("Missing airline name");

                    // if just airline name, pretty print all flights of airline
                } else if (flightNumber == null && searchFlag == 0) {

                    String xml = client.getAirlineAsXml(airlineName);
                    XmlParser parser = new XmlParser(xml);
                    Airline parsedAirline = (Airline) parser.parse();
                    PrettyPrinter toPretty = new PrettyPrinter();
                    toPretty.dump(parsedAirline);

                    // if -search, print direct flights of airline: SRC -> DEST
                } else if (searchFlag == 1) {
                    String xml = client.getAirlineAsXml(airlineName);
                    XmlParser parser = new XmlParser(xml);
                    Airline parsedAirline = (Airline) parser.parse();
                    PrettyPrinter toPretty = new PrettyPrinter();
                    toPretty.dumpSearch(parsedAirline, src, dest);

                    // create a full airline and add it to airline REST client
                } else {
                    // Post the airlineName/flightNumber pair
                    client.addFlight(airlineName, Integer.parseInt(flightNumber), src, depart, dest, arrive);
                }

                // bad connection/error
            } catch (IOException ex) {
                error("While contacting server: " + ex);
                return;

                // bad parse
            } catch (ParserException ex) {
                error("Parser error");
            }
        }

        // if print option, print new flight
        if (printFlag == 1) {
            System.out.println("\nAIRLINE: " + airlineName);
            System.out.println("FLIGHT: " + Integer.parseInt(flightNumber) + " " + src + " " + depart + " " + dest + " " + arrive);
        }

        System.exit(0);
    }

    /**
     * Prints error message and exits
     * @param message An error message to print
     */
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
        err.println("usage: java edu.pdx.cs410J.<login-id>.Project5 [options] <args>");
        err.println("\targs are (in this order):");
        err.println("\t\tairline \t\tThe name of the airline");
        err.println("\t\tflightNumber \t\tThe flight number");
        err.println("\t\tsrc \t\tThree-letter code of departure airport");
        err.println("\t\tdest \t\tThree-letter code of arrival airport");
        err.println("\t\tarrive \t\tArrival date/time");
        err.println("\toptions are:");
        err.println("\t\t-host hostname \t\tHost computer on which the server runs");
        err.println("\t\t-port port \t\tPort on which the server is listening");
        err.println("\t\t-search \t\tSearch for flights");
        err.println("\t\t-print \t\tPrints a description of the new flight");
        err.println("\t\t-README \t\tPrints a README for this project and exits");

        System.exit(1);
    }

    /**
     * Prints README text then exits
     */
    private static void readMe(){
        System.out.println("PROJECT 5: A REST-ful Airline Web Service\n" +
                "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
                "CLASS: CS410P Advanced Programmin with Java\n" +
                "TEACHER: David Whitlock\n" +
                "DUE DATE: March 4, 2020 before 5:30PM\n" +
                "DESCRIPTION: In Project 5, my job is to implement and make changes to the classes: Airline Servlet, Project5, and \n" +
                "AirlineRESTClient. The AirlineServlet class will provide REST access to Airline, which will be able to perform GET \n" +
                "and POST on particular URL parameters. GET will be able to return flights of an airline, or it will be able to return \n" +
                "particular direct flights. POST will be able to create or add a new flight to an airline that is stored on the servlet. \n" +
                "In Project5, I will implement the option -search, that will allow me to pretty print direct flights specified with only \n" +
                "the airline name, source code, and destination code. Otherwise, I can pretty print all of an airline's flights if I were \n" +
                "to only give the existing airline name. Finally, I will need to edit a few things in the AirlineRESTClient so action performed \n" +
                "between Project5 and AirlineServlet can function correctly. This means I will need to refactor some of the existing methods, such as \n" +
                "XmlDumper or XmlParser.");

        System.exit(1);
    }
}