package com.chorankates.bartpi;

public class tester {

	public static void main(String[] args) {
		BartPI bpi = new BartPI();

        //Stations stations     = bpi.getStations();
		Arrivals arrivals     = bpi.arrivals("EMBR", "POWL"); // for trips arriving based on specified time (NOW)
		//Departures departures = bpi.departures("EMBR", "POWL"); // for trips departing based on specified time (NOW)
	}

	
}
