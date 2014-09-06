package com.chorankates.bartpi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class tester {

    public static void main(String[] args) throws IOException {
        BartPI bpi = new BartPI();

        Stations stations = bpi.getStations();

        for (String name : stations.getStationNames()) {
            Station station = stations.getStation(name);

            System.out.println(String.format("%s: %s (%s)", name, station.getAbbreviation(), station.getCity()));
        }

        Profile workProfile = new Profile("leaving-work");
        workProfile.addRoute(new Route("Embarcadero", "Powell St.", "conor"));
        workProfile.addRoute(new Route("Embarcadero", "16th St. Mission", "pj"));
        workProfile.addRoute(new Route("Embarcadero", "Daly City", "fish"));

        Profile personalProfile = new Profile("personal");
        personalProfile.addRoute(new Route("Powell St.", "Embarcadero", "home->office"));
        personalProfile.addRoute(new Route("Powell St.", "Rockridge", "eb"));
        personalProfile.addRoute(new Route("Powell St.", "16th St. Mission", "pj"));

        ArrayList<Profile> profileList = new ArrayList<Profile>();
        profileList.add(workProfile);
        profileList.add(personalProfile);

        for (Profile profile : profileList) {

            for (Route route : profile.getRoutes()) {
                Arrivals arrivals = bpi.getArrivals(route.getOriginName(), route.getDestinationName());
                Departures departures = bpi.getDepartures(route.getOriginName(), route.getDestinationName());

                for (Arrival arrival : arrivals.getArrivals()) {
                    System.out.println(arrival.toString());
                }

                for (Departure departure : departures.getDepartures()) {
                    System.out.println(departure.toString());
                }
            }
        }
    }

}
