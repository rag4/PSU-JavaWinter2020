package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class XmlDumper implements AirlineDumper<Airline> {
    private final PrintWriter pw;

    public XmlDumper(PrintWriter pw) {
        this.pw = pw;

    }

    @Override
    public void dump(Airline airline) throws IOException {
        String xml = airline.getName() + " " + airline.getFlights().iterator().next().getNumber();
        this.pw.println(xml);
    }
}
