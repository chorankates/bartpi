package com.chorankates.bartpi;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by conor on 9/4/14.
 */
public class Departure extends Trip {

    private static Logger log = Logger.getLogger(Departure.class.getName());

    Departure (Route route) {
        this.route = route;
    }

    Departure () {

    }

    public static ArrayList<Trip> parseDeparturesXML(String xml) {

    	ArrayList<Trip> departures = new ArrayList<Trip>();
    	
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = db.parse(new InputSource(new StringReader(xml)));

            NodeList nodes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                log.trace(String.format("element: %s", element.getTagName()));

                if (element.getTagName().equals("schedule")) {
                    NodeList childNodes = element.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Element childElement = (Element) childNodes.item(j);

                        log.trace(String.format("childElement: %s", childElement.getTagName()));

                        if (childElement.getTagName().equals("request")) {
                            NodeList grandChildNodes = childElement.getChildNodes();

                            for (int k = 0; k < grandChildNodes.getLength(); k++) {
                                Element grandChildElement = (Element) grandChildNodes.item(k);

                                log.trace(String.format("grandChildElement: %s", grandChildElement.getTagName()));

                                NodeList greatGrandChildrenNodes = grandChildElement.getChildNodes();

                                if (grandChildElement.getTagName().equals("trip")) {

                                    Departure newDeparture = new Departure();

                                    newDeparture.setOrigin(grandChildElement.getAttributeNode("origin").getNodeValue());
                                    newDeparture.setDestination(grandChildElement.getAttributeNode("destination").getNodeValue());

                                    newDeparture.fare = grandChildElement.getAttributeNode("fare").getNodeValue();
                                    newDeparture.origTimeMin = grandChildElement.getAttributeNode("origTimeMin")
                                            .getNodeValue();
                                    newDeparture.origTimeDate = grandChildElement.getAttributeNode("origTimeDate")
                                            .getNodeValue();
                                    newDeparture.destTimeMin = grandChildElement.getAttributeNode("destTimeMin")
                                            .getNodeValue();
                                    newDeparture.destTimeDate = grandChildElement.getAttributeNode("destTimeDate")
                                            .getNodeValue();

                                    for (int l = 0; l < greatGrandChildrenNodes.getLength(); l++) {
                                        Element greatGrandChildElement = (Element) greatGrandChildrenNodes.item(l);

                                        log.trace(String.format("greatGrandChildElement: %s",
                                                greatGrandChildElement.getTagName()));

                                        if (greatGrandChildElement.getTagName().equals("leg")) {
                                            Leg newLeg = new Leg();

                                            newLeg.order = greatGrandChildElement.getAttributeNode("order")
                                                    .getNodeValue();
                                            newLeg.transferCode = greatGrandChildElement.getAttributeNode(
                                                    "transfercode").getNodeValue();
                                            newLeg.origin = greatGrandChildElement.getAttributeNode("origin")
                                                    .getNodeValue();
                                            newLeg.destination = greatGrandChildElement.getAttributeNode("destination")
                                                    .getNodeValue();
                                            newLeg.origTimeMin = greatGrandChildElement.getAttributeNode("origTimeMin")
                                                    .getNodeValue();
                                            newLeg.origTimeDate = greatGrandChildElement.getAttributeNode(
                                                    "origTimeDate").getNodeValue();
                                            newLeg.destTimeMin = greatGrandChildElement.getAttributeNode("destTimeMin")
                                                    .getNodeValue();
                                            newLeg.destTimeDate = greatGrandChildElement.getAttributeNode(
                                                    "destTimeDate").getNodeValue();
                                            newLeg.line = greatGrandChildElement.getAttributeNode("line")
                                                    .getNodeValue();
                                            newLeg.bikeFlag = greatGrandChildElement.getAttributeNode("bikeflag")
                                                    .getNodeValue();
                                            newLeg.trainHeadStation = greatGrandChildElement.getAttributeNode(
                                                    "trainHeadStation").getNodeValue();
                                            newLeg.trainIdx = greatGrandChildElement.getAttributeNode("trainIdx")
                                                    .getNodeValue();

                                            newDeparture.addLeg(newLeg);
                                        }
                                    }

                                    departures.add(newDeparture);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return departures;
    }
}