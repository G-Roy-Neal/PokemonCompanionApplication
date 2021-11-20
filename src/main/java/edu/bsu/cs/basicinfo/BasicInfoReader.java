package edu.bsu.cs.basicinfo;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BasicInfoReader {
    private final String data;

    public BasicInfoReader(InputStream inputData) throws IOException {
        data = new String(inputData.readAllBytes());
    }

    public BasicInfo buildBasicInfo() {
        return new BasicInfo.Builder().withName(readName()).withHeight(readHeight())
                   .withWeight(readWeight()).withTypes(readTypes()).build();
    }

    public String readName() {
        JsonPath encountersPath = JsonPath.compile("$..forms..name");
        JSONArray name = encountersPath.read(this.data);
        return name.get(0).toString();
    }

    public List<String> readTypes() {
        List<String> typeList = new ArrayList<>();
        JsonPath encountersPath = JsonPath.compile("$..types..type..name");
        JSONArray typesArray = encountersPath.read(this.data);
        if (typesArray.size() > 1){
            typeList.add(typesArray.get(0).toString());
            typeList.add(typesArray.get(1).toString());
        }
        else {
            typeList.add(typesArray.get(0).toString());
        }
        return typeList;
    }

    public int readHeight() {
        JsonPath encountersPath = JsonPath.compile("$..height");
        JSONArray height = encountersPath.read(this.data);
        return (int) height.get(0);
    }

    public int readWeight() {
        JsonPath encountersPath = JsonPath.compile("$..weight");
        JSONArray weight = encountersPath.read(this.data);
        return (int) weight.get(0);
    }
}
