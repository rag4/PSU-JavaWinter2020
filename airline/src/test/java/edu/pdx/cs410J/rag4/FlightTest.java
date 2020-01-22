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

  // test expects an unsupported operation exception if the getArrival() function in our Flight class has not been implemented
  @Ignore
  @Test(expected = UnsupportedOperationException.class)
  public void getArrivalStringNeedsToBeImplemented() {
    Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:000");
    flight.getArrivalString();
  }

  // test asserts that the flightNumber is 42 before implementing the getNumber() function in our Flight class
  @Test
  public void initiallyAllFlightsHaveTheSameNumber() {
    Flight flight = new Flight(42, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:00");
    assertThat(flight.getNumber(), equalTo(42));
  }

  // test asserts that the getDeparture() function will return null before implementing this function in our Flight class
  @Ignore
  @Test
  public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() {
    Flight flight = new Flight(00, "tst", null, "tst", "00/00/0000 00:00");
    assertThat(flight.getDeparture(), is(nullValue()));
  }

  //FLIGHTNUMBER
  /*
  // test makes sure our flightNumber is unique / is a key to each distinct flight
  @Test
  public void isFlightNumberUnique(){

  }
  */

  /*
  // test makes sure our flightNumber is ONLY an integer and not anything else
  @Test
  public void whenFlightNumberNotInt(){

  }
  */

  //SRC

  /**
   * creates a new Flight object with the src value of the object equaling the function argument
    * @param src
   * @return
   */
  private Flight createFlightWithSrc(String src) {
    return new Flight(00, src, "00/00/0000 00:00", "tst", "00/00/0000 00:00");
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the src
   */
  @Test
  public void properSrcTest(){
    String validSrc = "pdx";
    createFlightWithSrc(validSrc);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because src length is too small
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenSrcLessThanThreeLong(){
    String invalidSrc = "pd";
    createFlightWithSrc(invalidSrc);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because src length is too big
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenSrcMoreThanThreeLong(){
    String invalidSrc = "pdxt";
    createFlightWithSrc(invalidSrc);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because src contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenSrcIsntOnlyLetters(){
    String invalidSrc = "1/a";
    createFlightWithSrc(invalidSrc);
  }

  //DEPART

  /**
   * creates a new Flight object with the depart value of the object equaling the function argument
   * @param depart
   * @return
   */
  private Flight createFlightWithDepart(String depart) {
    return new Flight(00, "tst", depart, "tst", "00/00/0000 00:00");
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the depart
   */
  @Test
  public void properDepartTest(){
    String validDepart = "01/01/1111 11:11";
    createFlightWithDepart(validDepart);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because depart isn't correct size (specifically on the DATE)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDepartWrongFormatDate(){
    String invalidDepart = "01/01/11111 11:11";
    createFlightWithDepart(invalidDepart);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because depart isn't correct size (specifically on the TIME)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDepartWrongFormatTime(){
    String invalidDepart = "01/01/1111 111:11";
    createFlightWithDepart(invalidDepart);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because depart contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDepartNotValid(){
    String invalidDepart = "ab/bc/defg hi:jk";
    createFlightWithDepart(invalidDepart);
  }

  //DEST

  /**
   * creates a new Flight object with the dest value of the object equaling the function argument
   * @param dest
   * @return
   */
  private Flight createFlightWithDest(String dest) {
    return new Flight(00, "tst", "00/00/0000 00:00", dest, "00/00/0000 00:00");
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the dest
   */
  @Test
  public void properDestTest(){
    String validDest = "sdx";
    createFlightWithDest(validDest);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because dest length is too small
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDestLessThanThreeLong(){
    String invalidDest = "sf";
    createFlightWithDest(invalidDest);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because dest length is too big
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDestMoreThanThreeLong(){
    String invalidDest = "sfxt";
    createFlightWithDest(invalidDest);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because dest contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDestIsntOnlyLetters(){
    String invalidDest = "1/a";
    createFlightWithDest(invalidDest);
  }

  //ARRIVE

  /**
   * creates a new Flight object with the arrive value of the object equaling the function argument
   * @param arrive
   * @return
   */
  private Flight createFlightWithArrive(String arrive) {
    return new Flight(00, "tst", "00/00/0000 00:00", "tst", arrive);
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the arrive
   */
  @Test
  public void properArriveTest(){
    String validArrive = "01/01/1111 11:11";
    createFlightWithArrive(validArrive);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because arrive isn't correct size (specifically on the DATE)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenArriveWrongFormatDate(){
    String invalidArrive = "01/01/11111 11:11";
    createFlightWithArrive(invalidArrive);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because arrive isn't correct size (specifically on the TIME)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenArriveWrongFormatTime(){
    String invalidArrive = "01/01/1111 111:11";
    createFlightWithArrive(invalidArrive);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because arrive contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenArriveNotValid(){
    String invalidArrive = "ab/bc/defg hi:jk";
    createFlightWithDepart(invalidArrive);
  }

  //FINALIZED TEST PROPER

  /**
   * creates a new Flight object, the values of src, dept, dest, and arrive should match their respective function arguments
   * @param src
   * @param dept
   * @param dest
   * @param arrive
   * @return
   */
  private Flight createFlightFinal(String src, String dept,  String dest, String arrive){
    return new Flight(00, src, dept, dest, arrive);
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT
   */
  @Test
  public void properFinalTest(){
    String validSrc = "pdx";
    String validDepart = "01/01/1111 11:11";
    String validDest = "sfx";
    String validArrive = "02/02/2222 22:22";
    createFlightFinal(validSrc, validDepart, validDest, validArrive);
  }
}
