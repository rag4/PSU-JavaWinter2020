package edu.pdx.cs410J.rag4;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link AirlineServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
public class AirlineServletTest {

  @Test
  public void addingFlightToServletStoresAirlineWithFlight() throws ServletException, IOException {

    String airlineName = "TEST AIRLINE";
    int flightNumber = 123;
    String src = "PDX";
    String depart = "07/19/2020 1:02 pm";
    String dest = "ORD";
    String arrive = "07/19/2020 6:22 pm";

    AirlineServlet servlet = createFlight(airlineName, flightNumber, src, depart, dest, arrive);

    Airline airline = servlet.getAirline(airlineName);
    assertThat(airline, not(nullValue()));

    Flight flight = airline.getFlights().iterator().next();
    assertThat(flight.getNumber(), equalTo(flightNumber));
    assertThat(flight.getSource(), equalTo(src));
    assertThat(flight.getDepartureString(), equalTo(depart));
    assertThat(flight.getDestination(), equalTo(dest));
    assertThat(flight.getArrivalString(), equalTo(arrive));
  }

  @Test
  public void getAirlineAsXml() throws IOException, ServletException {
    String airlineName = "TEST AIRLINE";
    int flightNumber = 123;
    String src = "PDX";
    String depart = "07/19/2020 1:02 pm";
    String dest = "ORD";
    String arrive = "07/19/2020 6:22 pm";

    AirlineServlet servlet = createFlight(airlineName, flightNumber, src, depart, dest, arrive);

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("airline")).thenReturn(airlineName);

    HttpServletResponse response = mock(HttpServletResponse.class);

    StringWriter out = new StringWriter();
    PrintWriter pw = new PrintWriter(out);
    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    String xml = out.toString();

    assertThat(xml, containsString(airlineName));
    assertThat(xml, containsString(String.valueOf(flightNumber)));
    assertThat(xml, containsString(src));
    assertThat(xml, containsString(dest));


  }

  private AirlineServlet createFlight(String airlineName, int flightNumber, String src, String depart, String dest, String arrive) throws IOException, ServletException {
    AirlineServlet servlet = new AirlineServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("airline")).thenReturn(airlineName);
    when(request.getParameter("flightNumber")).thenReturn(String.valueOf(flightNumber));
    when(request.getParameter("src")).thenReturn(src);
    when(request.getParameter("depart")).thenReturn(depart);
    when(request.getParameter("dest")).thenReturn(dest);
    when(request.getParameter("arrive")).thenReturn(arrive);


    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    return servlet;
  }
}
