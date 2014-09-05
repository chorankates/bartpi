package com.chorankates.bartpi;

import org.junit.*;

public class tester {

	@Test
	public static void main(String[] args) {
		BartPI bpi = new BartPI();

        Stations stations     = bpi.getStations();

        for (String name : stations.getStationNames()) {
            Station station = stations.getStation(name);

            System.out.println(String.format("%s: %s (%s)",
                                            name,
                                            station.getAbbreviation(),
                                            station.getCity()
                                            ));

            // TODO this should be an actual test
            Assert.assertEquals(stations.stationAbbreviationToName(station.getAbbreviation()), name);
            Assert.assertEquals(stations.stationNameToAbbreviation(name), station.getAbbreviation());
        }

		Arrivals arrivals     = bpi.arrivals("EMBR", "POWL"); // for trips arriving based on specified time (NOW)
		Departures departures = bpi.departures("EMBR", "POWL"); // for trips departing based on specified time (NOW)
	}

	
}
