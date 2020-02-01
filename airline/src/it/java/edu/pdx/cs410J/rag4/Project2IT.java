package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project2} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
    public void testNoCommandLineArguments() {
      MainMethodResult result = invokeMain();
      assertThat(result.getExitCode(), equalTo(1));
      assertThat(result.getTextWrittenToStandardError(), containsString("There are NO command line arguments.\n"));
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
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 2: STORING AN AIRLINE IN A TEXT FILE\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 29, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 2, my objective is to implement the AirlineDumper interface with new java class TextDumper, \n" +
            "implement the AirlineParser interface with new java class TextParser, and to refactor Project 1 --> Project 2 for new \n" +
            "option: -textFile file. The purpose of the TextDumper class is to take an airline and its flights, and dump those contents \n" +
            "into a file. The purpose of the TextParser class is to take a text file that holds an airline's contents and its flights, \n" +
            "and to create and return a new airline from those contents. In the Project 2 class, when the user puts the option -textFile file \n" +
            "in the command line, it can do one of two things: " +
            "\n1) It will create a new file to dump an airline in " +
            "\n2) It will parse the airline in an existing text file, add a new flight only if the flight is from the same airline , then dump it back in \n\n"));
  }

  /**
   *  Tests that invoking the main method properly issues the program to print a README text and exit normally with code 1,
   *  -README flag located as 2nd command line argument
   */
  @Test
  public void readMeTestSecondArg() {
    MainMethodResult result = invokeMain("-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 2: STORING AN AIRLINE IN A TEXT FILE\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 29, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 2, my objective is to implement the AirlineDumper interface with new java class TextDumper, \n" +
            "implement the AirlineParser interface with new java class TextParser, and to refactor Project 1 --> Project 2 for new \n" +
            "option: -textFile file. The purpose of the TextDumper class is to take an airline and its flights, and dump those contents \n" +
            "into a file. The purpose of the TextParser class is to take a text file that holds an airline's contents and its flights, \n" +
            "and to create and return a new airline from those contents. In the Project 2 class, when the user puts the option -textFile file \n" +
            "in the command line, it can do one of two things: " +
            "\n1) It will create a new file to dump an airline in " +
            "\n2) It will parse the airline in an existing text file, add a new flight only if the flight is from the same airline , then dump it back in \n\n"));
  }

  /***
   * with -textFile option, check for too less arguments
   */
  @Test
  public void testTextFileTooLessCommandLineArguments() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "pdx");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
  }

  /***
   * with -textFile option, check for too many arguments
   */
  @Test
  public void testTextFileTooManyCommandLineArguments() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22", "heyyy");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }

  /***
   * test if -textFile option, it will exit with no errors
   */
  @Test
  public void testTextFileOption(){
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    File file = new File("Example.txt");
    if(file.delete()){
      System.out.println("Test textTextFileOption() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test textTextFileOption() failed.");
    }
  }

  /***
   * if -textFile has bad name, throw exception
   */
  @Ignore
  @Test(expected = IllegalArgumentException.class)
  public void testTextFileOptionBadName(){
    MainMethodResult result = invokeMain("-textFile", "(#", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    File file = new File("Example.txt");
    if(file.delete()){
      System.out.println("Test textTextFileOptionBadName() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test textTextFileOptionBadName() failed.");
    }
  }

  /***
   * test if -textFile option and a file exists, it will exit with no errors
   */
  @Test
  public void testTextFileOptionIfFileExists(){
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    MainMethodResult secondresult = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    assertThat(secondresult.getExitCode(), equalTo(1));
    File file = new File("Example.txt");
    if(file.delete()){
      System.out.println("Test testTextFileOptionIfFileExists() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test testTextFileOptionIfFileExists() failed.");
    }
  }

  /***
   * test if -textFile option and a file exists but bad name, throws exception
   */
  @Ignore
  @Test(expected = IllegalArgumentException.class)
  public void testTextFileOptionIfFileExistsBadName(){
    MainMethodResult result = invokeMain("-textFile", "Ex##@le", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    MainMethodResult secondresult = invokeMain("-textFile", "Ex##@le", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    assertThat(secondresult.getExitCode(), equalTo(1));
    File file = new File("Ex##@le.txt");
    if(file.delete()){
      System.out.println("Test testTextFileOptionIfFileExistsBadName() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test testTextFileOptionIfFileExistsBadName() failed.");
    }
  }

  // test what happens if you add an airline of a different name to an existing file with an airline
  @Test
  public void testTextFileOptionIfFileExistsDifferentAirline(){
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    try {
      MainMethodResult secondresult = invokeMain("-textFile", "Example.txt", "TEST2", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
      assertThat(secondresult.getExitCode(), equalTo(1));
    } catch (IllegalArgumentException e) {
      //IllegalArgumentException Caught / Expected
    }
    File file = new File("Example.txt");
    if(file.delete()){
      System.out.println("Test testTextFileOptionIfFileExistsDifferentAirline() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test testTextFileOptionIfFileExistsDifferentAirline() failed.");
    }
  }

  // test -print option will work, even if -textFile is on
  @Test
  public void testTextFilePrintOption(){
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    File file = new File("Example.txt");
    if(file.delete()){
      System.out.println("Test testTextFilePrintOption() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test testTextFilePrintOption() failed.");
    }
    assertThat(result.getExitCode(), equalTo(1));
        /*assertThat(result.getTextWrittenToStandardOut(), containsString("AIRLINE: TEST1\n" +
                "Flight 11 departs PDX at 11/11/1111 11:11 arrives SFX at 22/22/2222 22:22\n" +
                "FILE DOESN'T EXIST, CREATING NEW FILE\n"));*/
  }

  // test -print option will work, even if -textFile is on and a file already exists
  @Test
  public void testTextFilePrintOptionIfFileExists(){
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    MainMethodResult secondresult = invokeMain("-textFile", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
    File file = new File("Example.txt");
    if(file.delete()){
      System.out.println("Test testTextFilePrintOptionIfFileExists() Passed. Deleting Example.txt file.");
    }else{
      System.out.println("Test testTextFilePrintOptionIfFileExists() failed.");
    }
    assertThat(secondresult.getExitCode(), equalTo(1));
        /*assertThat(secondresult.getTextWrittenToStandardOut(), containsString("AIRLINE: TEST1\n" +
                "Flight 11 departs PDX at 11/11/1111 11:11 arrives SFX at 22/22/2222 22:22\n" +
                "FILE EXISTS\n"));*/
  }

  /***
   * test README will invoke and nothing else happens
   */
  @Test
  public void testTextFileReadMeOption(){
    MainMethodResult result = invokeMain("-textFile", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 2: STORING AN AIRLINE IN A TEXT FILE\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 29, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 2, my objective is to implement the AirlineDumper interface with new java class TextDumper, \n" +
            "implement the AirlineParser interface with new java class TextParser, and to refactor Project 1 --> Project 2 for new \n" +
            "option: -textFile file. The purpose of the TextDumper class is to take an airline and its flights, and dump those contents \n" +
            "into a file. The purpose of the TextParser class is to take a text file that holds an airline's contents and its flights, \n" +
            "and to create and return a new airline from those contents. In the Project 2 class, when the user puts the option -textFile file \n" +
            "in the command line, it can do one of two things: " +
            "\n1) It will create a new file to dump an airline in " +
            "\n2) It will parse the airline in an existing text file, add a new flight only if the flight is from the same airline , then dump it back in \n\n"));
  }

  /***
   * test README will invoke and nothing else happens
   */
  @Test
  public void testTextFilePrintReadMeOption(){
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 2: STORING AN AIRLINE IN A TEXT FILE\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: January 29, 2019 before 5:30PM\n" +
            "DESCRIPTION: In Project 2, my objective is to implement the AirlineDumper interface with new java class TextDumper, \n" +
            "implement the AirlineParser interface with new java class TextParser, and to refactor Project 1 --> Project 2 for new \n" +
            "option: -textFile file. The purpose of the TextDumper class is to take an airline and its flights, and dump those contents \n" +
            "into a file. The purpose of the TextParser class is to take a text file that holds an airline's contents and its flights, \n" +
            "and to create and return a new airline from those contents. In the Project 2 class, when the user puts the option -textFile file \n" +
            "in the command line, it can do one of two things: " +
            "\n1) It will create a new file to dump an airline in " +
            "\n2) It will parse the airline in an existing text file, add a new flight only if the flight is from the same airline , then dump it back in \n\n"));
  }
}