package edu.pdx.cs410J.rag4;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */
public class FlightTest {

  @Ignore
  @Test(expected = UnsupportedOperationException.class)
  public void getArrivalStringNeedsToBeImplemented() {
    Flight flight = new Flight(00, "test", "0/0/00 00:00", "test", "0/0/00 00:00");
    flight.getArrivalString();
  }

  @Test
  public void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight(42, "test", "0/0/00 00:00", "test", "0/0/00 00:00");
    assertThat(flight.getNumber(), equalTo(42));
  }

  @Test
  public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight(00, "test", null, "test", "0/0/00 00:00");
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  /*
  @Test
  public void isFlightNumberUnique(){

  }
  */

  /*
  @Test
  public void whenFlightNumberNotInt(){

  }
  */

  private Flight createFlightWithSrc(String src) {
    return new Flight(00, src, "0/0/00 00:00", "test", "0/0/00 00:00");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSrcLessThanThreeLong(){
    String invalidSrc = "pd";
    createFlightWithSrc(invalidSrc);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSrcMoreThanThreeLong(){
    String invalidSrc = "pdxt";
    createFlightWithSrc(invalidSrc);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenSrcIsntOnlyLetters(){
    String invalidSrc = "12a";
    createFlightWithSrc(invalidSrc);
  }

  @Test
  public void whenDepartWrongFormat(){

  }

  @Test
  public void whenDepartNotValid(){

  }

  private Flight createFlightWithDest(String dest) {
    return new Flight(00, "test", "0/0/00 00:00", dest, "0/0/00 00:00");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDestLessThanThreeLong(){
    String invalidDest = "sf";
    createFlightWithDest(invalidDest);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDestMoreThanThreeLong(){
    String invalidDest = "sfxt";
    createFlightWithDest(invalidDest);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDestIsntOnlyLetters(){
    String invalidDest = "12a";
    createFlightWithDest(invalidDest);
  }

  @Test
  public void whenArriveWrongFormat(){

  }

  @Test
  public void whenArriveNotValid(){

  }
}
