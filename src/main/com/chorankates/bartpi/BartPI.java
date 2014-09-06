package com.chorankates.bartpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;

public class BartPI {

    public final String BARTendpoint = "http://api.bart.gov/api";
    public Stations stations = null;

    Logger log = Logger.getLogger(BartPI.class.getName());

    private String BARTkey = "MW9S-E7SL-26DU-VV8V"; // don't worry, it's public
    private final String USER_AGENT = "Mozilla/5.0";

    // TODO we should probably get station information during object
    // instantiation
    BartPI() {
        System.out.println(String.format("using shared key[%s]", this.BARTkey));
        stations = this.getStations();
    }

    BartPI(String key) {
        this.BARTkey = key;
        log.debug(String.format("using key[%s]", this.BARTkey));
        stations = this.getStations();
    }

    public Arrivals getArrivals(String origin, String destination) throws IOException {

        // if passed the name, convert to abbreviation -- if not a known name,
        // assume abbreviated already
        String originAbbreviation = stations.getStationNames().contains(origin) ? stations
                .stationNameToAbbreviation(origin) : origin;
        String destinationAbbreviation = stations.getStationNames().contains(destination) ? stations
                .stationNameToAbbreviation(destination) : destination;

        // TODO should probably have a way for users to control these (or at
        // least trips before/after).. probably a global setting, so should make
        // it part of this object
        String time = "now";
        int howManyTripsBefore = 0;
        int howManyTripsAfter = 3;

        String url = String.format("cmd=arrive&orig=%s&dest=%s&time=%s&b=%s&a=%s", originAbbreviation,
                destinationAbbreviation, time, howManyTripsBefore, howManyTripsAfter);
        String xml = null;

        try {
            xml = callBART("sched", url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Arrivals(xml);
    }

    public Departures getDepartures(String origin, String destination) throws IOException {

        // if passed the name, convert to abbreviation -- if not a known name,
        // assume abbreviated already
        String originAbbreviation = stations.getStationNames().contains(origin) ? stations
                .stationNameToAbbreviation(origin) : origin;
        String destinationAbbreviation = stations.getStationNames().contains(destination) ? stations
                .stationNameToAbbreviation(destination) : destination;

        // TODO same as above..
        String time = "now";
        int howManyTripsBefore = 0;
        int howManyTripsAfter = 3;

        String url = String.format("cmd=depart&orig=%s&dest=%s&time=%s&b=%s&a=%s", originAbbreviation,
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

    public Stations getStations() {
        // TODO smarter caching..
        if (this.stations != null) {
            log.debug("using cached station information");
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

    public String callBART(String method, String query) throws IOException {
        // callBART() with a query, the endpoint and key (and trailing &) will
        // be prepended to your query

        String url = String.format("%s/%s.aspx?%s&key=%s", this.BARTendpoint, method, query, this.BARTkey);

        log.info(String.format("callBART[%s]", url));

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET"); // optional default is GET
        con.setRequestProperty("User-Agent", USER_AGENT); // add request header

        int responseCode = con.getResponseCode();
        log.debug(String.format("response code[%s]", responseCode));

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        log.debug(String.format("response (%s) [%s]", response.length(), response.toString()));

        return response.toString();
    }

}
