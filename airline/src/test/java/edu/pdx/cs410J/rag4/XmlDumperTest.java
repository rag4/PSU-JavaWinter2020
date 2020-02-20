package edu.pdx.cs410J.rag4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlDumperTest {

    // return brand new xmldumper object
    private XmlDumper createXmlDumper(String content){
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
    public void  createFileCreateAirlineOneFlight() throws IOException {
        XmlDumper exampleXml = createXmlDumper("Example.xml");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirline = createAirline("LongBeaches Flights", flightArray);
        exampleAirline.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleXml.dump(exampleAirline);
    }

    @Test
    public void  createFileCreateAirlineTwoFlights() throws IOException {
        XmlDumper exampleXml = createXmlDumper("Example2.xml");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirline = createAirline("LongBeaches Flights", flightArray);
        exampleAirline.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlight( "02", "LAX", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleXml.dump(exampleAirline);
    }

    @Test
    public void  createFileCreateAirlineTwoFlightsCheckSort() throws IOException {
        XmlDumper exampleXml = createXmlDumper("Example2Sort.xml");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirline = createAirline("LongBeaches Flights", flightArray);
        exampleAirline.addFlight(createFlight( "02", "ABQ", "11/11/1111 11:11 am", "PDX", "12/12/2222 22:22 am"));
        exampleAirline.addFlight(createFlight("01", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleXml.dump(exampleAirline);
    }


}
