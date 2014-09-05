package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Created by conor on 9/4/14.
 */
public class Arrival {

    String origin;
    String destination;
    String fare; // should this just be a double?

    // TODO parse these values into a real date object we can do maths on
    String origTimeMin;  // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;

    ArrayList<Leg> legs = new ArrayList<Leg>();
    Logger log = Logger.getLogger(Arrival.class.getName());

    public void addLeg(Leg newLeg) {
        log.info(String.format("adding leg[%s] [%s->%s]", newLeg.getOrder(), newLeg.getOrigin(), newLeg.getDestination()));
        legs.add(newLeg);
    }

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

	public String getTripTime() {
		// TODO implement something real here
		return "NOT IMPLEMENTED";
	}

    public String getTimeUntilTrip() {
        // TODO implement something real here
        return "NOT IMPLEMENTED";
    }

}
