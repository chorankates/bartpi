package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by conor on 9/5/14.
 */


public class Leg {

	String order; // this should be an int
	String transferCode;
    String origin;
    String destination;

    // TODO convert this to a date object we can do maths on
    String origTimeMin;  // h:mm ?m
    String origTimeDate; // mm/dd/yyyy
    String destTimeMin;
    String destTimeDate;
    String line;
    String bikeFlag;
    String trainHeadStation;
    String trainIdx; // this should be an int

    Logger log = Logger.getLogger(Leg.class.getName());

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
