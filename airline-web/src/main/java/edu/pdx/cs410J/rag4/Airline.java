package edu.pdx.cs410J.rag4;

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

  public Airline(String name) {
    this.name = name;

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void addFlight(Flight flight) {
    this.flights.add(flight);
  }

  @Override
  public Collection<Flight> getFlights() {
    return this.flights;
  }
}
