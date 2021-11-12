package edu.bsu.cs.BaisicInfo;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaisicInfoBuilder {
    private final InputStream firstClone;
    private final InputStream secondClone;
    private final InputStream thirdClone;
    private final InputStream fourthClone;

    public BaisicInfoBuilder(InputStream inputData) throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        this.firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        this.secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        this.thirdClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        this.fourthClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
    }

    public BaisicInfo buildBaisicInfo() throws IOException {
        return new BaisicInfo.Builder().withName(readName()).withHeight(readHeight()).withWeight(readWeight()).withTypes(readTypes()).build();
    }

    public String readName() throws IOException {
        JsonPath encountersPath = JsonPath.compile("$..forms..name");
        JSONArray name = encountersPath.read(this.firstClone);
        return name.get(0).toString();
    }

    public List<String> readTypes() throws IOException {
        List<String> typeList = new ArrayList<>();
        JsonPath encountersPath = JsonPath.compile("$..types..type..name");
        JSONArray typesArray = encountersPath.read(this.secondClone);
        if (typesArray.size() > 1){
            typeList.add(typesArray.get(0).toString());
            typeList.add(typesArray.get(1).toString());
        }
        return typeList;
    }

    public int readHeight() throws IOException {
        JsonPath encountersPath = JsonPath.compile("$..height");
        JSONArray height = encountersPath.read(this.thirdClone);
        return (int) height.get(0);
    }

    public int readWeight() throws IOException{
        JsonPath encountersPath = JsonPath.compile("$..weight");
        JSONArray weight = encountersPath.read(this.fourthClone);
        return (int) weight.get(0);
    }
}
