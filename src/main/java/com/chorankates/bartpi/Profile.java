package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by conor on 9/6/14.
 */
public class Profile {

    String name;
    Station defaultStation; // for the scenario where you care about many trips departing from a single location

    private static Logger log = Logger.getLogger(Profile.class.getName());
    HashMap<String, Route> routeHashMap = new HashMap<String, Route>();

    Profile (String name) {
        this.name = name;
    }

    public void setDefaultStation (Station defaultStation) {
        this.defaultStation = defaultStation;
    }

    public void setDefaultStation(String defaultStationString) {
        //TODO make this work.. currently we can't instantiate a station without Stations XML input
        // this.defaultStation = new Station(defaultStationString)
    }

    public void addRoute(Route route) throws IOException {
        log.debug(String.format("adding route[%s] to profile[%s]",
                                route.getRouteName(),
                                this.name));


        if (routeHashMap.containsKey(route.getRouteName())) {
            throw new IOException(String.format("route name[%s] already used in this profile", route.getRouteName()));
        }

        routeHashMap.put(route.getRouteName(), route);
    }

    public Route getRoute(String name) throws IOException {

        if (routeHashMap.containsKey(name)) {
            return routeHashMap.get(name);
        } else {
            throw new IOException(String.format("unable to find route[%s]", name));
        }
    }

    public Collection<String> getRouteNames() {
        return routeHashMap.keySet();
    }

    public Collection<Route> getRoutes() {
        return routeHashMap.values();
    }

}
