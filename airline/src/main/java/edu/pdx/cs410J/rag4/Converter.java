package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.ParserException;

import java.io.IOException;

public class Converter {
    private final String textContent;
    private final String xmlContent;

    public Converter(String textContent, String xmlContent) {
        this.textContent = textContent;
        this.xmlContent = xmlContent;
    }

    public void convert() throws ParserException, IOException {
        TextParser textToConvert = new TextParser(this.textContent);
        Airline airline = (Airline) textToConvert.parse();

        XmlDumper airlineToDump = new XmlDumper(this.xmlContent);
        airlineToDump.dump(airline);
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
        if(!args[0].contains(".txt")){
            System.err.println("First command line arguments needs to be .txt");
            commandLineInterface();
            System.exit(1);
        }
        if(!args[1].contains(".xml")){
            System.err.println("Second command line arguments needs to be .xml");
            commandLineInterface();
            System.exit(1);
        }
        System.out.println("Converting text file to xml file...");
        Converter toConvert = new Converter(args[0], args[1]);
        toConvert.convert();
        System.out.println("Done converting");
        System.exit(1);
    }

    /**Command Line Interface
     * prints out the command line interface, should be used when improper usage of the command line arguments in the main function occurs
     */
    public static void commandLineInterface(){
        System.out.println("usage: java edu.pdx.cs410J.<login-id>.Converter textFile xmlFile");
    }
}
