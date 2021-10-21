package edu.bsu.cs;

import com.jayway.jsonpath.JsonPath;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CheckGeneration {
    public boolean checkGeneration (ByteArrayOutputStream data) {
        ArrayList<String> generation = JsonPath.read(data.toString(), "$..generation..name");
        return generation.contains("generation-vi");
    }
}
