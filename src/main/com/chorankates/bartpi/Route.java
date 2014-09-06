package com.chorankates.bartpi;

import org.apache.log4j.Logger;

import java.io.IOException;

public class Route {

    private String originName;
    private String destinationName;
    private String routeName;

    Logger log = Logger.getLogger(Route.class.getName());

    Route (String originName, String destinationName, String routeName) {
        this.originName      = originName;
        this.destinationName = destinationName;
        this.routeName       = routeName;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getRouteName() {
        return routeName;
    }

}
