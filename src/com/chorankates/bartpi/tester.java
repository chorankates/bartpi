package com.chorankates.bartpi;

public class tester {

	public static void main(String[] args) {
		BartPI bpi = new BartPI();
		
		bpi.stations(); // list of stations
		bpi.arrivals("EMBR", "POWL"); // for trips arriving based on specified time (NOW)
		bpi.departures("EMBR", "POWL"); // for trips departing based on specified time (NOW)		
	}

	
}
