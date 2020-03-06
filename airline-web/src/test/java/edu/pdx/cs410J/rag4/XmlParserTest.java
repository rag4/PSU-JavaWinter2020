package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.ParserException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class XmlParserTest {

    // return brand new xmlparser object
    private XmlParser createXmlParser(String content) {
        return new XmlParser(content);
    }

    final String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<!DOCTYPE airline SYSTEM \"http://www.cs.pdx.edu/~whitlock/dtds/airline.dtd\">\n" +
            "<airline>\n" +
            "    <name>Airline</name>\n" +
            "    <flight>\n" +
            "        <number>567</number>\n" +
            "        <src>PDX</src>\n" +
            "        <depart>\n" +
            "            <date day=\"07\" month=\"07\" year=\"2021\"/>\n" +
            "            <time hour=\"13\" minute=\"02\"/>\n" +
            "        </depart>\n" +
            "        <dest>ORD</dest>\n" +
            "        <arrive>\n" +
            "            <date day=\"07\" month=\"07\" year=\"2021\"/>\n" +
            "            <time hour=\"18\" minute=\"22\"/>\n" +
            "        </arrive>\n" +
            "    </flight>\n" +
            "</airline>";

    // return brand new airline
    private Airline createAirline(String airlineName){
        Airline airline = new Airline(airlineName); // new Airline object
        return airline;
    }
    // return brand new flight
    private Flight createFlight(String flightNumber, String src, String depart, String dest, String arrive) throws ParseException {
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        return flight;
    }

    @Test
    public void  createFileCreateAirlineOneFlight() throws IOException, ParserException, ParseException {
        XmlParser exampleParse = createXmlParser(xml);
        Airline exampleAirlineP = (Airline) exampleParse.parse();
        System.out.println(exampleAirlineP.getName());
        exampleAirlineP.printFlights();
    }

    @Test
    public void  createFileCreateAirlineTwoFlights() throws IOException, ParserException, ParseException {
        XmlParser exampleParse = createXmlParser(xml);
        Airline exampleAirlineP = (Airline) exampleParse.parse();
        System.out.println(exampleAirlineP.getName());
        exampleAirlineP.printFlights();
    }

    @Test
    public void  createFileCreateAirlineTwoFlightsSort() throws IOException, ParserException, ParseException {
        XmlParser exampleParse = createXmlParser(xml);
        Airline exampleAirlineP = (Airline) exampleParse.parse();
        System.out.println(exampleAirlineP.getName());
        exampleAirlineP.printFlights();
    }


}
