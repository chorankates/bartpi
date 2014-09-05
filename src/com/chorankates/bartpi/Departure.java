package com.chorankates.bartpi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by conor on 9/4/14.
 */
public class Departure {

    String origin;
    String destination;
    String fare; // should this just be a double?
    String origTimeMin;  // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;

    // TODO should legs be their own objects? probably..
    ArrayList<HashMap<String, String>> legs = new ArrayList<HashMap<String, String>>();

    public Departure () {
        // allow this to be built up incrementally
    }

    public Departure (Departures departures, int index) {

        Departure departure = departures.getDeparture(index );

        origin       = departure.getOrigin();
        destination  = departure.getDestination();
        fare         = departure.getFare();
        origTimeMin  = departure.getOrigTimeMin();
        origTimeDate = departure.getOrigTimeDate();
        destTimeMin  = departure.getDestTimeMin();
        destTimeDate = departure.getDestTimeDate();
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getFare() {
        return fare;
    }

    public String getOrigTimeMin() {
        return origTimeMin;
    }

    public String getOrigTimeDate() {
        return origTimeDate;
    }

    public String getDestTimeMin() {
        return destTimeMin;
    }

    public String getDestTimeDate() {
        return destTimeDate;
    }

}
