package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;
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
  public void test1gettingDictionary() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    client.getAllDictionaryEntries();
  }

  @Test
  public void test2addOneFlight() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    String airlineName = "TEST AIRLINE";
    int flightNumber = 234;
    String src = "PDX";
    String depart = "07/19/2020 1:02 pm";
    String dest = "ORD";
    String arrive = "07/19/2020 6:22 pm";

    client.addFlight(airlineName, flightNumber, src, depart, dest, arrive);

    String xml = client.getAirlineAsXml(airlineName);
    System.out.println(xml);

    assertThat(xml, containsString(airlineName));
    assertThat(xml, containsString(String.valueOf(flightNumber)));
    assertThat(xml, containsString(src));
    assertThat(xml, containsString(dest));
  }


  @Test(expected = AirlineRestClient.AirlineRestException.class)
  public void test3getBadAirlineAsXml() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    String xml = client.getAirlineAsXml("TEST");
    System.out.println(xml);
  }

  @Test
  public void test4MissingRequiredParameterReturnsPreconditionFailed() throws IOException {
    AirlineRestClient client = newAirlineRestClient();
    HttpRequestHelper.Response response = client.postToMyURL(Map.of());
    assertThat(response.getContent(), containsString(Messages.missingRequiredParameter("airline")));
    assertThat(response.getCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
  }

  @Test
  public void test5postToMyUrl() throws IOException {
    String airlineName = "TEST AIRLINE";
    int flightNumber = 234;
    String src = "PDX";
    String depart = "07/19/2020 1:02 pm";
    String dest = "ORD";
    String arrive = "07/19/2020 6:22 pm";
    AirlineRestClient client = newAirlineRestClient();
    client.postToMyURL(Map.of("airline", airlineName, "src", src));
  }

  @Test(expected=AirlineRestClient.AirlineRestException.class)
  public void test6badFlight() throws IOException {
    String airlineName = "TEST AIRLINE";
    int flightNumber = 234;
    String src = "07/19/2020 1:02 pm";
    String depart = "07/19/2020 1:02 pm";
    String dest = "07/19/2020 1:02 pm";
    String arrive = "07/19/2020 6:22 pm";
    AirlineRestClient client = newAirlineRestClient();
    client.addFlight(src, flightNumber, src, src, src, src);
    client.removeAllDictionaryEntries();
  }


}
