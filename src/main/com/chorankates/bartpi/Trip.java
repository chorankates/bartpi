package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by conor on 9/6/14.
 */
public class Trip {

    Route route;
    String tripType; // whether looking for departures or arrivals <-- yeah, this is probably wrong, Arrival should subclass trip

    ArrayList<Arrival> arrivals;
    ArrayList<Departure> departures;

    //Arrivals arrivals;
    //Departures departures;

    String fare; // should this just be a double?
    String origTimeMin; // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;

    ArrayList<Leg> legs = new ArrayList<Leg>();
    private static Logger log = Logger.getLogger(Arrival.class.getName());

    // TODO how do we not allow this? just throw an exception if you try to instantiate this directly?
    Trip () {

    }

    public ArrayList<Arrival> getArrivals() {
        return arrivals;
    }

    public ArrayList<Departure> getDepartures() {
        return departures;
    }

    public Arrival getArrival(int index) {
        return arrivals.get(index);
    }

    public Departure getDeparture(int index) {
        return departures.get(index);
    }

    public void addArrival(Arrival arrival) {
        log.debug(arrival.toString());
        log.info(String.format("adding arrival[%s] [%s->%s]", arrivals.size(), arrival.getOrigin(),
                arrival.getDestination()));

        arrivals.add(arrival);
    }

    public void addDeparture(Departure departure) {
        log.debug(departure.toString());
        log.info(String.format("adding departure[%s] [%s->%s]", departures.size(), departure.getOrigin(),
                departure.getDestination()));

        departures.add(departure);
    }

    public void addLeg(Leg newLeg) {
        log.info(String.format("adding leg[%s] [%s->%s]", newLeg.getOrder(), newLeg.getOrigin(),
                newLeg.getDestination()));
        legs.add(newLeg);
    }

    public Leg getLeg(int index) {
        return legs.get(index);
    }

    public ArrayList<Leg> getLegs() {
        return legs;
    }

    public String getDestination() {
        return route.getDestinationName();
    }

    public void setDestination(String name) {
        route.setDestinationName(name);
    }

    public String getOrigin() {
        return route.getOriginName();
    }

    public void setOrigin(String name) {
        route.setOriginName(name);
    }

    public String getTripType() {
        return tripType;
    }

    public String toString() {

        String tripTime;
        String leaveTime;

        try {
            tripTime = this.getTripTime();
        } catch (ParseException e) {
            tripTime = "unknown";
            log.error(e.getMessage());
        }

        try {
            leaveTime = this.getTimeUntilTrip();
        } catch (ParseException e) {
            leaveTime = "unknown";
            log.error(e.getMessage());
        }

        return String.format("[%s->%s] [%s->%s] ($%s, %s) (leaves station in %s)", this.route.getOriginName(),
                this.route.getDestinationName(),
                this.getOrigTimeMin(),
                this.getDestTimeMin(),
                this.getFare(),
                tripTime,
                leaveTime);
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
        String input = String.format("%s %s", this.origTimeMin, this.origTimeDate);
        DateFormat formatter = new SimpleDateFormat("h:mm a MM/dd/yyyy");
        Date date = (Date) formatter.parse(input);
        return date;
    }

    public Date getDestinationTime() throws ParseException {
        String input = String.format("%s %s", this.destTimeMin, this.destTimeDate);
        DateFormat formatter = new SimpleDateFormat("h:mm a MM/dd/yyyy");
        Date date = (Date) formatter.parse(input);
        return date;
    }

    public String getTripTime() throws ParseException {
        Date tripStart = this.getOriginTime();
        Date tripStop = this.getDestinationTime();

        // TODO support more than just minutes..
        return String.format("%s minutes", (tripStop.getTime() - tripStart.getTime()) / 1000 / 60);
    }

    public String getTimeUntilTrip() throws ParseException {
        Date timeNow = new Date();
        Date tripStart = this.getOriginTime();

        // TODO support more than just minutes..
        return String.format("%s minutes", (timeNow.getTime() - tripStart.getTime()) / 1000 / 60);
    }

}
