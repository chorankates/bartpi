package com.chorankates.bartpi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by conor on 9/4/14.
 */
public class Arrival {

    String origin;
    String destination;
    String fare; // should this just be a double?
    String origTimeMin;  // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;

    ArrayList<Leg> legs = new ArrayList<Leg>();

    public Arrival () {
        // allow this to be built up incrementally
    }

    public Arrival (Arrivals arrivals, int index) {

        Arrival arrival = arrivals.getArrival(index );

        origin       = arrival.getOrigin();
        destination  = arrival.getDestination();
        fare         = arrival.getFare();
        origTimeMin  = arrival.getOrigTimeMin();
        origTimeDate = arrival.getOrigTimeDate();
        destTimeMin  = arrival.getDestTimeMin();
        destTimeDate = arrival.getDestTimeDate();
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

	public void addLeg(Leg newLeg) {
        System.out.println(String.format("adding leg[%s] [%s->%s]", newLeg.getOrder(), newLeg.getOrigin(), newLeg.getDestination()));
		legs.add(newLeg);
	}

}
