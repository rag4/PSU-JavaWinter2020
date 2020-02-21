package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlParserTest {
    // return brand new xmlparser object
    private XmlParser createXmlParser(String content) {
        return new XmlParser(content);
    }
    // return brand new xmldumper object
    private XmlDumper createXmlDumper(String content) {
        return new XmlDumper(content);
    }
    // return brand new airline
    private Airline createAirline(String airlineName, ArrayList<Flight> flightArray){
        Airline airline = new Airline(airlineName, flightArray); // new Airline object
        return airline;
    }
    // return brand new flight
    private Flight createFlight(String flightNumber, String src, String depart, String dest, String arrive){
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        return flight;
    }

    @Test
    public void  createFileCreateAirlineOneFlight() throws IOException, ParserException {
        XmlDumper exampleDump = createXmlDumper("Example.xml");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirlineD = createAirline("LongBeaches Flights", flightArray);
        exampleAirlineD.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 AM"));
        exampleDump.dump(exampleAirlineD);

        XmlParser exampleParse = createXmlParser("Example.xml");
        Airline exampleAirlineP = (Airline) exampleParse.parse();
        exampleAirlineP.printAirlineName();
        exampleAirlineP.printFlights();

        File file = new File("Example.xml");
        file.delete();
    }

    @Test
    public void  createFileCreateAirlineTwoFlights() throws IOException, ParserException {
        XmlDumper exampleDump = createXmlDumper("Example2.xml");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirlineD = createAirline("LongBeaches Flights", flightArray);
        exampleAirlineD.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirlineD.addFlight(createFlight( "02", "LAX", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleDump.dump(exampleAirlineD);

        XmlParser exampleParse = createXmlParser("Example2.xml");
        Airline exampleAirlineP = (Airline) exampleParse.parse();
        exampleAirlineP.printAirlineName();
        exampleAirlineP.printFlights();

        File file = new File("Example2.xml");
        file.delete();
    }

    @Test
    public void  createFileCreateAirlineTwoFlightsSort() throws IOException, ParserException {
        XmlDumper exampleDump = createXmlDumper("Example2Sort.xml");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirlineD = createAirline("LongBeaches Flights", flightArray);
        exampleAirlineD.addFlight(createFlight( "02", "ABQ", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleAirlineD.addFlight(createFlight("01", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleDump.dump(exampleAirlineD);

        XmlParser exampleParse = createXmlParser("Example2Sort.xml");
        Airline exampleAirlineP = (Airline) exampleParse.parse();
        exampleAirlineP.printAirlineName();
        exampleAirlineP.printFlights();

        File file = new File("Example2Sort.xml");
        file.delete();
    }

    @Test(expected = IllegalArgumentException.class)
    public void  nonexistantFile() throws IOException, ParserException {
        XmlParser exampleParse = createXmlParser("NonExistant.xml");
        Airline exampleAirlineP = (Airline) exampleParse.parse();
    }


}
