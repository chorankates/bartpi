package com.chorankates.bartpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * Created by conor on 9/4/14.
 */
public class Stations {

    public HashMap<String, Station> stationCollection = new HashMap<String, Station>();

    public Station getStation(String name) {
        return stationCollection.get(name);
    }

    public Stations (String xml) {

        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            //Document doc = db.parse(is);
            Document doc = db.parse(new InputSource(new StringReader(xml)));

            NodeList nodes = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Element element = (Element) nodes.item(i);
                System.out.println(String.format("element: %s",
                        element.getTagName()));

                if (element.getTagName().equals("stations")) {
                    NodeList childNodes = element.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Element childElement = (Element) childNodes.item(j);

                        System.out.println(String.format("childElement: %s",
                                childElement.getTagName()));

                        NodeList grandChildNodes = childElement.getChildNodes();
                        Station newStation = new Station();

                        // TODO we can probably grab them by name here, right?
                        for (int k = 0; k < grandChildNodes.getLength(); k++) {
                            Element grandChildElement = (Element) grandChildNodes.item(k);
                            System.out.println(String.format("grandChildElement: %s:%s",
                                    grandChildElement.getTagName(),
                                    grandChildElement.getTextContent()));

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