package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextDumperTest {

    private TextDumper createTextDumper(String content){
        return new TextDumper(content + ".txt");
    }
    private TextParser createTextParser(String content) {
        return new TextParser(content + ".txt");
    }

    private Airline createAirlineWithFlights(String airlineName, String flightNumber, String src, String depart, String dest, String arrive){
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>(); // new Abstract FLight Array List
        Airline airline = new Airline(airlineName, flightArray); // new Airline object
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        airline.addFlight(flight); // add this newly created flight with initialized values into airline
        return airline;
    }

    private Flight createFlights(String flightNumber, String src, String depart, String dest, String arrive){
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        return flight;
    }

    @Test
    public void  createAFileAndPutAirlineWithOneFlight() throws IOException {
        TextDumper exampleText = createTextDumper("Example");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        exampleText.dump(exampleAirline);
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test createAFileAndPutAirlineWithOneFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAFileAndPutAirlineWithOneFlight() failed.");
        }
    }

    @Test
    public void  createAFileAndPutAirlineWithTwoFlight() throws IOException {
        TextDumper exampleText = createTextDumper("Example");
        Airline exampleAirline = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        exampleAirline.addFlight(createFlights( "01", "SFX", "11/11/1111 11:11", "PDX", "12/12/2222 22:22"));
        exampleText.dump(exampleAirline);
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test createAFileAndPutAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAFileAndPutAirlineWithTwoFlight() failed.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void  createAFileAndPutTwoAirlinesWithFlight() throws IOException, ParserException {
        TextDumper exampleText = createTextDumper("Example");
        TextParser exampleParse = createTextParser("Example");
        String AirlineName1 = "TEST1";
        String AirlineName2 = "TEST2";

        Airline exampleAirline1 = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        Airline exampleAirline2 = createAirlineWithFlights("TEST2", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");

        exampleText.dump(exampleAirline1);
        exampleParse.checkIfEqual(AirlineName1, AirlineName2);
        exampleText.dump(exampleAirline2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  createAFileAndPutTwoAirlinesWithTwoFlight() throws IOException {
        TextDumper exampleText = createTextDumper("Example");
        TextParser exampleParse = createTextParser("Example");
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

    @Test(expected = IllegalArgumentException.class)
    public void dumpABadNameExampleFile() throws ParserException, IOException {
        TextDumper toDump = createTextDumper("%^$");
        Airline exampleAirline1 = createAirlineWithFlights("TEST1", "00", "PDX", "10/10/1010 10:10", "SFX", "11/11/1111 11:11");
        toDump.dump(exampleAirline1);
    }
}
