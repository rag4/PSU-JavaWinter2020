package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AbstractFlight;
import edu.pdx.cs410J.AirlineDumper;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class XmlDumper implements AirlineDumper {

    public final String content;

    public XmlDumper(String content) {
        this.content = content;
    }

    @Override
    public void dump(AbstractAirline airline) throws IOException {
        ArrayList<Flight> flightArray = (ArrayList<Flight>) airline.getFlights();

        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            //Airline
            Element airlineRoot = document.createElement("Airline");
            document.appendChild(airlineRoot);
            //AirlineName
            Attr airlineName = document.createAttribute("Name");
            airlineName.setValue(airline.getName());
            airlineRoot.setAttributeNode(airlineName);

            //Flight
            for(Flight f : flightArray) {
                String departureDate = f.getDepartureString().replace(",", "");
                String arrivalDate = f.getArrivalString().replace(",", "");
                String num = String.valueOf(f.getNumber());

                //Flight
                Element flightRoot = document.createElement("Flight");
                airlineRoot.appendChild(flightRoot);
                //FlightNumber
                Attr flightNumber = document.createAttribute("Number");
                flightNumber.setValue(num);
                flightRoot.setAttributeNode(flightNumber);
                //FlightSrc
                Element flightSrc = document.createElement("Source");
                flightSrc.appendChild(document.createTextNode(f.getSource()));
                flightRoot.appendChild(flightSrc);
                //FlightDepart
                Element flightDepart = document.createElement("Departure");
                flightDepart.appendChild(document.createTextNode(departureDate));
                flightRoot.appendChild(flightDepart);
                //FlightDest
                Element flightDest = document.createElement("Destination");
                flightDest.appendChild(document.createTextNode(f.getDestination()));
                flightRoot.appendChild(flightDest);
                //FlightArrival
                Element flightArrival = document.createElement("Destination");
                flightArrival.appendChild(document.createTextNode(arrivalDate));
                flightRoot.appendChild(flightArrival);
            }
            //create XML file
            //transform DOM to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(this.content));

            transformer.transform(domSource, streamResult);
            System.out.println("DONE");
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
