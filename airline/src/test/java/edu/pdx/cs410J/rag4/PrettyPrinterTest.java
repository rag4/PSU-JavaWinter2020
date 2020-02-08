package edu.pdx.cs410J.rag4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class PrettyPrinterTest {

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

    @Test
    public void  createAPrettyPrintWithOneAirlineWithOneFlight() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithOneFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithOneFlight() failed.");
        }
    }

    @Test
    public void  createAPrettyPrintWithOneAirlineWithTwoFlight() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() failed.");
        }
    }

    @Test
    public void  createAPrettyPrintWithOneAirlineWithThreeFlight() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() failed.");
        }
    }

    @Test
    public void  testPrintOutOne() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dumpOut(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedDepart() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/11/1010c 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dumpOut(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedArrive() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/110 10:10 PM", "LAX", "11/11/11b11 11:11 PM");
        pretty.dumpOut(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedDepartTIMES() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:xx PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dumpOut(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneMalformattedArriveTIMES() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 xx:11 PM");
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintOutTwo() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintOutThree() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintOutSortName() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "ABQ", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintOutSortTime() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintOutSortNameThree() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "ABQ", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintOutSortTimeThree() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dumpOut(exampleAirline);
    }

    @Test
    public void  testPrintFileSortName() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "ABQ", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() failed.");
        }
    }

    @Test
    public void  testPrintFileSortTime() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() failed.");
        }
    }

    @Test
    public void  testPrintFileSortNameThree() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "ABE", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "ABQ", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() failed.");
        }
    }

    @Test
    public void  testPrintFileSortTimeThree() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        exampleAirline.addFlight(createFlights("00", "PDX", "10/10/1010 09:10 PM", "LAX", "11/11/1111 11:11 PM"));
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithTwoFlight() failed.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrettyFileNonExistantSRC() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "SFX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithOneFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithOneFlight() failed.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrettyFileNonExistantDEST() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "SFX", "11/11/1111 11:11 PM");
        pretty.dump(exampleAirline);
        File file = new File("PrettyFile.txt");
        if(file.delete()){
            System.out.println("Test createAPrettyPrintWithOneAirlineWithOneFlight() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test createAPrettyPrintWithOneAirlineWithOneFlight() failed.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneNonExistantSRC() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "SFX", "10/10/1010 10:10 PM", "LAX", "11/11/1111 11:11 PM");
        pretty.dumpOut(exampleAirline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void  testPrintOutOneNonExistantDEPT() throws IOException {
        PrettyPrinter pretty = new PrettyPrinter("PrettyFile.txt");
        Airline exampleAirline = createAirlineWithFlights("TEST", "00", "PDX", "10/10/1010 10:10 PM", "SFX", "11/11/1111 11:11 PM");
        pretty.dumpOut(exampleAirline);
    }



}
