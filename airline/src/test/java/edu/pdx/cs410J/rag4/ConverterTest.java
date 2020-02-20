package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class ConverterTest {
    // return brand new xmlparser object
    private XmlParser createXmlParser(String content) {
        return new XmlParser(content);
    }
    // return brand new xmldumper object
    private XmlDumper createXmlDumper(String content) {
        return new XmlDumper(content);
    }
    // return brand new airline
    // return brand new textparser object
    private TextParser createTextParser(String content) {
        return new TextParser(content);
    }
    // return brand new textdumper object
    private TextDumper createTextDumper(String content) {
        return new TextDumper(content);
    }
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
        TextDumper exampleDump = createTextDumper("ExampleConvert.txt");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirlineD = createAirline("LongBeaches Flights", flightArray);
        exampleAirlineD.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleDump.dump(exampleAirlineD);

        Converter toConvert = new Converter("ExampleConvert.txt", "ExampleConvert.xml");
        toConvert.convert();
    }

    @Test
    public void  createFileCreateAirlineTwoFlights() throws IOException, ParserException {
        TextDumper exampleDump = createTextDumper("ExampleConvert2.txt");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirlineD = createAirline("LongBeaches Flights", flightArray);
        exampleAirlineD.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirlineD.addFlight(createFlight("02", "LAX", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleDump.dump(exampleAirlineD);

        Converter toConvert = new Converter("ExampleConvert2.txt", "ExampleConvert2.xml");
        toConvert.convert();
    }

    @Test
    public void  createFileCreateAirlineTwoFlightsSort() throws IOException, ParserException {
        TextDumper exampleDump = createTextDumper("ExampleConvert2Sort.txt");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirlineD = createAirline("LongBeaches Flights", flightArray);
        exampleAirlineD.addFlight(createFlight("02", "ABQ", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleAirlineD.addFlight(createFlight("01", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleDump.dump(exampleAirlineD);

        Converter toConvert = new Converter("ExampleConvert2Sort.txt", "ExampleConvert2Sort.xml");
        toConvert.convert();
    }
}
