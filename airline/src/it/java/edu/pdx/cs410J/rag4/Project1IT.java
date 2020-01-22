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
      assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Test
    public void testTooLessCommandLineArgumentsWithoutOption() {
      MainMethodResult result = invokeMain("Portland", "00", "pdx");
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Test
  public void testTooManyCommandLineArgumentsWithoutOption() {
      MainMethodResult result = invokeMain("Portland", "00", "pdx", "11/11/1111 11:11", "sfx", "22/22/2222", "heyyy");
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("Too many command line arguments"));
  }

    @Test
    public void testTooLessCommandLineArgumentsWithOption() {
        MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testTooManyCommandLineArgumentsWithOption() {
        MainMethodResult result = invokeMain("-README", "Portland", "00", "pdx");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
    }

    @Test
    public void testProperCommandLineArgumentsWithoutOption() {
        MainMethodResult result = invokeMain("Portland", "00", "pdx", "11/11/1111 11:11", "sfx", "22/22/2222 22:22");
        assertThat(result.getExitCode(), equalTo(1));
    }

    @Test
    public void testProperCommandLineArgumentsWithOption() {
        MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx", "11/11/1111 11:11", "sfx", "22/22/2222 22:22");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("Flight 0 departs pdx at 11/11/1111 11:11 arrives sfx at 22/22/2222 22:22"));
    }

}