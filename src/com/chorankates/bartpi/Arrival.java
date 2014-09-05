package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat.*;
import java.util.ArrayList;
import java.util.Date;

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
    Logger log = Logger.getLogger(Arrival.class.getName());

    public void addLeg(Leg newLeg) {
        log.info(String.format("adding leg[%s] [%s->%s]", newLeg.getOrder(), newLeg.getOrigin(), newLeg.getDestination()));
        legs.add(newLeg);
    }

    public Arrival () {
        // allow this to be built up incrementally
    }

    public String toString() {
    	
    	String tripTime;
    	
		try {
			tripTime = this.getTripTime();
		} catch (ParseException e) {
			tripTime = "unknown";
			log.error(e.getMessage());
		}
    	
        return String.format("[%s->%s] [%s->%s] ($ %s) (%s)",
                this.getOrigin(),
                this.getDestination(),
                this.getOrigTimeMin(),
                this.getDestTimeMin(),
                this.getFare(),
                tripTime);
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

    public Date getOriginTime() throws ParseException {
        String input         = String.format("%s %s", this.origTimeMin, this.origTimeDate);
        DateFormat formatter = new SimpleDateFormat("h:mm a MM/dd/yyyy");
        Date date            = (Date) formatter.parse(input);
        return date;
    }

    public Date getDestinationTime() throws ParseException {
        String input         = String.format("%s %s", this.destTimeMin, this.destTimeDate);
        DateFormat formatter = new SimpleDateFormat("h:mm a MM/dd/yyyy");
        Date date            = (Date) formatter.parse(input);
        return date;
    }

    public String getTripTime() throws ParseException {
        Date tripStart = this.getOriginTime();
        Date tripStop  = this.getDestinationTime();

        // TODO support more than just minutes..
        return String.format("%s minutes", (tripStop.getTime() - tripStart.getTime()) / 1000 / 60);
	}

    public String getTimeUntilTrip() {
        // TODO implement something real here
        // time.untiltrip - time.now
        return "NOT IMPLEMENTED";
    }

}
