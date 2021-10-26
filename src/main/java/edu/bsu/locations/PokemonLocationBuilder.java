package edu.bsu.locations;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs.QuerySearcher;
import edu.bsu.locations.PokemonLocation;
import net.minidev.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonLocationBuilder {
    private final InputStream inputData;

    public PokemonLocationBuilder(InputStream inputData){
        this.inputData = inputData;
    }

    public List<PokemonLocation> buildLocationList () throws IOException {
        List<PokemonLocation> list = new ArrayList<>();
        String locationUrl = getUrl();
        QuerySearcher searcher = new QuerySearcher();
        InputStream rawLocations = searcher.DataFromUrl(locationUrl);
        JsonPath locationPath = JsonPath.compile("$..location_area..name");
        JSONArray locationsArray = locationPath.read(rawLocations);
        for (Object o : locationsArray) {
            list.add(new PokemonLocation(o.toString()));
        }
        return list;

    }

    public String getUrl() throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        InputStream clone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        JsonPath encountersPath = JsonPath.compile("$..location_area_encounters");
        JSONArray locationUrl = encountersPath.read(clone);
        return locationUrl.get(0).toString();
    }
}