package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by conor on 9/5/14.
 */

public class Leg {

    String order; // this should be an int
    String transferCode;
    String origin;
    String destination;

    String origTimeMin; // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;
    String line;
    String bikeFlag;
    String trainHeadStation;
    String trainIdx; // this should be an int

    @SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Leg.class.getName());

    public Leg() {
        // allow this to be built up incrementally
    }

    public String getOrder() {
        return order;
    }

    public String getTransferCode() {
        return transferCode;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
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

    public String getLine() {
        return line;
    }

    public String getBikeFlag() {
        return bikeFlag;
    }

    public String getTrainHeadStation() {
        return trainHeadStation;
    }

    public String getTrainIdx() {
        return trainIdx;
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
