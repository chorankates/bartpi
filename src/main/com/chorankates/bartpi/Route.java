package com.chorankates.bartpi;

import org.apache.log4j.Logger;

public class Route {

    // TODO implement a method that can validate originName/destinationName
    private String originName;
    private String destinationName;
    private String routeName;

    Logger log = Logger.getLogger(Route.class.getName());

    Route (String originName, String destinationName, String routeName) {
        this.originName      = originName;
        this.destinationName = destinationName;
        this.routeName       = routeName;
    }

    // allow incremental buildup
    Route () {
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

    public void setOriginName(String name) { originName = name; }

    public void setDestinationName(String name) { destinationName = name; }

    public void setRouteName(String name) { routeName = name; }

}
