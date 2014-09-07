package com.chorankates.bartpi;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// TODO make a better toString

/**
 * Created by conor on 9/4/14.
 */
public class Stations {

    private HashMap<String, Station> stationCollection = new HashMap<String, Station>();
    private static Logger log = Logger.getLogger(Stations.class.getName());

    public Station getStation(String name) {
        return stationCollection.get(name);
    }

    public HashMap<String, Station> getStationCollection() {
        return stationCollection;
    }

    public ArrayList<Station> getStations() {
        ArrayList<Station> results = new ArrayList<Station>();

        for (String stationName : stationCollection.keySet()) {
            results.add(stationCollection.get(stationName));
        }

        return results;
    }

    public ArrayList<String> getStationNames() {
        ArrayList<String> results = new ArrayList<String>();

        for (String name : stationCollection.keySet()) {
            Station station = stationCollection.get(name);
            results.add(station.getName());
        }

        return results;
    }

    public ArrayList<String> getStationAbbreviations() {
        ArrayList<String> results = new ArrayList<String>();

        for (String name : stationCollection.keySet()) {
            Station station = stationCollection.get(name);
            results.add(station.getAbbreviation());
        }

        return results;
    }

    public String stationAbbreviationToName(String stationAbbreviation) throws IOException {
        for (String name : stationCollection.keySet()) {
            Station station = stationCollection.get(name);
            if (station.getAbbreviation().equals(stationAbbreviation)) {
                return station.getName();
            }
        }

        throw new IOException(String.format("unable to find station name from abbreviation [%s]", stationAbbreviation));
    }

    public String stationNameToAbbreviation(String stationName) throws IOException {
        for (String name : stationCollection.keySet()) {
            Station station = stationCollection.get(name);
            if (station.getName().equals(stationName)) {
                return station.getAbbreviation();
            }
        }
        throw new IOException(String.format("unable to find station abbreviation from name [%s]", stationName));
    }

    public Stations(String xml) {

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = db.parse(new InputSource(new StringReader(xml)));

            NodeList nodes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                log.trace(String.format("element: %s", element.getTagName()));

                if (element.getTagName().equals("stations")) {
                    NodeList childNodes = element.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Element childElement = (Element) childNodes.item(j);

                        log.trace(String.format("childElement: %s", childElement.getTagName()));

                        NodeList grandChildNodes = childElement.getChildNodes();
                        Station newStation = new Station();

                        for (int k = 0; k < grandChildNodes.getLength(); k++) {
                            Element grandChildElement = (Element) grandChildNodes.item(k);
                            log.trace(String.format("grandChildElement: %s:%s", grandChildElement.getTagName(),
                                    grandChildElement.getTextContent()));

                            // TODO make this a switch
                            if (grandChildElement.getTagName().equals("name")) {
                                newStation.name = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("abbr")) {
                                newStation.abbreviation = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("gtfs_latitude")) {
                                newStation.latitude = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("gtfs_longitude")) {
                                newStation.longitude = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("address")) {
                                newStation.address = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("city")) {
                                newStation.city = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("county")) {
                                newStation.county = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("state")) {
                                newStation.state = grandChildElement.getTextContent();
                            } else if (grandChildElement.getTagName().equals("zipcode")) {
                                newStation.zipcode = grandChildElement.getTextContent();
                            }

                        }

                        stationCollection.put(newStation.getName(), newStation);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
