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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class XmlDumper implements AirlineDumper {

    public final String content;

    public XmlDumper(String content) {
        this.content = content;
    }

    @Override
    public void dump(AbstractAirline airline) throws IOException {
        Document doc = null;
        ArrayList<Flight> flightArray = (ArrayList<Flight>) airline.getFlights();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            DocumentType docType = dom.createDocumentType("airline", AirlineXmlHelper.PUBLIC_ID, AirlineXmlHelper.SYSTEM_ID);
            doc = dom.createDocument(null, "airline", docType);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            //Airline
            Element airlineRoot = doc.getDocumentElement();
            //Airline
            //Element airlineRoot = doc.createElement("airline");
            //doc.appendChild(airlineRoot);
            //AirlineName
            Element airlineName = doc.createElement("name");
            airlineName.appendChild(doc.createTextNode(airline.getName()));
            airlineRoot.appendChild(airlineName);

            //Flight
            for (Flight f : flightArray) {
                String departureDate = f.getLongDeparture().replace(",", "");
                departureDate = convertDate(departureDate);

                String arrivalDate = f.getLongArrival().replace(",", "");
                arrivalDate = convertDate(arrivalDate);

                String num = String.valueOf(f.getNumber());

                //Flight
                Element flightRoot = doc.createElement("flight");
                airlineRoot.appendChild(flightRoot);

                //FlightNumber
                Element flightNumber = doc.createElement("number");
                flightNumber.appendChild(doc.createTextNode(num));
                flightRoot.appendChild(flightNumber);

                //FlightSrc
                Element flightSrc = doc.createElement("src");
                flightSrc.appendChild(doc.createTextNode(f.getSource()));
                flightRoot.appendChild(flightSrc);

                //FlightDepart
                Element departRoot = doc.createElement("depart");
                appendDateTime(doc, departureDate, flightRoot, departRoot);

                //FlightDest
                Element flightDest = doc.createElement("dest");
                flightDest.appendChild(doc.createTextNode(f.getDestination()));
                flightRoot.appendChild(flightDest);

                //FlightArrival
                Element arriveRoot = doc.createElement("arrive");
                appendDateTime(doc, arrivalDate, flightRoot, arriveRoot);
            }
        } catch (DOMException e) {
            e.printStackTrace();
        }
        try {
            Source src = new DOMSource(doc);
            Result res = new StreamResult(System.out);
            TransformerFactory xFactory = TransformerFactory.newInstance();
            Transformer xForm = xFactory.newTransformer();
            xForm.setOutputProperty(OutputKeys.INDENT, "yes");
            xForm.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, AirlineXmlHelper.SYSTEM_ID);
            StreamResult streamResult = new StreamResult(new File(this.content));
            xForm.transform(src, streamResult);
        } catch (TransformerException ex) {
            ex.printStackTrace(System.err);
            System.exit(1);
        }

    }

    private void appendDateTime(Document doc, String dateString, Element flightRoot, Element dateRoot) {
        flightRoot.appendChild(dateRoot);

        Element departDate = doc.createElement("date");
        dateRoot.appendChild(departDate);

        Attr departDay = doc.createAttribute("day");
        departDay.setValue(dateString.substring(0,2));
        departDate.setAttributeNode(departDay);
        Attr departMonth = doc.createAttribute("month");
        departMonth.setValue(dateString.substring(3,5));
        departDate.setAttributeNode(departMonth);
        Attr departYear = doc.createAttribute("year");
        departYear.setValue(dateString.substring(6,10));
        departDate.setAttributeNode(departYear);

        Element departTime = doc.createElement("time");
        dateRoot.appendChild(departTime);

        Attr departHour = doc.createAttribute("hour");
        departHour.setValue(dateString.substring(11,13));
        departTime.setAttributeNode(departHour);
        Attr departMinute = doc.createAttribute("minute");
        departMinute.setValue(dateString.substring(14,16));
        departTime.setAttributeNode(departMinute);
    }

    private String convertDate(String departureDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
        DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = null;
        String output = null;
        try{
            date = df.parse(departureDate);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }
}
