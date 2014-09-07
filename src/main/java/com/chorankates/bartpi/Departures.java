package com.chorankates.bartpi;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Created by conor on 9/4/14.
 */

public class Departures {

    public List<Departure> departureCollection = new ArrayList<Departure>();
    Logger log = Logger.getLogger(Departures.class.getName());

    public Departure getDeparture(int index) {
        return departureCollection.get(index);
    }

    public List<Departure> getDepartures() {
        return departureCollection;
    }

    public Departures(String xml) {

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

                                    newDeparture.origin = grandChildElement.getAttributeNode("origin").getNodeValue();
                                    newDeparture.destination = grandChildElement.getAttributeNode("destination")
                                            .getNodeValue();
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
                                                    .getNodeValue(); // TODO
                                                                     // this is
                                                                     // a code,
                                                                     // should
                                                                     // we auto
                                                                     // convert?
                                                                     // no - do
                                                                     // that on
                                                                     // render
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

                                    this.addDeparture(newDeparture);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addDeparture(Departure departure) {
        log.debug(departure.toString());
        log.info(String.format("adding departure[%s] [%s->%s]", departureCollection.size(), departure.getOrigin(),
                departure.getDestination()));

        departureCollection.add(departure);
    }
}
