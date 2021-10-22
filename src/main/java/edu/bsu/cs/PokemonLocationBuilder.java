package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PokemonLocationBuilder {
    private InputStream inputData;

    PokemonLocationBuilder(InputStream inputData){
        this.inputData = inputData;
    }

    public List<PokemonLocation> buildLocationList (){
        List<PokemonLocation> list = new ArrayList<>();
        list.add(new PokemonLocation(""));
        return list;
    }

    public String getUrl() throws IOException {
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        inputData.transferTo(byteArray);
        JsonPath timePath = JsonPath.compile("$..location_area_encounters");
        JSONArray locationUrl = timePath.read(byteArray);
        return locationUrl.get(0).toString();
    }
}
