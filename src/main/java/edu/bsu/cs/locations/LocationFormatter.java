package edu.bsu.cs.locations;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class LocationFormatter {

    public String transformLocationDescription(String rawLocation) {
        if (checkIfLocationInGame(rawLocation).equals(true)) {
            return formatLocationDescription(rawLocation);
        }
        return "";
    }

    private String formatLocationDescription(String rawLocation) {
        rawLocation = rawLocation.replace('-', ' ');
        String replacedLocation = rawLocation.replace("sinnoh ", "");
        return replacedLocation.substring(0, 1).toUpperCase() + replacedLocation.substring(1);
    }

    private Object checkIfLocationInGame(String rawLocation) {
        return rawLocation.contains("sinnoh");
    }
}
