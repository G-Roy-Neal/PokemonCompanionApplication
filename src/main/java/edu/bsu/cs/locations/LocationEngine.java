package edu.bsu.cs.locations;

import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;

public interface LocationEngine {
    String getLocations(InputStream inputData) throws IOException;
}
