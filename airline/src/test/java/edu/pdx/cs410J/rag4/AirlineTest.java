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
        Flight flight = new Flight(00, "pdx", "00/00/0000 00:00 am", "lax", "00/00/0000 00:00 pm");
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    /**
     * test creates a new Airline object, test returns the flightArray of the object, test expected to PASS
     */
    @Test
    public void getProperFlight(){
        Flight flight = new Flight(00, "pdx", "00/00/0000 00:00 am", "lax", "00/00/0000 00:00 pm");
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
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 am", "lax", "02/02/2222 22:22 pm");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight3 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadDate(){
        Flight flight = new Flight(00, "pdx", "02/02/2222 22:22 pm", "lax", "01/01/1111 11:11 am");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.printFlights();
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadDepartTimeName(){
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 xm" , "lax", "02/02/2222 22:22 pm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.printFlights();
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadArrivalTimeName(){
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 pm" , "lax", "02/02/2222 22:22 dm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.printFlights();
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadSrcName(){
        Flight flight = new Flight(00, "sfx", "01/01/1111 11:11 dm", "lax", "02/02/2222 22:22 dm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.printFlights();
    }

    @Test(expected = IllegalArgumentException.class)
    public void sortBadDestName(){
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 pm", "sfx", "02/02/2222 22:22 pm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.printFlights();
    }

    @Test
    public void properSorting(){
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 am", "lax", "02/02/2222 22:22 pm");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight3 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        Airline airline = new Airline("TEST", contain);
        airline.addFlight(flight);
        airline.addFlight(flight2);
        airline.addFlight(flight3);
        airline.printFlights();
    }
    @Test
    public void properSortingAlternative(){
        Flight flight = new Flight(00, "pdx", "01/01/1111 11:11 am", "lax", "02/02/2222 22:22 pm");
        Flight flight2 = new Flight(00, "pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight3 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        Flight flight4 = new Flight(00,"pdx", "01/01/1111 10:10 am", "lax", "02/02/2222 11:11 pm");
        ArrayList<Flight> contain = new ArrayList<Flight>();
        contain.add(flight);
        contain.add(flight2);
        contain.add(flight3);
        contain.add(flight4);
        Airline airline = new Airline("TEST", contain);
        airline.printFlights();
    }
}
