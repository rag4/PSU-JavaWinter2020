package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * An integration test for the {@link Project4} main class.
 */
public class Project4IT extends InvokeMainTestCase {

  /**
   * Invokes the main method of {@link Project4} with the given arguments.
   */
  private MainMethodResult invokeMain(String... args) {
    return invokeMain(Project4.class, args);
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
   * Tests that invoking the main method with too many arguments issues an error (WITHOUT ANY OPTION)
   */
  @Test
  public void testTooManyCommandLineArgumentsWithoutOption() {
    MainMethodResult result = invokeMain("Portland", "00", "pdx", "11/11/1111", "11:11", "am", "lax", "22/22/2222", "22:22", "pm", "heyyy");
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
    MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx", "11/11/1111", "11:11", "am", "lax", "22/22/2222", "22:22", "pm", "heyyy");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }

  /**
   * Tests that invoking the main method properly issues the program regularly and exits normally with code 1 (WITHOUT ANY OPTION)
   */
  @Test
  public void testProperCommandLineArgumentsWithoutOption() {
    MainMethodResult result = invokeMain("Portland", "00", "pdx", "11/11/1111 11:11", "lax", "22/22/2222 22:22");
    assertThat(result.getExitCode(), equalTo(1));
  }

  /**
   * Tests that invoking the main method properly issues the program regularly and exits normally with code 1 (WITH OPTION)
   */
  @Ignore
  @Test
  public void testProperCommandLineArgumentsWithOption() {
    MainMethodResult result = invokeMain("-print", "Portland", "00", "pdx", "11/11/1111", "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("\nAIRLINE: Portland\n" +
            "Flight 0 departs pdx at 11/11/11, 11:11 AM arrives lax at 10/23/23, 10:22 AM\n"));
  }

  /**
   * Tests that invoking the main method properly issues the program to print a README text and exit normally with code 1,
   * -README flag located as 1st command line argument
   */
  @Test
  public void readMeTestFirstArg() {
    MainMethodResult result = invokeMain("-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
  }

  /**
   * Tests that invoking the main method properly issues the program to print a README text and exit normally with code 1,
   * -README flag located as 2nd command line argument
   */
  @Test
  public void readMeTestSecondArg() {
    MainMethodResult result = invokeMain("-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
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
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "Portland", "00", "pdx", "11/11/1111", "am", "11:11", "lax", "22/22/2222", "22:22", "pm", "heyyy");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }

  /***
   * test if -textFile option, it will exit with no errors
   */
  @Test
  public void testTextFileOption() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    File file = new File("Example.txt");
    if (file.delete()) {
      System.out.println("Test textTextFileOption() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test textTextFileOption() failed.");
    }
  }

  /***
   * if -textFile has bad name, throw exception
   */
  @Ignore
  @Test(expected = IllegalArgumentException.class)
  public void testTextFileOptionBadName() {
    MainMethodResult result = invokeMain("-textFile", "(#", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    File file = new File("Example.txt");
    if (file.delete()) {
      System.out.println("Test textTextFileOptionBadName() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test textTextFileOptionBadName() failed.");
    }
  }

  /***
   * test if -textFile option and a file exists, it will exit with no errors
   */
  @Test
  public void testTextFileOptionIfFileExists() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    MainMethodResult secondresult = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(secondresult.getExitCode(), equalTo(1));
    File file = new File("Example.txt");
    if (file.delete()) {
      System.out.println("Test testTextFileOptionIfFileExists() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test testTextFileOptionIfFileExists() failed.");
    }
  }

  /***
   * test if -textFile option and a file exists but bad name, throws exception
   */
  @Ignore
  @Test(expected = IllegalArgumentException.class)
  public void testTextFileOptionIfFileExistsBadName() {
    MainMethodResult result = invokeMain("-textFile", "Ex##@le", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    MainMethodResult secondresult = invokeMain("-textFile", "Ex##@le", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(secondresult.getExitCode(), equalTo(1));
    File file = new File("Ex##@le.txt");
    if (file.delete()) {
      System.out.println("Test testTextFileOptionIfFileExistsBadName() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test testTextFileOptionIfFileExistsBadName() failed.");
    }
  }

  // test what happens if you add an airline of a different name to an existing file with an airline
  @Test
  public void testTextFileOptionIfFileExistsDifferentAirline() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    try {
      MainMethodResult secondresult = invokeMain("-textFile", "Example.txt", "TEST2", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
      assertThat(secondresult.getExitCode(), equalTo(1));
    } catch (IllegalArgumentException e) {
      //IllegalArgumentException Caught / Expected
    }
    File file = new File("Example.txt");
    if (file.delete()) {
      System.out.println("Test testTextFileOptionIfFileExistsDifferentAirline() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test testTextFileOptionIfFileExistsDifferentAirline() failed.");
    }
  }

  // test -print option will work, even if -textFile is on
  @Test
  public void testTextFilePrintOption() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    File file = new File("Example.txt");
    if (file.delete()) {
      System.out.println("Test testTextFilePrintOption() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test testTextFilePrintOption() failed.");
    }
    assertThat(result.getExitCode(), equalTo(1));
        /*assertThat(result.getTextWrittenToStandardOut(), containsString("AIRLINE: TEST1\n" +
                "Flight 11 departs PDX at 11/11/1111 11:11 arrives lax at 22/22/2222 22:22\n" +
                "FILE DOESN'T EXIST, CREATING NEW FILE\n"));*/
  }

  // test -print option will work, even if -textFile is on and a file already exists
  @Test
  public void testTextFilePrintOptionIfFileExists() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    MainMethodResult secondresult = invokeMain("-textFile", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    File file = new File("Example.txt");
    if (file.delete()) {
      System.out.println("Test testTextFilePrintOptionIfFileExists() Passed. Deleting Example.txt file.");
    } else {
      System.out.println("Test testTextFilePrintOptionIfFileExists() failed.");
    }
    assertThat(secondresult.getExitCode(), equalTo(1));
        /*assertThat(secondresult.getTextWrittenToStandardOut(), containsString("AIRLINE: TEST1\n" +
                "Flight 11 departs PDX at 11/11/1111 11:11 arrives lax at 22/22/2222 22:22\n" +
                "FILE EXISTS\n"));*/
  }

  // test -print option will work, even if -textFile is on and a file already exists
  @Test
  public void testAllOptionsPrettyOut() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-pretty", "-", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
  }

  // test -print option will work, even if -textFile is on and a file already exists
  @Test
  public void testAllOptionsPrettyFile() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-pretty", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
  }

  /***
   * test README will invoke and nothing else happens
   */
  @Test
  public void testTextFileReadMeOption() {
    MainMethodResult result = invokeMain("-textFile", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
  }

  /***
   * test README will invoke and nothing else happens
   */
  @Test
  public void testTextFilePrintReadMeOption() {
    MainMethodResult result = invokeMain("-textFile", "Example.txt", "-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
  }

  @Test
  public void testPrettyReadMeOption() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
  }

  @Test
  public void testPrettyPrintReadMeOption() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "lax", "22/22/2222", "22:22");
    assertThat(result.getExitCode(), equalTo(1));
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
  }

  @Test
  public void testPrettyOutOption() {
    MainMethodResult result = invokeMain("-pretty", "-", "TEST1", "11", "PDX", "11/11/1111",  "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getExitCode(), equalTo(1));
  }
  @Test
  public void testPrettyOutOptionTooMany() {
    MainMethodResult result = invokeMain("-pretty", "-", "TEST1", "11", "PDX", "heyho",  "11/11/1111",  "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }
  @Test
  public void testPrettyOutOptionTooFew() {
    MainMethodResult result = invokeMain("-pretty", "-", "TEST1", "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
  }

  @Test
  public void testPrettyFileOption() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "TEST1", "11", "PDX", "11/11/1111",  "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void testPrettyOutPrintOption() {
    MainMethodResult result = invokeMain("-pretty", "-", "-print", "TEST1", "11", "PDX", "11/11/1111",  "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void testPrettyFilePrintOption() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void testPrettyFilePrintOptionTooMany() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "olo",  "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }
  @Test
  public void testPrettyFilePrintOptionTooFew() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "am", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
  }


  @Test
  public void testPrettyFilePrintReadMeOption() {
    MainMethodResult result = invokeMain("-pretty", "Example.txt", "-print", "-README", "TEST1", "11", "PDX", "11/11/1111", "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 4: XML\n" +
            "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
            "CLASS: CS410P Advanced Programmin with Java\n" +
            "TEACHER: David Whitlock\n" +
            "DUE DATE: February 19, 2020 before 5:30PM\n" +
            "DESCRIPTION: In Project 4, my job is to implement the classes: XmlDumper, XmlParser, and Converter. The XmlDumper class implements the \n" +
            "AirlineDumper class, and its purpose is to take an instance of an airline and its flights, and to store it into an external XML file.\n" +
            "Thew XmlParaser class implements the AirlineParser class, and its purpose is to read an airline instance from an XML file, and to create and\n" +
            "return an airline from it. The Converter class converts an airline from a text file, and stores it into an XML file.\n" +
            "The next step of this project is to reconfigure and refactor the Project 3 class --> Project 4 class. The Project 4 class should now include\n" +
            "the option -xmlFile and should take in one to two parameters (one xml file) (or a text file and an xml file in succession).\n\n"));
  }

  @Test
  public void testXmlFilePrintOption() {
    MainMethodResult result = invokeMain("-xmlFile", "Example.xml", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void testXmlFilePrintOptionTooMany() {
    MainMethodResult result = invokeMain("-xmlFile", "Example.xml", "-print", "TEST1", "11", "PDX", "11/11/1111", "olo",  "11:11", "am", "lax", "22/22/2222", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
  }
  @Test
  public void testXmlFilePrintOptionTooFew() {
    MainMethodResult result = invokeMain("-xmlFile", "Example.xml", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "am", "22:22", "pm");
    assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
  }
}