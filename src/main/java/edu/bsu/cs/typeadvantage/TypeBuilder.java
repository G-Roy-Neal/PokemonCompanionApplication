package edu.bsu.cs.typeadvantage;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TypeBuilder {

    private InputStream firstClone;
    private InputStream secondClone;

    public TypeBuilder(InputStream inputData) throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        this.firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        this.secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
    }

    public List<Type> buildTypes() throws IOException {
        List<Type> builtList = new ArrayList<>();
        List<URL> urlList = getUrl();
        List<String> typeList = getType();
        if (urlList.size() > 1){
            for(int i = 0; i < urlList.size(); i++){
                Type type = new Type.Builder().withType(typeList.get(i)).withURL(urlList.get(i)).build();
                builtList.add(type);
            }
        }
        else{
            Type type = new Type.Builder().withType(typeList.get(0)).withURL(urlList.get(0)).build();
            builtList.add(type);
        }
        return builtList;
    }

    public List<URL> getUrl() throws IOException{
        List<URL> urlList = new ArrayList<>();
        JsonPath typePath = JsonPath.compile("$..type..url");
        JSONArray typeArray = typePath.read(firstClone);
        if (typeArray.size() > 1){
            URL urlOne = new URL (typeArray.get(typeArray.size() - 2).toString());
            urlList.add(urlOne);
            URL urlTwo = new URL (typeArray.get(typeArray.size() - 1).toString());
            urlList.add(urlTwo);
        }
        else {
            URL urlOne = new URL (typeArray.get(typeArray.size() - 1).toString());
            urlList.add(urlOne);
        }
        return urlList;
    }

    public List<String> getType() throws IOException{
        List<String> typeList = new ArrayList<>();
        JsonPath typePath = JsonPath.compile("$..type..name");
        JSONArray typeArray = typePath.read(secondClone);
        if (typeArray.size() > 1){
            String typeOne = typeArray.get(typeArray.size() - 2).toString();
            typeList.add(typeOne);
            String typeTwo = typeArray.get(typeArray.size() - 1).toString();
            typeList.add(typeTwo);
        }
        else {
            String typeOne = typeArray.get(typeArray.size() - 1).toString();
            typeList.add(typeOne);
        }
        return typeList;
    }
}
