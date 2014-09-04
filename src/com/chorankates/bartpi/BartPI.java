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
	public String BARTkey = "MW9S-E7SL-26DU-VV8V"; // don't worry, it's

	// public..

	BartPI() {
		System.out.println(String.format("using shared key[%s]", this.BARTkey));
	}

	BartPI(String key) {
		this.BARTkey = key;
		System.out.println(String.format("using key[%s]", this.BARTkey));
	}

	// TODO use station names?
	public void arrivals(String originCode, String destinationCode) {
		// TODO should probably have a way for users to control these..
		String time = "now";
		int howManyTripsBefore = 0;
		int howManyTripsAfter = 3;

		String url = String.format(
				"cmd=arrive&orig=%s&dest=%s&time=%s&b=%s&a=%s", originCode,
				destinationCode, time, howManyTripsBefore, howManyTripsAfter);

		try {
			String xml = callBART("sched", url);


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void departures(String originCode, String destinationCode) {
		String time = "now";
		int howManyTripsBefore = 0;
		int howManyTripsAfter = 3;

		String url = String.format(
				"cmd=depart&orig=%s&dest=%s&time=%s&b=%s&a=%s", originCode,
				destinationCode, time, howManyTripsBefore, howManyTripsAfter);

		try {
			String xml = callBART("sched", url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
