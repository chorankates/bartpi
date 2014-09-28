package com.chorankates.bartpi;

import org.apache.log4j.Logger;

public class Route {

    // TODO implement a method that can validate originName/destinationName
    private String originName;
    private String destinationName;
    private String routeName;

    private static Logger log = Logger.getLogger(Route.class.getName());

    // TODO we need to validate that these are valid names (or codes, and then we need to translate)
    Route (String originName, String destinationName, String routeName) {
    	
    	log.debug(String.format("adding route[%s] [%s]->[%s]", routeName, originName, destinationName));
    	
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
