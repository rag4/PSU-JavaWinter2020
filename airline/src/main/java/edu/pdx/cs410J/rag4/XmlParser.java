package edu.pdx.cs410J.rag4;

import edu.pdx.cs410J.AbstractAirline;
import edu.pdx.cs410J.AirlineParser;
import edu.pdx.cs410J.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class XmlParser implements AirlineParser {
    private final String content;

    public XmlParser(String content) {
        this.content = content;
    }

    @Override
    public AbstractAirline parse() throws ParserException {
        String[] newCommandArgs = new String[5];
        String airlineName = null;
        ArrayList<Flight> flightArray = new ArrayList<Flight>();
        try{
            File file = new File(this.content);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);

            document.getDocumentElement().normalize();

            airlineName = document.getDocumentElement().getAttribute("Name");

            NodeList nodeList = document.getElementsByTagName("Flight");
            for (int i = 0; i < nodeList.getLength(); i++){
                Node nNode = nodeList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    newCommandArgs[0] = eElement.getAttribute("Number");
                    newCommandArgs[1] = eElement.getElementsByTagName("Source").item(0).getTextContent();
                    newCommandArgs[2] = eElement.getElementsByTagName("Departure").item(0).getTextContent();
                    newCommandArgs[3] = eElement.getElementsByTagName("Destination").item(0).getTextContent();
                    newCommandArgs[4] = eElement.getElementsByTagName("Arrival").item(0).getTextContent();
                }
                Flight flight = new Flight(Integer.parseInt(newCommandArgs[0]), newCommandArgs[1], newCommandArgs[2], newCommandArgs[3], newCommandArgs[4]);
                flightArray.add(flight);
            }
        } catch (Exception e) {
            System.err.println("FILE DOES NOT EXIST. CANNOT PARSE.");
            throw new IllegalArgumentException();
        }
        return new Airline(airlineName, flightArray);
    }
}
