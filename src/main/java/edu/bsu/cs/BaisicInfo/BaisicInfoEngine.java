package edu.bsu.cs.BaisicInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface BaisicInfoEngine {
    List<String> getBaisicInfo(InputStream inputData) throws IOException;
}
