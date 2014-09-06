package com.chorankates.bartpi;

import org.junit.*;

public class TestStations {

	private static BartPI   bpi;
	private static Stations stations;
	private static Station  station;
	
	private static String stationName        = "Embarcadero";
	private String stationAbbreviation = "EMBR";
	
	@BeforeClass
	public static void setup() {
		bpi      = new BartPI();
		stations = bpi.getStations();
		station   = bpi.getStations().getStation(stationName);
	}
	
	@Test
	public void testNametoAbbreviation() throws Exception {
		Assert.assertEquals(stations.stationAbbreviationToName(station.getAbbreviation()), stationName);

        // loop through the entire collection?
        // need some negative tests
	}
	
	@Test
	public void testAbbreviationToName() throws Exception {
		Assert.assertEquals(stations.stationNameToAbbreviation(stationName), station.getAbbreviation());
	}
	
	
}
