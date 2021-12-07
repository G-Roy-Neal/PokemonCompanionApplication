package edu.bsu.cs.basicinfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BasicInfoEngine {
    public List<String> getBaisicInfo(InputStream inputData) throws IOException {
        BasicInfoReader infoBuilder = new BasicInfoReader(inputData);
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(infoBuilder.buildBasicInfo());
        return infoFormatter.formatInfo();
    }
}
