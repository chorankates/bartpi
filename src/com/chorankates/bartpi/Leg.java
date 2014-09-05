package com.chorankates.bartpi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by conor on 9/5/14.
 */


public class Leg {

    // TODO calculate leg length -- want to be able to do this on arrivals/departures as well - should these just be modelled as 'trips' and then subclassed?
	
	String order; // this should be an int
	String transferCode;
    String origin;
    String destination;
    String origTimeMin;  // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;
    String line;
    String bikeFlag;
    String trainHeadStation;
    String trainIdx; // this should be an int

    public Leg () {
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

}
