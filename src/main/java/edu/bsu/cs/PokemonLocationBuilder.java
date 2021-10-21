package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;

public class PokemonLocationBuilder {
    public String getUrl(InputStream rawData) throws IOException {
        JsonPath timePath = JsonPath.compile("$..location_area_encounters");
        JSONArray locationUrl = timePath.read(rawData);
        return locationUrl.get(0).toString();
    }
}
