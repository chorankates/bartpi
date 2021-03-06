package com.chorankates.bartpi;

import org.apache.log4j.Logger;

/**
 * Created by conor on 9/4/14.
 */
public class Station {

    String name;
    String abbreviation;
    String latitude;
    String longitude;
    String address;
    String city;
    String county;
    String state; // really
    String zipcode;

    private static Logger log = Logger.getLogger(Station.class.getName());

    public Station() {
        // allow this to be built up incrementally
    }

    public Station(Stations stations, String stationName) {

        Station station = stations.getStation(stationName);

        log.debug(String.format("adding station[%s] to collection", stationName));

        name         = station.getName();
        abbreviation = station.getAbbreviation();
        latitude     = station.getLatitude();
        longitude    = station.getLongitude();
        address      = station.getAddress();
        city         = station.getCity();
        county       = station.getCounty();
        state        = station.getState();
        zipcode      = station.getZipcode();
    }

    public boolean validateStation() {
        // TODO implement something that validates we have (or do not) all attributes supplied
        return false;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

}
