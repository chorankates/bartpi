package com.chorankates.bartpi;

import org.junit.*;

/**
 * Created by conor on 9/28/14.
 */
public class TestRoute {

    private String goodStationCode1 = "EMBR";
    private String goodStationCode2 = "POWL";

    private String goodStationName1 = "Embarcadero";
    private String goodStationName2 = "Powell St.";

    private String goodRouteName = "home";

    Route goodRoute = new Route(goodStationName1, goodStationName2, goodRouteName);

    @Test
    public void happyPathTest () {
        Assert.assertEquals(goodStationName1, goodRoute.getOriginName());
        Assert.assertEquals(goodStationName2, goodRoute.getDestinationName());
        Assert.assertEquals(goodRouteName,    goodRoute.getRouteName());
    }

    @Test
    public void changeItUpTest () {
        goodRoute.setRouteName("reverse");
        Assert.assertEquals(goodRoute.getRouteName(), "reverse");

        goodRoute.setOriginName(goodStationName2);
        goodRoute.setDestinationName(goodStationName1);

        Assert.assertEquals(goodStationName2, goodRoute.getOriginName());
        Assert.assertEquals(goodStationName1, goodRoute.getDestinationName());
    }

    @Test
    public void badRoutesTest () {
        // TODO ensure we're throwing the right exceptions -- right now we're not doing anything
        Route badRoute = new Route(goodStationCode1, goodStationCode1, "noop route");
        Route stationDNERoute = new Route(goodStationCode1, "fizzy", "this station does not exist");
    }

    // TODO this doesn't work right now because we dont actually translate between names and codes - or see above, do anything
    public void testRouteByCode () {
        Route goodRouteByCode = new Route(goodStationCode1, goodStationCode2, goodRouteName);

        Assert.assertEquals(goodStationName1, goodRouteByCode.getOriginName());
        Assert.assertEquals(goodStationName2, goodRouteByCode.getDestinationName());
        Assert.assertEquals(goodRouteName,    goodRouteByCode.getRouteName());

        // TODO this also needs to handle the change case - or we need to rethink the model
    }

}
