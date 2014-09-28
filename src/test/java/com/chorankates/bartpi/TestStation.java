package com.chorankates.bartpi;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by conor on 9/28/14.
 */
public class TestStation {

    private static BartPI bpi;
    private static Stations stations;

    private static Station kgStation;
    private static String kgStationName = "Embarcadero";
    private static String kgStationCode = "EMBR";

    @BeforeClass
    public static void setup() {
        bpi = new BartPI();
        stations = bpi.getStations();
        kgStation = stations.getStation(kgStationName);
    }

    @Test
    public void testHappyPath () {
        Assert.assertEquals(kgStationCode, kgStation.getAbbreviation());
        Assert.assertEquals(kgStationName, kgStation.getName());

        // TODO how do we do non-not-null validation?
        Assert.assertNotNull(kgStation.getAddress());
        Assert.assertNotNull(kgStation.getCity());
        Assert.assertNotNull(kgStation.getCounty());
        Assert.assertNotNull(kgStation.getState());
        Assert.assertNotNull(kgStation.getZipcode());
        Assert.assertNotNull(kgStation.getLatitude());
        Assert.assertNotNull(kgStation.getLongitude());
    }

    @Test
    public void testBadStationName() {

        Station badStation = new Station();
        badStation.name = "foo";

        // TODO assert that we throw the right exception
        // probably need to throw an is_validated() method before all getters before returning.. or should we?
        //badStation.getAddress();
    }
}
