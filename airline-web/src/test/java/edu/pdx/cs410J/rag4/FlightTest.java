package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractFlight;
import org.junit.Ignore;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Flight} class.
 */


public class FlightTest {


  // test expects an unsupported operation exception if the getArrival() function in our Flight class has not been implemented
  @Ignore
  @Test(expected = UnsupportedOperationException.class)
  public void getArrivalStringNeedsToBeImplemented() throws ParseException {
    Flight flight = new Flight(00, "tst", "00/00/0000 00:00", "tst", "00/00/0000 00:000");
    flight.getArrivalString();
  }

  // test asserts that the flightNumber is 42 before implementing the getNumber() function in our Flight class
  @Test
  public void initiallyAllFlightsHaveTheSameNumber() throws ParseException {
    Flight flight = new Flight(42, "pdx", "00/00/0000 00:00 am", "abq", "00/00/0000 00:00 pm");
    assertThat(flight.getNumber(), equalTo(42));
  }

  // test asserts that the getDeparture() function will return null before implementing this function in our Flight class
  @Ignore
  @Test
  public void forProject1ItIsOkayIfGetDepartureTimeReturnsNull() throws ParseException {
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
  private Flight createFlightWithSrc(String src) throws ParseException {
    return new Flight(00, src, "00/00/0000 00:00 am", "abq", "00/00/0000 00:00 pm");
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the src
   */
  @Test
  public void properSrcTest() throws ParseException {
    String validSrc = "pdx";
    createFlightWithSrc(validSrc);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because src length is too small
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenSrcLessThanThreeLong() throws ParseException {
    String invalidSrc = "pd";
    createFlightWithSrc(invalidSrc);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because src length is too big
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenSrcMoreThanThreeLong() throws ParseException {
    String invalidSrc = "pdxt";
    createFlightWithSrc(invalidSrc);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because src contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenSrcIsntOnlyLetters() throws ParseException {
    String invalidSrc = "1/a";
    createFlightWithSrc(invalidSrc);
  }

  //DEPART

  /**
   * creates a new Flight object with the depart value of the object equaling the function argument
   * @param depart
   * @return
   */
  private Flight createFlightWithDepart(String depart) throws ParseException {
    return new Flight(00, "pdx", depart, "abq", "03/30/3333 00:00 pm");
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the depart
   */
  @Test
  public void properDepartTest() throws ParseException {
    String validDepart = "01/01/1111 11:11 am";
    createFlightWithDepart(validDepart);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because depart isn't correct size (specifically on the DATE)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDepartWrongFormatDate() throws ParseException {
    String invalidDepart = "01/01/11111 11:11";
    createFlightWithDepart(invalidDepart);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because depart isn't correct size (specifically on the TIME)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDepartWrongFormatTime() throws ParseException {
    String invalidDepart = "01/01/1111 111:11";
    createFlightWithDepart(invalidDepart);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because depart contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDepartNotValid() throws ParseException {
    String invalidDepart = "ab/bc/defg hi:jk";
    createFlightWithDepart(invalidDepart);
  }

  //DEST

  /**
   * creates a new Flight object with the dest value of the object equaling the function argument
   * @param dest
   * @return
   */
  private Flight createFlightWithDest(String dest) throws ParseException {
    return new Flight(00, "abq", "01/01/1111 00:00 am", dest, "02/02/2222 00:00 pm");
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the dest
   */
  @Test
  public void properDestTest() throws ParseException {
    String validDest = "PDX";
    createFlightWithDest(validDest);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because dest length is too small
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDestLessThanThreeLong() throws ParseException {
    String invalidDest = "sf";
    createFlightWithDest(invalidDest);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because dest length is too big
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDestMoreThanThreeLong() throws ParseException {
    String invalidDest = "abqt";
    createFlightWithDest(invalidDest);
  }

  /**
   * test creates a new Flight object, test should fail, expects an illegal argument exception because dest contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenDestIsntOnlyLetters() throws ParseException {
    String invalidDest = "1/a";
    createFlightWithDest(invalidDest);
  }

  //ARRIVE

  /**
   * creates a new Flight object with the arrive value of the object equaling the function argument
   * @param arrive
   * @return
   */
  private Flight createFlightWithArrive(String arrive) throws ParseException {
    return new Flight(00, "pdx", "03/03/3333 00:00 pm", "abq", arrive);
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT, ESPECIALLY the arrive
   */
  @Test
  public void properArriveTest() throws ParseException {
    String validArrive = "04/04/4444 11:11 am";
    createFlightWithArrive(validArrive);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because arrive isn't correct size (specifically on the DATE)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenArriveWrongFormatDate() throws ParseException {
    String invalidArrive = "01/01/11111 11:11";
    createFlightWithArrive(invalidArrive);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because arrive isn't correct size (specifically on the TIME)
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenArriveWrongFormatTime() throws ParseException {
    String invalidArrive = "01/01/1111 111:11";
    createFlightWithArrive(invalidArrive);
  }

  /**
   * test creates a new Flight object, expects an illegal argument exception because arrive contains invalid characters
   */
  @Test(expected = IllegalArgumentException.class)
  public void whenArriveNotValid() throws ParseException {
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
  private Flight createFlightFinal(String src, String dept, String dest, String arrive) throws ParseException {
    return new Flight(00, src, dept, dest, arrive);
  }

  /**
   * test creates a new Flight object, test should pass as everything is expected to be CORRECT
   */
  @Test
  public void properFinalTest() throws ParseException {
    String validSrc = "pdx";
    String validDepart = "01/01/1111 11:11 am";
    String validDest = "abq";
    String validArrive = "02/02/2222 12:22 am";
    createFlightFinal(validSrc, validDepart, validDest, validArrive);
  }

  @Test
  public void testSorting() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("pdx", "01/01/1111 11:11 am", "abq", "02/02/2222 12:22 am"));
    flightArray.add(createFlightFinal("pdx", "01/01/1111 10:10 pm", "abq", "02/02/2222 11:11 pm"));
    flightArray.add(createFlightFinal("pdx", "01/01/1111 10:10 am", "abq", "02/02/2222 11:11 am"));
    flightArray.add(createFlightFinal("abe", "01/01/1111 10:10 am", "abq", "02/02/2222 11:11 am"));

    Collections.sort(flightArray);

    for(AbstractFlight f : flightArray){
      System.out.println(f.getNumber() + " " + f.getSource() + " " + f.getDepartureString() + " " + f.getDestination() + " " + f.getArrivalString());
    }
  }

  @Test
  public void testAMPM() throws ParseException {
    DateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
    Date date = format.parse("11/22/1997 10:30 pm");
    System.out.println(date);
    System.out.println(format.format(date));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAPFail() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("pdx", "01/01/1111 11:11 dm", "abq", "02/02/2222 22:22 am"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMFail() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("pdx", "01/01/1111 11:11 dn", "abq", "02/02/2222 22:22 am"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void arriveBeforeArrive() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("pdx", "02/02/2222 22:22 am", "abq", "01/01/1111 11:11 pm"));
  }

  @Test
  public void getDifferenceMorning() throws ParseException {
    Flight flight = createFlightFinal("ABE", "02/02/2222 01:00 am", "ABQ", "02/02/2222 02:00 am");
    System.out.println(flight.getDifference());
  }
  @Test
  public void getDifferenceOneDay() throws ParseException {
    Flight flight = createFlightFinal("ABE", "02/02/2222 01:00 am", "ABQ", "02/03/2222 01:00 am");
    System.out.println(flight.getDifference());
  }
  @Test
  public void getDifferenceTwelve() throws ParseException {
    Flight flight = createFlightFinal("ABE", "02/02/2222 01:00 am", "ABQ", "02/02/2222 01:00 pm");
    System.out.println(flight.getDifference());
  }
  @Test
  public void getDifferenceMultipile() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("ABE", "01/01/1111 10:00 am", "ABQ", "01/01/1111 12:00 pm"));
    flightArray.add(createFlightFinal("ABE", "01/01/1111 10:00 am", "ABQ", "01/01/1111 10:00 pm"));
    flightArray.add(createFlightFinal("ABE", "01/01/1111 10:00 am", "ABQ", "01/02/1111 10:00 am"));
    flightArray.add(createFlightFinal("ABE", "01/01/1111 10:00 am", "ABQ", "01/02/1111 10:00 pm"));

    Collections.sort(flightArray);

    for(Flight f : flightArray){
      System.out.println(f.getDifference());
    }
  }

  @Test
  public void getSRCName() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("PDX", "01/01/1111 10:00 am", "ABQ", "01/01/1111 12:00 pm"));

    for(Flight f : flightArray){
      System.out.println(f.getSRCName());
    }
  }

  @Test
  public void getDESTName() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("ABE", "01/01/1111 10:00 am", "ABQ", "01/01/1111 12:00 pm"));

    for(Flight f : flightArray){
      System.out.println(f.getDESTName());
    }
  }

  @Test
  public void geteDepartureString() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("PDX", "01/01/1111 10:00 am", "ABQ", "01/01/1111 12:00 pm"));

    for(Flight f : flightArray){
      System.out.println(f.getDepartureString());
    }
  }

  @Test
  public void geteArrivalString() throws ParseException {
    ArrayList<Flight> flightArray  = new ArrayList<Flight>();
    flightArray.add(createFlightFinal("PDX", "01/01/1111 10:00 am", "ABQ", "01/01/1111 12:00 pm"));

    for(Flight f : flightArray){
      System.out.println(f.getArrivalString());
    }
  }

}

