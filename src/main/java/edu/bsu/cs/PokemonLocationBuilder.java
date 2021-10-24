package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class PokemonLocationBuilder {
    private InputStream inputData;

    PokemonLocationBuilder(InputStream inputData){
        this.inputData = inputData;
    }

    public List<PokemonLocation> buildLocationList () throws IOException {
        List<PokemonLocation> list = new ArrayList<>();
        String locationUrl = getUrl();
        URL Url = new URL(locationUrl);
        URLConnection connection = Url.openConnection();
        InputStream rawLocations = connection.getInputStream();
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