package edu.pdx.cs410J.rag4;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Unit tests for the {@link Airline} class.
 */
public class AirlineTest{

    /*
    //AIRLINE NAME

    */

    /**
     * creates a new Airline object with the flightArray value of the object equaling the function argument
     * @param flightArray
     * @return
     */
    private Airline createAirlineFlightList(ArrayList<Flight> flightArray){
        return new Airline("Test", flightArray);
    }

    /**
     * test creates a new Airline object, test adds/appends a flight into the flightArray of the object, test expected to PASS
     */
    @Test
    public void addProperFlight(){
        Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test returns the flightArray of the object, test expected to PASS
     */
    @Test
    public void getProperFlight(){
        Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.getFlights();
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because src is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidSrcInFlight(){
        Flight flight = new Flight(00, "no", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because depart is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidDepartInFlight(){
        Flight flight = new Flight(00, "pdx", "000/000/00000 000:000", "tst", "00/00/0000 00:00");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because dest is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidDestInFlight(){
        Flight flight = new Flight(00, "pdx", "11/11/1111 11:11", "wrong", "22/22/2222 22:22");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test supposed to add a flight into flightArray but an illegal argument exception is expected because arrive is invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void addInvalidArriveInFlight(){
        Flight flight = new Flight(00, "pdx", "11/11/1111 11:11", "sfx", "ab/cd/efgh ij:kl");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    @Test
    public void sortThreeProperFlight(){
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11", "sfx", "02/02/2222 22:22");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10", "sfx", "02/02/2222 11:11");
        Flight flight3 = new Flight(00,"abc", "01/01/1111 10:10", "sfx", "02/02/2222 11:11");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
        airline.printFlights();
    }
}
