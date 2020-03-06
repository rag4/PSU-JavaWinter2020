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
        MainMethodResult result = invokeMain(Project5.class);
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString(Project5.MISSING_ARGS));
    }

    @Test
    public void test2EmptyServer() {
        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
    }

    @Test(expected = AirlineRestException.class)
    public void test3NoDefinitionsThrowsAppointmentBookRestException() throws Throwable {
        String word = "WORD";
        try {
            invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, word);

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

        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, airlineName, String.valueOf(flightNumber), src, depart, departTime, departTimeSymbol,
                dest, arrive, arriveTime, arriveTimeSymbol);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
    }

    @Test
    public void test5PrintAllFlightsGivenAirlineOnly() {
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

        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, airlineName, String.valueOf(flightNumber), src, depart, departTime, departTimeSymbol,
                dest, arrive, arriveTime, arriveTimeSymbol);

        result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, airlineName);
        System.out.println(result.getTextWrittenToStandardOut());
    }

    @Test
    public void test6BadOption() {
        String airlineName = "Airline";
        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, "-screw", airlineName);
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
    }

    @Test
    public void test7READMEOption() {
        String airlineName = "Airline";
        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, "-README", airlineName);
        assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 5: A REST-ful Airline Web Service\n" +
                "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
                "CLASS: CS410P Advanced Programmin with Java\n" +
                "TEACHER: David Whitlock\n" +
                "DUE DATE: March 4, 2020 before 5:30PM\n" +
                "DESCRIPTION: In Project 5, my job is to implement and make changes to the classes: Airline Servlet, Project5, and \n" +
                "AirlineRESTClient. The AirlineServlet class will provide REST access to Airline, which will be able to perform GET \n" +
                "and POST on particular URL parameters. GET will be able to return flights of an airline, or it will be able to return \n" +
                "particular direct flights. POST will be able to create or add a new flight to an airline that is stored on the servlet. \n" +
                "In Project5, I will implement the option -search, that will allow me to pretty print direct flights specified with only \n" +
                "the airline name, source code, and destination code. Otherwise, I can pretty print all of an airline's flights if I were \n" +
                "to only give the existing airline name. Finally, I will need to edit a few things in the AirlineRESTClient so action performed \n" +
                "between Project5 and AirlineServlet can function correctly. This means I will need to refactor some of the existing methods, such as \n" +
                "XmlDumper or XmlParser."));
    }

    @Test
    public void test8PrintOption() {
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

        MainMethodResult result = invokeMain(Project5.class, "-print", airlineName, String.valueOf(flightNumber), src, depart, departTime, departTimeSymbol,
                dest, arrive, arriveTime, arriveTimeSymbol);
        assertThat(result.getTextWrittenToStandardOut(), containsString("AIRLINE: Airline"));
        assertThat(result.getTextWrittenToStandardOut(), containsString("FLIGHT: 567 PDX 07/19/2020 1:02 pm ORD 07/19/2020 6:22 pm"));
    }

    @Test
    public void test9SearchOption() {
        String airlineName = "Airline";
        int flightNumber = 567;
        String src = "PDX";
        String dest = "ORD";

        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, "-search", airlineName, src, dest);
        assertThat(result.getTextWrittenToStandardOut(), containsString("Welcome to our catalogue viewer"));
    }

    @Test
    public void test10SearchOptionNoHostAndPort() {
        String airlineName = "Airline";
        int flightNumber = 567;
        String src = "PDX";
        String dest = "ORD";

        MainMethodResult result = invokeMain(Project5.class, "-search", airlineName, src, dest);
        assertThat(result.getTextWrittenToStandardError(), containsString("Cannot search without host name and port number"));
    }

    @Test
    public void test10PrintAndSearchOptions() {
        MainMethodResult result = invokeMain(Project5.class, "-search", "-print");
        assertThat(result.getTextWrittenToStandardError(), containsString("-print and -search cannot both be called"));
    }

    @Test
    public void test11TooManyOptions() {
        String airlineName = "Airline";
        int flightNumber = 567;
        String src = "PDX";
        String dest = "ORD";
        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", PORT, "-search", airlineName, src, dest, "yes");
        assertThat(result.getTextWrittenToStandardError(), containsString("Extraneous command line argument:"));
    }

    @Test
    public void test12PortIsntInteger() {
        String airlineName = "Airline";
        int flightNumber = 567;
        String src = "PDX";
        String dest = "ORD";
        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", "yes", "-search", airlineName, src, dest);
        assertThat(result.getTextWrittenToStandardError(), containsString("must be an integer"));
    }

    @Test
    public void test13NoSuchPort2() {
        String airlineName = "Airline";
        int flightNumber = 567;
        String src = "LAX";
        String dest = "LAX";

        MainMethodResult result = invokeMain(Project5.class, "-host", HOSTNAME, "-port", "1234", "-search", airlineName, src, dest);
        assertThat(result.getTextWrittenToStandardError(), containsString("While contacting server:"));
    }
}