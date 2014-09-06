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

        // iterate over routes instead of static specs      
        ArrayList<Route> routes = new ArrayList<Route>();
        routes.add(new Route("work"));
        routes.add(new Route("mish"));
        routes.add(new Route("eb"));
        routes.add(new Route("fish"));
        routes.add(new Route("Rockridge", "Powell St.", "test", "test"));

        for (Route route : routes) {
            Arrivals arrivals     = bpi.getArrivals(route.getOriginName(), route.getDestinationName());
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
