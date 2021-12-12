package edu.bsu.cs.locations;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs.query.QuerySearcher;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocationBuilder {
    private final InputStream inputData;

    public LocationBuilder(InputStream inputData) {
        this.inputData = inputData;
    }

    public List<Location> buildLocationList() throws IOException {
        List<Location> list = new ArrayList<>();
        String locationUrl = getUrl();
        QuerySearcher searcher = new QuerySearcher();
        InputStream rawLocations = searcher.getInputStream(locationUrl);
        JsonPath locationPath = JsonPath.compile("$..location_area..name");
        JSONArray locationsArray = locationPath.read(rawLocations);
        for (Object o : locationsArray) {
            list.add(new Location(o.toString()));
        }
        return list;
    }

    public String getUrl() throws IOException {
        JsonPath encountersPath = JsonPath.compile("$..location_area_encounters");
        JSONArray locationUrl = encountersPath.read(this.inputData);
        return locationUrl.get(0).toString();
    }
}