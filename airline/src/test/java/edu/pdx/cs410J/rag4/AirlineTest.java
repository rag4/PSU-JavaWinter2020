package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractFlight;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AirlineTest{

    /*
    //AIRLINE NAME

    */
    private Airline createAirlineFlightList(ArrayList<AbstractFlight> flightArray){
        return new Airline("Test", flightArray);
    }

    @Test
    public void addProperFlight(){
        Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    @Test
    public void getProperFlight(){
        Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.getFlights();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidSrcInFlight(){
        Flight flight = new Flight(00, "no", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidDepartInFlight(){
        Flight flight = new Flight(00, "pdx", "000/000/00000 000:000", "tst", "00/00/0000 00:00");
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidDestInFlight(){
        Flight flight = new Flight(00, "pdx", "11/11/1111 11:11", "wrong", "22/22/2222 22:22");
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addInvalidArriveInFlight(){
        Flight flight = new Flight(00, "pdx", "11/11/1111 11:11", "sfx", "ab/cd/efgh ij:kl");
        ArrayList<AbstractFlight> flightArray = new ArrayList<AbstractFlight>();
        Airline airline = createAirlineFlightList(flightArray);
        airline.addFlight(flight);
    }

}
