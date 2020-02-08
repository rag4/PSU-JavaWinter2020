package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextDumperTest {

    // return brand new textdumper object
    private TextDumper createTextDumper(String content){
        return new TextDumper(content );
    }

    // return brand new textparser object
    private TextParser createTextParser(String content) {
        return new TextParser(content + ".txt");
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
     * tests to see if you can create a new file, then dump one airline with one flight into it
     * @throws IOException
     */
    @Test
    public void  createAFileAndPutAirlineWithOneFlight() throws IOException {
        TextDumper exampleText = createTextDumper("Example.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleText.dump(exampleAirline);
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test createAFileAndPutAirlineWithOneFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAFileAndPutAirlineWithOneFlight() failed.");
        }
    }

    /***
     * tests to see if you can create a new file, then dump one airline with two flights into it
     * @throws IOException
     */
    @Test
    public void  createAFileAndPutAirlineWithTwoFlight() throws IOException {
        TextDumper exampleText = createTextDumper("Example.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10 am", "LAX", "11/11/1111 11:11 am");
        exampleAirline.addFlight(createFlights( "01", "LAX", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleText.dump(exampleAirline);
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test createAFileAndPutAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAFileAndPutAirlineWithTwoFlight() failed.");
        }
    }

    /***
     * tests to see if you can create a new file, then dump two airline with one flight into it
     * you are not supposed to be able to put two airlines -- should throw an exception
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    public void  createAFileAndPutTwoAirlinesWithFlight() throws IOException, ParserException {
        TextDumper exampleText = createTextDumper("Example.txt");
        TextParser exampleParse = createTextParser("Example.txt");
        String AirlineName1 = "TEST1";
        String AirlineName2 = "TEST2";

        Airline exampleAirline1 = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        Airline exampleAirline2 = createAirlineWithFlights("TEST2", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");

        exampleText.dump(exampleAirline1);
        exampleParse.checkIfEqual(AirlineName1, AirlineName2);
        exampleText.dump(exampleAirline2);
    }

    /***
     * tests to see if you can create a new file, then dump two airline with two flights into it
     * you are not supposed to be able to put two airlines -- should throw an exception
     * @throws IOException
     */
    @Test(expected = IllegalArgumentException.class)
    public void  createAFileAndPutTwoAirlinesWithTwoFlight() throws IOException {
        TextDumper exampleText = createTextDumper("Example.txt");
        TextParser exampleParse = createTextParser("Example.txt");
        String AirlineName1 = "TEST1";
        String AirlineName2 = "TEST2";

        Airline exampleAirline1 = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        Airline exampleAirline2 = createAirlineWithFlights("TEST2", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        exampleAirline1.addFlight(createFlights( "01", "SFX", "11/11/1111 11:11", "PDX", "12/12/2222 22:22"));
        exampleAirline2.addFlight(createFlights( "01", "SFX", "11/11/1111 11:11", "PDX", "12/12/2222 22:22"));

        exampleText.dump(exampleAirline1);
        exampleParse.checkIfEqual(AirlineName1, AirlineName2);
        exampleText.dump(exampleAirline2);
    }

    /***
     * throws an exception if dumper tries to dump a file with a bad name
     * @throws ParserException
     * @throws IOException
     */
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void dumpABadNameExampleFile() throws ParserException, IOException {
        TextDumper toDump = createTextDumper("%^$");
        Airline exampleAirline1 = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        toDump.dump(exampleAirline1);
    }
}
