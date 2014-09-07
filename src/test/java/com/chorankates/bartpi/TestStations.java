package com.chorankates.bartpi;

import org.junit.*;

import java.io.IOException;
import java.text.ParseException;

@SuppressWarnings("unused")
public class TestStations {

    private static BartPI bpi;
    private static Stations stations;
    private static Station station;

    private static String stationName = "Embarcadero";
    private String stationAbbreviation = "EMBR";

    @BeforeClass
    public static void setup() {
        bpi = new BartPI();
        stations = bpi.getStations();
        station = stations.getStation(stationName);
    }

    @Test
    public void testNametoAbbreviation() throws Exception {
        Assert.assertEquals(stations.stationAbbreviationToName(station.getAbbreviation()), stationName);

        // loop through the entire collection?
    }

    @Test
    public void testBadNameToAbbreviation() {
        try {
            stations.stationNameToAbbreviation("Breezy Acres");
        } catch (IOException e) {
            Assert.assertTrue("correct exception thrown", true);
        } catch (Exception e) {
            Assert.assertTrue("correct exception thrown", false);
        }
    }

    @Test
    public void testAbbreviationToName() throws Exception {
        Assert.assertEquals(stations.stationNameToAbbreviation(stationName), stationAbbreviation);
    }

    @Test
    public void testBadAbbreviationToName() {
        try {
            stations.stationAbbreviationToName("FOO");
        } catch (IOException e) {
            Assert.assertTrue("correct exception thrown", true);
        } catch (Exception e) {
            Assert.assertTrue("correct exception thrown", false);
        }
    }

    @Test
    public void testAbbreviationNormalizer() {
        try {
            String fromName         = stations.stationAbbreviation(stationName);
            String fromAbbreviation = stations.stationAbbreviation(stationAbbreviation);

            Assert.assertEquals(fromAbbreviation, fromName);
            Assert.assertEquals(stationAbbreviation, fromName);
        } catch (Exception e) {
            Assert.assertTrue("no exception thrown", false);
        }
    }

    @Test
    public void testNameNormalizer() {
        try {
            String fromName         = stations.stationName(stationName);
            String fromAbbreviation = stations.stationName(stationAbbreviation);

            Assert.assertEquals(fromAbbreviation, fromName);
            Assert.assertEquals(stationName, fromName);
        } catch (Exception e) {
            Assert.assertTrue("no exception thrown", false);
        }
    }

}
