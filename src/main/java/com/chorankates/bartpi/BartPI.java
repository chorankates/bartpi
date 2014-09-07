package com.chorankates.bartpi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class BartPI {

    public final String BARTendpoint = "http://api.bart.gov/api";
    public Stations stations = null;

    private Logger log = Logger.getLogger(BartPI.class.getName());
    private String BARTkey                        = "MW9S-E7SL-26DU-VV8V"; // don't worry, it's public
    private final String USER_AGENT               = "Mozilla/5.0";
    private int cacheValidity                     = 60 * 1000; // milli->sec
    private HashMap<String, HashMap<String, String>> responseCache = new HashMap<String, HashMap<String, String>>();

    // TODO these probably belong on the 'profile' level, not here..
    private int tripsBefore = 0;
    private int tripsAfter = 3;

    BartPI() {
        System.out.println(String.format("using shared key[%s]", this.BARTkey));
        stations = this.getStations();
    }

    BartPI(String key) {
        this.BARTkey = key;
        log.debug(String.format("using key[%s]", this.BARTkey));
        stations = this.getStations();
    }

    public int getCacheValidity() {
        return cacheValidity;
    }

    public void setCacheValidity(int length) {
        cacheValidity = length;
    }

    public String getKey() {
        return BARTkey;
    }

    public void setKey(String key) {
        BARTkey = key;
    }

    public int getTripsBefore() {
        return tripsBefore;
    }

    public void setTripsBefore(int count) {
        tripsBefore = count;
    }

    public int getTripsAfter() {
        return tripsAfter;
    }

    public void setTripsAfter(int count) {
        tripsAfter = count;
    }

    public String getEndpoint() {
        return BARTendpoint;
    }

    public Arrivals getArrivals(Station origin, Station destination) throws IOException {
        return getArrivals(origin, destination, "now");
    }

    public Arrivals getArrivals(Station origin, Station destination, String time) {

        String url = String.format("cmd=arrive&orig=%s&dest=%s&time=%s&b=%s&a=%s",
                origin.getAbbreviation(),
                destination.getAbbreviation(),
                time,
                tripsBefore,
                tripsAfter);

        String xml = null;

        try {
            xml = callBART("sched", url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Arrivals(xml);
    }

    public Arrivals getArrivals(String origin, String destination) throws IOException {

        String originName = stations.getStationNames().contains(origin)
                ? origin
                : stations.stationAbbreviationToName(origin);
        String destinationName = stations.getStationNames().contains(destination)
                ? destination
                : stations.stationAbbreviationToName(destination);

        Station originStation      = stations.getStation(originName);
        Station destinationStation = stations.getStation(destinationName);

        return getArrivals(originStation, destinationStation);
    }

    public Departures getDepartures(Station origin, Station destination) {
        return getDepartures(origin, destination, "now");
    }

    public Departures getDepartures(Station origin, Station destination, String time) {
        String url = String.format("cmd=depart&orig=%s&dest=%s&time=%s&b=%s&a=%s",
                origin.getAbbreviation(),
                destination.getAbbreviation(),
                time,
                tripsBefore,
                tripsAfter);

        String xml = null;

        try {
            xml = callBART("sched", url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new Departures(xml);

    }

    public Departures getDepartures(String origin, String destination) throws IOException {

        String originName = stations.getStationNames().contains(origin)
                ? origin
                : stations.stationAbbreviationToName(origin);
        String destinationName = stations.getStationNames().contains(destination)
                ? destination
                : stations.stationAbbreviationToName(destination);

        Station originStation      = stations.getStation(originName);
        Station destinationStation = stations.getStation(destinationName);

        return getDepartures(originStation, destinationStation);
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

    public String callBART(String method, String query) throws IOException {
        // callBART() with a query, the endpoint and key (and trailing &) will
        // be prepended to your query

        String url = String.format("%s/%s.aspx?%s&key=%s", this.BARTendpoint, method, query, this.BARTkey);

        if (responseCache.containsKey(url)) {
            HashMap<String, String> cachedResponse = responseCache.get(url);

            Long created = Long.parseLong(cachedResponse.get("time"));
            Long now     = new Date().getTime();
            Long age     = now - created;

            if (age < cacheValidity) {
                log.debug(String.format("using cached response[age: %s, cacheValidity: %s, created: %s, now: %s]",
                        age,
                        cacheValidity,
                        created,
                        now));
                return cachedResponse.get("response");
            } else {
                log.debug(String.format("expiring cached response[age: %s, cacheValidity: %s, created: %s, now: %s]",
                        age,
                        cacheValidity,
                        created,
                        now));
            }
        }

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

        if (responseCode == 200) {
            // cache this response
            HashMap<String, String> value = new HashMap<String, String>();

            String time = String.format("%s", new Date().getTime()); // TODO this is stupid
            log.debug(String.format("caching response[time: %s]", time));

            value.put("time", time);
            value.put("response", response.toString());

            responseCache.put(url, value);
        }

        return response.toString();
    }

}
