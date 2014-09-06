package com.chorankates.bartpi;

import java.io.IOException;

public class Route {

    String originName;
    String destinationName;
    String profileName; // TODO figure out how we're going to persist profiles -- on disk?
    String routeName;
    
    Route (String routeName) throws IOException {

        if (routeName.equals("work")) {                                  
            originName      = "Powell St.";
            destinationName = "Embarcadero";
            profileName     = "unused, but Profiles will allow grouping of routes";
        } else if (routeName.equals("mish")) {           
            originName      = "Powell St.";
            destinationName = "16th St. Mission";
            profileName     = "";
        } else if (routeName.equals("eb")) {
            originName      = "Embarcadero";
            destinationName = "Rockridge";
            profileName     = "";
        } else if (routeName.equals("fish")) {
            originName      = "Embarcadero";
            destinationName = "Daly City";
            profileName     = "";
        } else {
            throw new IOException(String.format("unknown profile[%s]", routeName));
        }

        this.routeName = routeName;
    }
    
    Route (String originName, String destinationName, String routeName, String profileName) {
        this.originName      = originName;
        this.destinationName = destinationName;
        this.routeName       = routeName;
        this.profileName     = profileName;
    }

    public String getOriginName() {
        return originName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getRouteName() {
        return routeName;
    }

}
