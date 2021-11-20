package edu.bsu.cs.basicinfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface BasicInfoEngine {
    List<String> getBaisicInfo(InputStream inputData) throws IOException;
}
