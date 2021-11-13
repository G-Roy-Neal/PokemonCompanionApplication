package edu.bsu.cs.BaisicInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OnlineBaisicInfoEngine implements BaisicInfoEngine{
    @Override
    public List<String> getBaisicInfo(InputStream inputData) throws IOException {
        BaisicInfoBuilder infoBuilder = new BaisicInfoBuilder(inputData);
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(infoBuilder.buildBaisicInfo());
        return infoFormatter.formatInfo();
    }
}
