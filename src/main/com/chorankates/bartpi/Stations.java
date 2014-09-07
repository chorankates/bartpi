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

    HashMap<String, Station> stationCollection   = new HashMap<String, Station>();
    HashMap<String, String> stationNames         = new HashMap<String, String>();
    HashMap<String, String> stationAbbreviations = new HashMap<String, String>();
    
    Logger log = Logger.getLogger(Stations.class.getName());

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
        return new ArrayList<String>(stationNames.keySet());
    }

    public ArrayList<String> getStationAbbreviations() {
    	return new ArrayList<String>(stationAbbreviations.keySet());
    }

    public String stationAbbreviationToName(String stationAbbreviation) throws IOException {
        if (stationAbbreviations.containsKey(stationAbbreviation)) {
        	return stationAbbreviations.get(stationAbbreviation);
        }

        throw new IOException(String.format("unable to find station name from abbreviation [%s]", stationAbbreviation));
    }


	public String stationAbbreviation(String station) {
        if (stationAbbreviations.containsKey(station)) {
            return station;
        } else {
            return stationNames.get(station);
        }
	}

    public String stationNameToAbbreviation(String stationName) throws IOException {
        if (stationNames.containsKey(stationName)) {
        	return stationNames.get(stationName);        	
        }
    	
        throw new IOException(String.format("unable to find station abbreviation from name [%s]", stationName));
    }

	public String stationName(String station) {
        if (stationNames.containsKey(station)) {
            return station;
        } else {
            return stationAbbreviations.get(station);
        }
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

                            String value =  grandChildElement.getTextContent();

                            // TODO make this a switch - compiler complaining we can't switch on a string - is that only 1.8?
                            if (grandChildElement.getTagName().equals("name")) {
                                log.trace(String.format("adding [name:%s]", value));
                                newStation.name = value;
                            } else if (grandChildElement.getTagName().equals("abbr")) {
                                log.trace(String.format("adding [abbr:%s]", value));
                                newStation.abbreviation = value;
                            } else if (grandChildElement.getTagName().equals("gtfs_latitude")) {
                                log.trace(String.format("adding [gtfs_latitude:%s]", value));
                                newStation.latitude = value;
                            } else if (grandChildElement.getTagName().equals("gtfs_longitude")) {
                                log.trace(String.format("adding [gtfs_longitude:%s]", value));
                                newStation.longitude = value;
                            } else if (grandChildElement.getTagName().equals("address")) {
                                log.trace(String.format("adding [address:%s]", value));
                                newStation.address = value;
                            } else if (grandChildElement.getTagName().equals("city")) {
                                log.trace(String.format("adding [city:%s]", value));
                                newStation.city = value;
                            } else if (grandChildElement.getTagName().equals("county")) {
                                log.trace(String.format("adding [county:%s]", value));
                                newStation.county = value;
                            } else if (grandChildElement.getTagName().equals("state")) {
                                log.trace(String.format("adding [state:%s]", value));
                                newStation.state = value;
                            } else if (grandChildElement.getTagName().equals("zipcode")) {
                                log.trace(String.format("adding [zipcode:%s]", value));
                                newStation.zipcode = value;
                            }

                        }

                        stationCollection.put(newStation.getName(), newStation);
                        stationNames.put(newStation.getName(), newStation.getAbbreviation());
                        stationAbbreviations.put(newStation.getAbbreviation(), newStation.getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
