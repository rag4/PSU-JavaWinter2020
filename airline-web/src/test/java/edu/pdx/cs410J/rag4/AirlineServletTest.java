package edu.pdx.cs410J.rag4;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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


    AirlineServlet servlet = createFlight(airlineName, flightNumber);

    Airline airline = servlet.getAirline(airlineName);
    assertThat(airline, not(nullValue()));

    Flight flight = airline.getFlights().iterator().next();
    assertThat(flight.getNumber(), equalTo(flightNumber));
  }

  private AirlineServlet createFlight(String airlineName, int flightNumber) throws IOException, ServletException {
    AirlineServlet servlet = new AirlineServlet();
    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("airline")).thenReturn(airlineName);
    when(request.getParameter("flightNumber")).thenReturn(String.valueOf(flightNumber));

    HttpServletResponse response = mock(HttpServletResponse.class);

    PrintWriter pw = mock(PrintWriter.class);
    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    return servlet;
  }

  @Test
  public void getAirlineAsXml() throws IOException, ServletException {
    String airlineName = "TEST AIRLINE";
    int flightNumber = 123;

    AirlineServlet servlet = createFlight(airlineName, flightNumber);

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("airline")).thenReturn(airlineName);

    HttpServletResponse response = mock(HttpServletResponse.class);

    ArgumentCaptor<String> textWrittenToWriter = ArgumentCaptor.forClass(String.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);


    servlet.doGet(request, response);
    
    verify(pw).println(textWrittenToWriter.capture());
    String xml = textWrittenToWriter.getValue();
    assertThat(xml, containsString(airlineName));
    assertThat(xml, containsString(String.valueOf(flightNumber)));
  }
}
