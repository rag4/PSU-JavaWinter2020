package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;

import java.util.*;

/**
 * The main class for the CS410J airline Project
 */

//AIRLINE CLASS
public class Airline extends AbstractAirline {

  private final String airlineName; // the airline name
  private ArrayList<Flight> flightArray; // the list of flights contained within an airline

  /**
   * Class implementation for Airline / Constructor
   * @param airlineName
   * @param flightArray
   */
  public Airline(String airlineName, ArrayList<Flight> flightArray){
    this.airlineName = airlineName; // initialize
    this.flightArray = flightArray; // initialize
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
    this.flightArray.add((Flight) flight);
    Collections.sort(this.flightArray);
  }

  public void printAirlineName(){
    System.out.println(this.airlineName);
  }
  public void printFlights(){
    for(AbstractFlight f : this.flightArray){
      System.out.println(f.getNumber() + " " + f.getSource() + " " + f.getDepartureString() + " " + f.getDestination() + " " + f.getArrivalString());
    }

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
