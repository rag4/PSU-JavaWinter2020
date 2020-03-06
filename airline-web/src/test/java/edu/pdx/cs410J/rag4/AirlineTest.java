package edu.pdx.cs410J.rag4;

import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Unit tests for the {@link Airline} class.
 */
public class AirlineTest {

    /*
    //AIRLINE NAME

    */

    /**
     * creates a new Airline object with the flightArray value of the object equaling the function argument
     * @return
     */
    private Airline createAirlineFlightList(){
        return new Airline("Test");
    }

    /**
     * test creates a new Airline object, test adds/appends a flight into the flightArray of the object, test expected to PASS
     */
    @Test
    public void addProperFlight() throws ParseException {
        Flight flight = new Flight(00, "pdx", "00/00/0000 00:00 am", "lax", "00/00/0000 00:00 pm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test returns the flightArray of the object, test expected to PASS
     */
    @Test
    public void getProperFlight() throws ParseException {
        Flight flight = new Flight(00, "pdx", "00/00/0000 00:00 am", "lax", "00/00/0000 00:00 pm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
        airline.getFlights();
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because src is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidSrcInFlight() throws ParseException {
        Flight flight = new Flight(00, "no", "00/00/0000 00:00 am", "lax", "00/00/0000 00:00 pm");
        Airline airline = createAirlineFlightList();

        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because depart is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidDepartInFlight() throws ParseException {
        Flight flight = new Flight(00, "pdx", "000/000/00000 000:000", "tst", "00/00/0000 00:00");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);

    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because dest is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidDestInFlight() throws ParseException {
        Flight flight = new Flight(00, "pdx", "11/11/1111 11:11", "wrong", "22/22/2222 22:22");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because arrive is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidArriveInFlight() throws ParseException {
        Flight flight = new Flight(00, "pdx", "11/11/1111 11:11", "sfx", "ab/cd/efgh ij:kl");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    @Test
    public void sortThreeProperFlight() throws ParseException {
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 am", "lax", "02/02/2222 12:22 pm");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight3 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
        airline.printFlights();
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadDate() throws ParseException {
        Flight flight = new Flight(00, "pdx", "02/02/2222 22:22 pm", "lax", "01/01/1111 11:11 am");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadDepartTimeName() throws ParseException {
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 xm" , "lax", "02/02/2222 12:22 pm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadArrivalTimeName() throws ParseException {
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 pm" , "lax", "02/02/2222 12:22 dm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadSrcName() throws ParseException {
        Flight flight = new Flight(00, "sfx", "01/01/1111 11:11 dm", "lax", "02/02/2222 12:22 dm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadDestName() throws ParseException {
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 pm", "sfx", "02/02/2222 12:22 pm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
    }

    @Test
    public void properSorting() throws ParseException {
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 am", "lax", "02/02/2222 12:22 pm");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight3 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
        airline.printFlights();
    }
    @Test
    public void properSortingAlternative() throws ParseException {
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 am", "lax", "02/02/2222 12:22 pm");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight3 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight4 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Airline airline = createAirlineFlightList();
        airline.addFlight(flight);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
        airline.addFlight(flight4);
        airline.printFlights();
    }
}
