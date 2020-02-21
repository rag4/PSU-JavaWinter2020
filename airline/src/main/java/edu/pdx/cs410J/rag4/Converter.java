package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;

public class Converter {
    private String textContent = null;
    private String xmlContent = null;

    public Converter(String textContent, String xmlContent) {
        String error = null;
        try {
            if (!textContent.contains(".txt")) {
                error = "FIRST COMMAND LINE ARGUMENT NEEDS TO BE .TXT";
                throw new IllegalArgumentException();
            }
            if (!xmlContent.contains(".xml")) {
                error = "SECOND COMMAND LINE ARGUMENT NEEDS TO BE .XML";
                throw new IllegalArgumentException();
            }
            this.textContent = textContent;
            this.xmlContent = xmlContent;
        } catch (IllegalArgumentException e) {
            System.err.println(error);
            throw new IllegalArgumentException();
        }
    }

    public void convert() throws ParserException, IOException {
        try {
            TextParser textToConvert = new TextParser(this.textContent);
            Airline airline = (Airline) textToConvert.parse();
            XmlDumper airlineToDump = new XmlDumper(this.xmlContent);
            airlineToDump.dump(airline);
        }catch(NullPointerException e){
            System.err.println("FILE DOES NOT EXISTS");
            throw new IllegalArgumentException();
        }


    }

    public static void main(String[] args) throws IOException, ParserException {
        //error if there are NO command line arguments
        if(args.length <= 0) {
            System.err.println("THERE ARE NO COMMAND LINE ARGUMENTS.\n");
            commandLineInterface();
            System.exit(1);
        }
        if(args.length > 2) {
            System.err.println("THERE ARE TOO MANY COMMAND LINE ARGUMENTS.\n");
            commandLineInterface();
            System.exit(1);
        }
        try {
            System.out.println("Converting text file to xml file...");
            Converter toConvert = new Converter(args[0], args[1]);
            toConvert.convert();
            System.out.println("Done converting");
            System.exit(1);
        }catch(IllegalArgumentException e){
            System.err.println("COULD NOT CONVERT");
            commandLineInterface();
            System.exit(1);
        }
    }

    /**Command Line Interface
     * prints out the command line interface, should be used when improper usage of the command line arguments in the main function occurs
     */
    public static void commandLineInterface(){
        System.out.println("usage: java edu.pdx.cs410J.<login-id>.Converter textFile xmlFile");
    }
}
