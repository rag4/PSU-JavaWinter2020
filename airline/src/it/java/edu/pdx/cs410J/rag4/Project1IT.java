package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project1} main class.
 */
public class Project1IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project1.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
    public void testNoCommandLineArguments() {
      MainMethodResult result = invokeMain();
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("There are missing command line arguments.\n"));
  }

    /**
     * Tests that invoking the main method with too less arguments issues an error (WITHOUT ANY OPTION)
     */
  @Test
    public void testTooLessCommandLineArgumentsWithoutOption() {
      MainMethodResult result = invokeMain("Portland", "00", "pdx");
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
  }

  /**
   *  Tests that invoking the main method with too many arguments issues an error (WITHOUT ANY OPTION)
   */
  @Test
  public void testTooManyCommandLineArgumentsWithoutOption() {
      MainMethodResult result = invokeMain("Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22", "heyyy");
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }

    /**
     * Tests that invoking the main method with too less arguments issues an error (WITH OPTION)
     */
  @Test
  public void testTooLessCommandLineArgumentsWithOption() {
    MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
  }

    /**
     * Tests that invoking the main method with too less arguments issues an error (WITH OPTION)
     */
  @Test
  public void testTooManyCommandLineArgumentsWithOption() {
    MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22", "heyyy");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }

    /**
     * Tests that invoking the main method properly issues the program regularly and exits normally with code 1 (WITHOUT ANY OPTION)
     */
  @Test
  public void testProperCommandLineArgumentsWithoutOption() {
    MainMethodResult result = invokeMain("Portland", "00", "pdx", "11/11/1111 11:11", "sfx", "22/22/2222 22:22");
    assertThat(result.getExitCode(), equalTo(1));
  }

    /**
     * Tests that invoking the main method properly issues the program regularly and exits normally with code 1 (WITH OPTION)
     */
  @Test
  public void testProperCommandLineArgumentsWithOption() {
    MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 0 departs pdx at 11/11/1111 11:11 arrives sfx at 22/22/2222 22:22"));
  }

  /**
   *  Tests that invoking the main method properly issues the program to print a README text and exit normally with code 1,
   *  -README flag located as 1st command line argument
   */
  @Test
  public void readMeTestFirstArg() {
    MainMethodResult result = invokeMain("-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 1: DESIGNING AN AIRLINE APPLICATION\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 22, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 1, my objective is to extend the AbstractAirline class with the class Airline, \n" +
            "extend the AbstractFlight class with the class flight, implement assigned functionality for the main function \n" +
            "within Project1.java, and to create test suites for all of these classes/files. \n" +
            "\n" +
            "In the Airline class, aside from error handling, I implemented a constructor to consider the airline's name, and a \n" +
            "list of available flights the airline contains. In the Flight class, aside from error handling, I implemented a constructor\n" +
            "to consider the flight's unique number, three-letter source code, departure date and time, three-letter destination code, \n" +
            "and arrival date and time. Within the main function, the purpose of this function is to create an airline and flight to add\n" +
            "to the airline using the values of the user's input command line arguments. The user also has the option to print the descriptions\n" +
            "of the flight, as well as to be able to view this README."));
  }

  /**
   *  Tests that invoking the main method properly issues the program to print a README text and exit normally with code 1,
   *  -README flag located as 2nd command line argument
   */
  @Test
  public void readMeTestSecondArg() {
    MainMethodResult result = invokeMain("-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 1: DESIGNING AN AIRLINE APPLICATION\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 22, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 1, my objective is to extend the AbstractAirline class with the class Airline, \n" +
            "extend the AbstractFlight class with the class flight, implement assigned functionality for the main function \n" +
            "within Project1.java, and to create test suites for all of these classes/files. \n" +
            "\n" +
            "In the Airline class, aside from error handling, I implemented a constructor to consider the airline's name, and a \n" +
            "list of available flights the airline contains. In the Flight class, aside from error handling, I implemented a constructor\n" +
            "to consider the flight's unique number, three-letter source code, departure date and time, three-letter destination code, \n" +
            "and arrival date and time. Within the main function, the purpose of this function is to create an airline and flight to add\n" +
            "to the airline using the values of the user's input command line arguments. The user also has the option to print the descriptions\n" +
            "of the flight, as well as to be able to view this README."));
  }
}