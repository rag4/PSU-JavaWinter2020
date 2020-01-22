package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The main class for the CS410J airline Project
 */

//AIRLINE CLASS
public class Airline extends AbstractAirline {

  private final String airlineName;
  private ArrayList<AbstractFlight> flightArray;

  public Airline(String airlineName, ArrayList<AbstractFlight> flightArray){
    this.airlineName = airlineName;
    this.flightArray = new ArrayList<AbstractFlight>();
  }

  //GET AIRLINE NAME
  @Override
  public String getName() {
    return this.airlineName;
  }

  //ADD FLIGHT TO ARRAY LIST
  @Override
  public void addFlight(AbstractFlight flight) {
    this.flightArray.add(flight);
  }

  //???
  @Override
  public Collection getFlights() {
    return this.flightArray;
  }
}
