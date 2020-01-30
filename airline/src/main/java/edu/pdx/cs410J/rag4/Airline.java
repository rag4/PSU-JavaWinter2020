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

  private final String airlineName; // the airline name
  private ArrayList<AbstractFlight> flightArray; // the list of flights contained within an airline

  /**
   * Class implementation for Airline / Constructor
   * @param airlineName
   * @param flightArray
   */
  public Airline(String airlineName, ArrayList<AbstractFlight> flightArray){
    this.airlineName = airlineName; // initialize
    this.flightArray = new ArrayList<AbstractFlight>(); // initialize
  }

  /**
   * returns the airlineName of the class
   * @return
   */
  @Override
  public String getName() {
    return this.airlineName;
  }

  /**
   * appends a flight to the flightArray of the class
   * @param flight
   */
  @Override
  public void addFlight(AbstractFlight flight) {
    this.flightArray.add(flight);
  }

  /**
   * returns the flightArray
   * @return
   */
  @Override
  public Collection getFlights() {
    return this.flightArray;
  }


}
