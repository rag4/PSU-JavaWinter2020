package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class Project2IT extends InvokeMainTestCase {

    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("There are NO command line arguments.\n"));
    }

    @Test
    public void testTextFileTooLessCommandLineArguments() {
        MainMethodResult result = invokeMain("-textFile", "Example", "pdx");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("You have too FEW command line arguments. \n"));
    }

    @Test
    public void testTextFileTooManyCommandLineArguments() {
        MainMethodResult result = invokeMain("-textFile", "Example", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22", "heyyy");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardError(), containsString("You have far too MANY command line arguments. \n"));
    }

    @Test
    public void testTextFileOption(){
        MainMethodResult result = invokeMain("-textFile", "Example", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        assertThat(result.getExitCode(), equalTo(1));
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test textTextFileOption() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test textTextFileOption() failed.");
        }
    }

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

    @Test
    public void testTextFileOptionIfFileExists(){
        MainMethodResult result = invokeMain("-textFile", "Example", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        assertThat(result.getExitCode(), equalTo(1));
        MainMethodResult secondresult = invokeMain("-textFile", "Example", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        assertThat(secondresult.getExitCode(), equalTo(1));
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test testTextFileOptionIfFileExists() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test testTextFileOptionIfFileExists() failed.");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTextFileOptionIfFileExistsBadName(){
        MainMethodResult result = invokeMain("-textFile", "Ex##@le", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        assertThat(result.getExitCode(), equalTo(1));
        MainMethodResult secondresult = invokeMain("-textFile", "Example", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        assertThat(secondresult.getExitCode(), equalTo(1));
        File file = new File("Example.txt");
        if(file.delete()){
            System.out.println("Test testTextFileOptionIfFileExistsBadName() Passed. Deleting Example.txt file.");
        }else{
            System.out.println("Test testTextFileOptionIfFileExistsBadName() failed.");
        }
    }

    @Test
    public void testTextFileOptionIfFileExistsDifferentAirline(){
        MainMethodResult result = invokeMain("-textFile", "Example", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        assertThat(result.getExitCode(), equalTo(1));
        try {
            MainMethodResult secondresult = invokeMain("-textFile", "Example", "TEST2", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
            assertThat(secondresult.getExitCode(), equalTo(-1));
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

    @Test
    public void testTextFilePrintOption(){
        MainMethodResult result = invokeMain("-textFile", "Example", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
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

    @Test
    public void testTextFilePrintOptionIfFileExists(){
        MainMethodResult result = invokeMain("-textFile", "Example", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
        MainMethodResult secondresult = invokeMain("-textFile", "Example", "-print", "TEST1", "11", "PDX", "11/11/1111", "11:11", "SFX", "22/22/2222", "22:22");
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

    @Test
    public void testTextFileReadMeOption(){
        MainMethodResult result = invokeMain("-textFile", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 2: DESIGNING AN AIRLINE APPLICATION\n" +
                "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
                "CLASS: CS410P Advanced Programmin with Java\n" +
                "TEACHER: David Whitlock\n" +
                "DUE DATE: January 29, 2019 before 5:30PM\n" +
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

    @Test
    public void testTextFilePrintReadMeOption(){
        MainMethodResult result = invokeMain("-textFile", "Example", "-print", "-README", "Portland", "00", "pdx", "11/11/1111", "11:11", "sfx", "22/22/2222", "22:22");
        assertThat(result.getExitCode(), equalTo(1));
        assertThat(result.getTextWrittenToStandardOut(), containsString("PROJECT 2: DESIGNING AN AIRLINE APPLICATION\n" +
                "SUBMITION/DEVELOPED BY: Ramon Guarnes 942268924\n" +
                "CLASS: CS410P Advanced Programmin with Java\n" +
                "TEACHER: David Whitlock\n" +
                "DUE DATE: January 29, 2019 before 5:30PM\n" +
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