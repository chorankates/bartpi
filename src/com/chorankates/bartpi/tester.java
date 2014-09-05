package com.chorankates.bartpi;

import org.junit.*;

public class tester {

	@Test
	public static void main(String[] args) {
		BartPI bpi = new BartPI();

        Stations stations = bpi.getStations();

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

		Arrivals arrivals = bpi.arrivals("Embarcadero", "Powell St."); // for trips arriving based on specified time (NOW)

        for (Arrival arrival : arrivals.getArrivals()) {
            System.out.println(String.format("[%s->%s] [%s->%s] ($ %s) (%s)",
                    arrival.getOrigin(),
                    arrival.getDestination(),
                    arrival.getOrigTimeMin(),
                    arrival.getDestTimeMin(),
                    arrival.getFare(),
                    arrival.getTripTime()
                    ));
        }

		Departures departures = bpi.departures("Embarcadero", "Powell St."); // for trips departing based on specified time (NOW)

        for (Departure departure : departures.getDepartures()) {
            System.out.println(String.format("[%s->%s] [%s->%s] ($ %s) (%s)",
                    departure.getOrigin(),
                    departure.getDestination(),
                    departure.getOrigTimeMin(),
                    departure.getDestTimeMin(),
                    departure.getFare(),
                    departure.getTripTime()
            ));
        }

	}

	
}
