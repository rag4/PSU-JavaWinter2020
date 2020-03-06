package edu.pdx.cs410J.rag4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class XmlDumperTest {

    // return brand new xmldumper object
    private XmlDumper createXmlDumper(){
        PrintWriter pw = mock(PrintWriter.class);
        return new XmlDumper(pw);
    }

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
    public void  createFileCreateAirlineOneFlight() throws IOException, ParseException {
        XmlDumper exampleXml = createXmlDumper();
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirline = createAirline("LongBeaches Flights");
        exampleAirline.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleXml.dump(exampleAirline);
    }

    @Test
    public void  createFileCreateAirlineTwoFlights() throws IOException, ParseException {
        XmlDumper exampleXml = createXmlDumper();
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirline = createAirline("LongBeaches Flights");
        exampleAirline.addFlight(createFlight("01", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlight( "02", "LAX", "11/11/1111 11:11 am", "PDX", "12/12/2222 12:22 am"));
        exampleXml.dump(exampleAirline);
    }

    @Test
    public void  createFileCreateAirlineTwoFlightsCheckSort() throws IOException, ParseException {
        XmlDumper exampleXml = createXmlDumper();
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline exampleAirline = createAirline("LongBeaches Flights");
        exampleAirline.addFlight(createFlight( "02", "ABQ", "11/11/1111 11:11 am", "PDX", "12/12/2222 12:22 am"));
        exampleAirline.addFlight(createFlight("01", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleXml.dump(exampleAirline);
    }

}
