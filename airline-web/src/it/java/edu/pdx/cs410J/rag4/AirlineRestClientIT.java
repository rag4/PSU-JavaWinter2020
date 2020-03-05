package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Integration test that tests the REST calls made by {@link AirlineRestClient}
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AirlineRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private AirlineRestClient newAirlineRestClient() {
    int port = Integer.parseInt(PORT);
    return new AirlineRestClient(HOSTNAME, port);
  }

  @Test
  public void test0RemoveAllDictionaryEntries() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    client.removeAllDictionaryEntries();
  }

  @Test
  public void test2addOneFlight() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    String airlineName = "TEST AIRLINE";
    int flightNumber = 234;
    client.addFlight(airlineName, flightNumber);

    String xml = client.getAirlineAsXml(airlineName);
    assertThat(xml, containsString(airlineName));
    assertThat(xml, containsString(String.valueOf(flightNumber)));
  }

  @Test
  public void test4MissingRequiredParameterReturnsPreconditionFailed() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    HttpRequestHelper.Response response = client.postToMyURL(Map.of());
    assertThat(response.getContent(), containsString(Messages.missingRequiredParameter("word")));
    assertThat(response.getCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
  }
}
