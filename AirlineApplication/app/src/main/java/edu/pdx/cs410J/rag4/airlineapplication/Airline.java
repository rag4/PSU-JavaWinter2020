package edu.pdx.cs410J.rag4.airlineapplication;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.*;

/**
 * The main class for the CS410J airline Project
 */

//AIRLINE CLASS
public class Airline extends AbstractAirline<Flight>{

  private final String name;
  Collection<Flight> flights = new ArrayList<>();

  Airline(String name) {
    this.name = name;

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    this.flights.add(flight);
    ArrayList<Flight> newFlights = (ArrayList<Flight>) this.flights;
    Collections.sort(newFlights);
  }

  public void printFlights(){
    for(Flight f : this.flights){
      System.out.println(f.getNumber() + " " + f.getSource() + " " + f.getDepartureString() + " " + f.getDestination() + " " + f.getArrivalString());
    }

  }

  @Override
  public Collection<Flight> getFlights() {
    return this.flights;
  }
}
