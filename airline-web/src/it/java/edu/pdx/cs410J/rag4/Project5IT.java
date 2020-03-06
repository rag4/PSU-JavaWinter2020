package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.InvokeMainTestCase;
import edu.pdx.cs410J.rag4.AirlineRestClient.AirlineRestException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * An integration test for {@link Project5} that invokes its main method with
 * various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project5IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");

    @Test
    public void test0RemoveAllMappings() throws IOException {
      AirlineRestClient client = new AirlineRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllDictionaryEntries();
    }

    @Test
    public void test1NoCommandLineArguments() {
        MainMethodResult result = invokeMain( Project5.class );
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project5.MISSING_ARGS));
    }

    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain( Project5.class, HOSTNAME, PORT );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        String out = result.getTextWrittenToStandardOut();
        assertThat(out, out, containsString(Messages.formatWordCount(0)));
    }

    @Test(expected = AirlineRestException.class)
    public void test3NoDefinitionsThrowsAppointmentBookRestException() throws Throwable {
        String word = "WORD";
        try {
            invokeMain(Project5.class, HOSTNAME, PORT, word);

        } catch (IllegalArgumentException ex) {
            throw ex.getCause().getCause();
        }
    }

    @Test
    public void test4AddFlight() {
        String airlineName = "Airline";
        int flightNumber = 567;
        String src = "PDX";
        String depart = "07/19/2020";
        String departTime = "1:02";
        String departTimeSymbol = "pm";
        String dest = "ORD";
        String arrive = "07/19/2020";
        String arriveTime = "6:22";
        String arriveTimeSymbol = "pm";

        MainMethodResult result = invokeMain( Project5.class, HOSTNAME, PORT, airlineName, String.valueOf(flightNumber), src, depart, departTime, departTimeSymbol,
                dest, arrive, arriveTime, arriveTimeSymbol);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
    }

    @Test
    public void test5PrintAllFlightsGivenAirlineOnly() {
        String airlineName = "Airline";

        MainMethodResult result = invokeMain(Project5.class, HOSTNAME, PORT, airlineName);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
        System.out.println(result.getTextWrittenToStandardOut());
    }
}