package edu.bsu.cs.locations;

import java.io.IOException;
import java.io.InputStream;

public interface LocationEngine {
    String getLocations(InputStream inputData) throws IOException;
}
