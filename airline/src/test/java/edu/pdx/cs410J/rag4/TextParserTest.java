package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextParserTest {

    // return brand new textparser object
    private TextParser createTextParser(String content) {
        return new TextParser(content + ".txt");
    }

    // return brand new textdumper object
    private TextDumper createTextDumper(String content) {
        return new TextDumper(content + ".txt");
    }

    // return brand new airline with filghts
    private Airline createAirlineWithFlights(String airlineName, String flightNumber, String src, String depart, String dest, String arrive){
        ArrayList<Flight> flightArray = new ArrayList<Flight>(); // new Abstract FLight Array List
        Airline airline = new Airline(airlineName, flightArray); // new Airline object
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        airline.addFlight(flight); // add this newly created flight with initialized values into airline
        return airline;
    }

    // return brand new flight
    private Flight createFlights(String flightNumber, String src, String depart, String dest, String arrive){
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        return flight;
    }

    /***
     * creates a file with an airline, then tests if parse is able to get contents of airline
     * and return it as a new airline
     * @throws ParserException
     * @throws IOException
     */
    @Test
    public void parseAnProperExampleFile() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("Example", "11", "PDX", "11/11/1111 11:11 am",
                "LAX", "22/22/2222 22:22 pm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseBadTime() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("ExampleA", "11", "PDX", "01/23/45  04:44 dm",
                "LAX", "12/34/56 05:55 pm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseBadTime2() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("ExampleA", "11", "PDX", "01/23/45  04:44 pm",
                "LAX", "12/34/56 05:55 dm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseBadTime2Alt() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("ExampleA", "11", "PDX", "01/23/45  04:44 pm",
                "LAX", "12/34/56 05:55 dm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseBadTimeBefore() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("Example", "11", "PDX", "01/23/45  04:44 pm",
                "LAX", "12/34/56 05:55 pm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseBadSRC() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("Example", "11", "ABC", "11/11/1111 11:11 am",
                "LAX", "22/22/2222 22:22 pm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseBadDEST() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("Example", "11", "PDX", "11/11/1111 11:11 am",
                "ABC", "22/22/2222 22:22 pm");
        TextDumper toDump = createTextDumper("Example");
        toDump.dump(airline);

        TextParser toParse = createTextParser("Example");
        Airline parsedAirline = (Airline) toParse.parse();

        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test parseAnProperExampleFile() Passed. Example.txt PARSED. Deleting Example.txt file.");
        }else{
            System.out.println("Test parseAnProperExampleFile() failed. Did not PARSE.");
        }
    }

    /***
     * throws an exception if parser tries to parse a file that does not exist
     * @throws ParserException
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    public void parseANonExistingExampleFile() throws ParserException, IOException {
        TextParser toParse = createTextParser("NONEXISTANTFILE");
        Airline parsedAirline = (Airline) toParse.parse();
    }

    /***
     * throws an exception if parser tries to parse a file with a bad name
     * @throws ParserException
     * @throws IOException
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void parseABadNameExampleFile() throws ParserException, IOException {
        TextParser toParse = createTextParser("%^$");
        Airline parsedAirline = (Airline) toParse.parse();
    }



}
