package com.chorankates.bartpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// TODO proper logging

public class BartPI {

	private final String USER_AGENT = "Mozilla/5.0";
	public String BARTendpoint = "http://api.bart.gov/api";
	private String BARTkey = "MW9S-E7SL-26DU-VV8V"; // don't worry, it's public
	public Stations stations = null;

	// TODO we should probably get station information during object instantiation
	BartPI() {
        System.out.println(String.format("using shared key[%s]", this.BARTkey));
        stations = this.getStations();
	}

	BartPI(String key) {
		this.BARTkey = key;
		System.out.println(String.format("using key[%s]", this.BARTkey));
        stations = this.getStations();
	}

	// TODO use station names? how can we lookup station abbrevs/names here without doing another lookup? or can make it a class attribute
	public Arrivals arrivals(String origin, String destination) {

        // if passed the name, convert to abbreviation -- if not a known name, assume abbrevivated already
        String originAbbreviation      = stations.getStationNames().contains(origin) ? stations.stationNameToAbbreviation(origin) : origin;
        String destinationAbbreviation = stations.getStationNames().contains(destination) ? stations.stationNameToAbbreviation(destination) : destination;

		// TODO should probably have a way for users to control these (or at least trips before/after).. probably a global setting, so should make it part of this object
		String time = "now";
		int howManyTripsBefore = 0;
		int howManyTripsAfter = 3;

		String url = String.format(
				"cmd=arrive&orig=%s&dest=%s&time=%s&b=%s&a=%s", originAbbreviation,
				destinationAbbreviation, time, howManyTripsBefore, howManyTripsAfter);
		String xml = null;

		try {
			xml = callBART("sched", url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new Arrivals(xml);
	}

	public Departures departures(String origin, String destination) {

        // if passed the name, convert to abbreviation -- if not a known name, assume abbreviated already
        String originAbbreviation      = stations.getStationNames().contains(origin) ? stations.stationNameToAbbreviation(origin) : origin;
        String destinationAbbreviation = stations.getStationNames().contains(destination) ? stations.stationNameToAbbreviation(destination) : destination;

        // TODO same as above..
		String time = "now";
		int howManyTripsBefore = 0;
		int howManyTripsAfter = 3;

		String url = String.format(
				"cmd=depart&orig=%s&dest=%s&time=%s&b=%s&a=%s", originAbbreviation,
				destinationAbbreviation, time, howManyTripsBefore, howManyTripsAfter);
		String xml = null;
		
		try {
			xml = callBART("sched", url);			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new Departures(xml);
	}

	public String callBART(String method, String query)
			throws IOException {
		// callBART() with a query, the endpoint and key (and trailing &) will
		// be prepended to your query

		String url = String.format("%s/%s.aspx?%s&key=%s", this.BARTendpoint,
				method, query, this.BARTkey);

		System.out.println(String.format("callBART[%s]", url)); // or should we
																// be printing
																// query?

		// call something to convert stringy XML to an object of some sort
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET"); // optional default is GET
		con.setRequestProperty("User-Agent", USER_AGENT); // add request header

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println("Response : " + response.toString());

		return response.toString();
	}


	public Stations getStations() {
        // TODO smarter caching..
        if (this.stations != null) {
            return this.stations;
        }

        String response = null;

		try {
			response = callBART("stn", "cmd=stns");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return new Stations(response);
	}

}
