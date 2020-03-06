package edu.pdx.cs410J.rag4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class PrettyPrinterTest {

    // return brand new airline with filghts
    private Airline createAirlineWithFlights(String airlineName, String flightNumber, String src, String depart, String dest, String arrive) throws ParseException {
        Airline airline = new Airline(airlineName); // new Airline object
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        airline.addFlight(flight); // add this newly created flight with initialized values into airline
        return airline;
    }

    // return brand new flight
    private Flight createFlights(String flightNumber, String src, String depart, String dest, String arrive) throws ParseException {
        Flight flight = new Flight(Integer.parseInt(flightNumber), src, depart, dest, arrive);
        return flight;
    }

    @Test
    public void  testPrintOutOne() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedDepart() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/11/1010c 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedArrive() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/110 10:10 PM", "LAX", "11/11/11b11 11:11 PM");
        pretty.dump(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedDepartTIMES() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:xx PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedArriveTIMES() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 xx:11 PM");
        pretty.dump(exampleAirline);
    }

    @Test
    public void  testPrintOutTwo() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
    }

    @Test
    public void  testPrintOutThree() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
    }

    @Test
    public void  testPrintOutSortName() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "ABQ", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
    }

    @Test
    public void  testPrintOutSortTime() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
    }

    @Test
    public void  testPrintOutSortNameThree() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "ABQ", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
    }

    @Test
    public void  testPrintOutSortTimeThree() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneNonExistantSRC() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "SFX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneNonExistantDEPT() throws IOException, ParseException {
        PrettyPrinter pretty = new PrettyPrinter();
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "SFX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
    }



}
