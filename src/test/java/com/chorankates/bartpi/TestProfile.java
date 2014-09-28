package com.chorankates.bartpi;

import org.junit.*;

import java.io.IOException;

/**
 * Created by conor on 9/28/14.
 */
public class TestProfile {

    private static Profile kgProfile;
    private static Route kgRoute;

    @BeforeClass
    public static void setup () throws IOException {
        kgProfile  = new Profile("foo");
        kgRoute = new Route("EMBR", "POWL", "home");

        Assert.assertEquals(kgProfile.getRoutes().size(), 0);
        kgProfile.addRoute(kgRoute);
    }

    @Test
    public void happyPathTest () throws IOException {
        Assert.assertEquals(kgProfile.getRoutes().size(), 1);

        Route byName = kgProfile.getRoute("home");
        Assert.assertEquals(kgRoute, byName);
    }

    @Test(expected = IOException.class)
    public void badInputGetRouteByNameTest () throws IOException {
        Route byNameDne = kgProfile.getRoute("fizzy");
    }

    @Test(expected = IOException.class)
    public void addDuplicateRouteNameTest () throws  IOException {
        Route duplicateName = new Route("POWL", "EMBR", "home");
        kgProfile.addRoute(duplicateName);
    }

    @Test(expected = IOException.class)
    public void addDuplciateRouteTest () throws IOException {
        kgProfile.addRoute(kgRoute);
    }

    @Test
    public void allowSameTripTest () throws IOException {
        Route aRoseByAnotherName = new Route("EMBR", "POWL", "rosalind");
        kgProfile.addRoute(aRoseByAnotherName);
    }

}
