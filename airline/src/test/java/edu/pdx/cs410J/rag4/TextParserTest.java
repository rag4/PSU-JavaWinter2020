package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextParserTest {

    private TextParser createTextParser(String content) {
        return new TextParser(content + ".txt");
    }

    private TextDumper createTextDumper(String content) {
        return new TextDumper(content + ".txt");
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
    public void parseAnProperExampleFile() throws ParserException, IOException {
        Airline airline = createAirlineWithFlights("Example", "11", "PDX", "11/11/1111 11:11",
                "SFX", "22/22/2222 22:22");
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
    public void parseANonExistingExampleFile() throws ParserException, IOException {
        TextParser toParse = createTextParser("NONEXISTANTFILE");
        Airline parsedAirline = (Airline) toParse.parse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseABadNameExampleFile() throws ParserException, IOException {
        TextParser toParse = createTextParser("%^$");
        Airline parsedAirline = (Airline) toParse.parse();
    }

}
