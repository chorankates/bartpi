package com.chorankates.bartpi;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.*;

public class tester {

    static BartPI bpi;

    @BeforeClass
    public static void setup() {
        bpi = new BartPI();
    }

    @Test
    public void testGetStations() {

        Stations stations = bpi.getStations();

        for (String name : stations.getStationNames()) {
            Station station = stations.getStation(name);

            System.out.println(String.format("%s: %s (%s)", name, station.getAbbreviation(), station.getCity()));
        }
    }

    @Test
    public void testWorkflow() throws IOException {
        Profile workProfile = new Profile("leaving-work");
        workProfile.addRoute(new Route("Embarcadero", "Powell St.", "conor"));
        workProfile.addRoute(new Route("Embarcadero", "16th St. Mission", "pj"));
        workProfile.addRoute(new Route("Embarcadero", "Daly City", "fish"));

        Profile personalProfile = new Profile("personal");
        personalProfile.addRoute(new Route("Powell St.", "Embarcadero", "home->office"));
        personalProfile.addRoute(new Route("Powell St.", "Rockridge", "eb"));
        personalProfile.addRoute(new Route("Powell St.", "16th St. Mission", "pj"));

        Profile duplicateProfile = new Profile("duplicate");
        duplicateProfile.addRoute(new Route("Powell St.", "Embarcadero", "foo1"));
        duplicateProfile.addRoute(new Route("Powell St.", "Embarcadero", "foo2"));

        ArrayList<Profile> profileList = new ArrayList<Profile>();
        profileList.add(workProfile);
        profileList.add(personalProfile);
        profileList.add(workProfile);
        profileList.add(personalProfile);
        profileList.add(duplicateProfile);

        for (Profile profile : profileList) {

            for (Route route : profile.getRoutes()) {
                ArrayList<Trip> arrivals = bpi.getArrivals(route.getOriginName(), route.getDestinationName());
                ArrayList<Trip> departures = bpi.getDepartures(route.getOriginName(), route.getDestinationName());

                System.out.println("arrivals: ");
                for (Trip arrival : arrivals) {
                    System.out.println(arrival.toString());
                }

                System.out.println("departures: ");
                for (Trip departure: departures) {
                    System.out.println(departure.toString());
                }
            }
        }

        System.out.println("endup");
    }
}
