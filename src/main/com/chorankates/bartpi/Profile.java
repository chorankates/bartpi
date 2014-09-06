package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by conor on 9/6/14.
 */
public class Profile {

    String name;
    Station defaultStation; // for the scenario where you care about many trips departing from a single location

    Logger log = Logger.getLogger(Profile.class.getName());
    ArrayList<Route> routeList = new ArrayList<Route>();

    Profile (String name) {
        this.name = name;
    }

    public void addRoute(Route route) {
        log.debug(String.format("adding route[%s] to profile[%s]",
                                route.getRouteName(),
                                this.name));
        routeList.add(route);
    }

    public Route getRoute(String name) throws IOException {
        for (Route route : routeList) {
            if (route.getRouteName().equals(name)) {
                return route;
            }
        }

        throw new IOException(String.format("unable to find route[%s]", name));
    }

    public Route getRoute(int index) {
        return routeList.get(index);
    }

    public ArrayList<Route> getRoutes() {
        return routeList;
    }

}
