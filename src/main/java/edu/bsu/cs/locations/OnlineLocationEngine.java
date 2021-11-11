package edu.bsu.cs.locations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OnlineLocationEngine implements LocationEngine {
    @Override
    public String getLocations(InputStream inputData) throws IOException {
        String result;
        LocationFormatter formatter = new LocationFormatter();
        LocationBuilder locationBuilder = new LocationBuilder(inputData);
        List<Location> locationsList = locationBuilder.buildLocationList();
        result = formatter.formatLocationList(locationsList);
        return result;
    }
}
