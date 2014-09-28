package com.chorankates.bartpi;

import org.junit.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by conor on 9/28/14.
 */
public class TestProfile {

    private static BartPI bpi;
    private Profile kgProfile = new Profile("foo");
    private Route kgRoute = new Route("EMBR", "POWL", "home");

    @Test
    public void happyPathTest () throws IOException {
        Assert.assertEquals(kgProfile.getRoutes().size(), 0);
        kgProfile.addRoute(kgRoute);
        Assert.assertEquals(kgProfile.getRoutes().size(), 1);

        Route byIndex = kgProfile.getRoute(0);
        Assert.assertEquals(kgRoute, byIndex);

        Route byName = kgProfile.getRoute("home");
        Assert.assertEquals(kgRoute, byName);
    }

    @Test
    public void badInputTest () throws IOException {
        // TODO do we actually want to catch these? these should be private anyway, right?
        //Route byIndexDNE = kgProfile.getRoute(100);
        //Route byIndexNegative = kgProfile.getRoute(-100);

        // TODO assure the correct IO exception is thrown
        //Route byStringDNE = kgProfile.getRoute("fizzy");

    }

}
