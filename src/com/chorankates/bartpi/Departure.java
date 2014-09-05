package com.chorankates.bartpi;

import org.apache.log4j.Logger;

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
    Logger log = Logger.getLogger(Departure.class.getName());

    ArrayList<Leg> legs = new ArrayList<Leg>();
    
	public void addLeg(Leg newLeg) {
        log.debug(String.format("adding leg[%s] [%s->%s]", newLeg.getOrder(), newLeg.getOrigin(), newLeg.getDestination()));
		legs.add(newLeg);
	}

    
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

	public String getTripTime() {
		// TODO implement this
		return "NOT IMPLEMENTED";
	}

    public String getTimeUntilTrip() {
        // TODO implement something real here
        return "NOT IMPLEMENTED";
    }

}
