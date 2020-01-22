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
    Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:000");
    flight.getArrivalString();
  }

  @Test
  public void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight(42, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
    assertThat(flight.getNumber(), equalTo(42));
  }

  @Ignore
  @Test
  public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight(00, "tst", null, "tst", "00/00/0000 00:00");
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  //FLIGHTNUMBER
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

  //SRC
  private Flight createFlightWithSrc(String src) {
    return new Flight(00, src, "00/00/0000 00:00", "tst", "00/00/0000 00:00");
  }

  @Test
  public void properSrcTest(){
    String validSrc = "pdx";
    createFlightWithSrc(validSrc);
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

  //DEPART
  private Flight createFlightWithDepart(String depart) {
    return new Flight(00, "tst", depart, "tst", "00/00/0000 00:00");
  }

  @Test
  public void properDepartTest(){
    String validDepart = "01/01/1111 11:11";
    createFlightWithDepart(validDepart);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartWrongFormatDate(){
    String invalidDepart = "01/01/11111 11:11";
    createFlightWithDepart(invalidDepart);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartWrongFormatTime(){
    String invalidDepart = "01/01/1111 111:11";
    createFlightWithDepart(invalidDepart);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenDepartNotValid(){
    String invalidDepart = "ab/bc/defg hi:jk";
    createFlightWithDepart(invalidDepart);
  }

  //DEST
  private Flight createFlightWithDest(String dest) {
    return new Flight(00, "tst", "00/00/0000 00:00", dest, "00/00/0000 00:00");
  }

  @Test
  public void properDestTest(){
    String validDest = "sdx";
    createFlightWithDest(validDest);
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

  //ARRIVE
  private Flight createFlightWithArrive(String arrive) {
    return new Flight(00, "tst", "00/00/0000 00:00", "tst", arrive);
  }

  @Test
  public void properArriveTest(){
    String validArrive = "01/01/1111 11:11";
    createFlightWithArrive(validArrive);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArriveWrongFormatDate(){
    String invalidArrive = "01/01/11111 11:11";
    createFlightWithArrive(invalidArrive);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArriveWrongFormatTime(){
    String invalidArrive = "01/01/1111 111:11";
    createFlightWithArrive(invalidArrive);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenArriveNotValid(){
    String invalidArrive = "ab/bc/defg hi:jk";
    createFlightWithDepart(invalidArrive);
  }

  //FINALIZED TEST PROPER
  private Flight createFlightFinal(String src, String dept,  String dest, String arrive){
    return new Flight(00, src, dept, dest, arrive);
  }

  @Test
  public void properFinalTest(){
    String validSrc = "pdx";
    String validDepart = "01/01/1111 11:11";
    String validDest = "sfx";
    String validArrive = "02/02/2222 22:22";
    createFlightFinal(validSrc, validDepart, validDest, validArrive);
  }
}

