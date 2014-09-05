package com.chorankates.bartpi;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


/**
 * Created by conor on 9/4/14.
 */

public class Departures {

    public List<Departure> departureCollection = new ArrayList<Departure>();
    
    public Departure getDeparture(int index) {
    	return departureCollection.get(index);
    }
    
    public Departures (String xml) {

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

                if (element.getTagName().equals("schedule")) {
                    NodeList childNodes = element.getChildNodes();

                    for (int j = 0; j < childNodes.getLength(); j++) {
                        Element childElement = (Element) childNodes.item(j);

                        System.out.println(String.format("childElement: %s",
                                childElement.getTagName()));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

