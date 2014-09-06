package com.chorankates.bartpi;

import java.io.IOException;

public class tester {

	public static void main(String[] args) throws IOException {
		BartPI bpi = new BartPI();

        Stations stations = bpi.getStations();

        for (String name : stations.getStationNames()) {
            Station station = stations.getStation(name);

            System.out.println(String.format("%s: %s (%s)",
                                            name,
                                            station.getAbbreviation(),
                                            station.getCity()
                                            ));
        }

		Arrivals arrivals = bpi.getArrivals("Powell St.", "Rockridge"); // for trips arriving based on specified time (NOW)

        for (Arrival arrival : arrivals.getArrivals()) {
            System.out.println(arrival.toString());
        }

		Departures departures = bpi.getDepartures("Powell St.", "Rockridge"); // for trips departing based on specified time (NOW)

        for (Departure departure : departures.getDepartures()) {
            System.out.println(departure.toString());
        }

	}

	
}
