package edu.bsu.cs.locations;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocationEngine {
    private final Font labelFont = Font.loadFont("File:src/main/resources/fonts/november.ttf", 20);
    private final Font dataFont = Font.loadFont("File:src/main/resources/fonts/pokemon_pixel_font.ttf", 24);

    public List<HBox> generateLocationList(InputStream inputData) throws IOException {
        List<HBox> locationList = new ArrayList<>();
        LocationBuilder builder = new LocationBuilder(inputData);
        List<Location> locationsList = builder.buildLocationList();
        LocationFormatter formatter = new LocationFormatter();
        for (Location location : locationsList) {
            String formattedLocation = formatter.transformLocationDescription(location.getLocation());
            if (!formattedLocation.equals("")) {
                Label locationLabel = new Label(formattedLocation);
                locationLabel.setFont(dataFont);
                locationList.add(new HBox(locationLabel));
            }
        }
        if (locationList.isEmpty()) {
            Label lineOne = new Label("This Pokemon can not be captured in the wild");
            Label lineTwo = new Label("in the Sinnoh region");
            lineOne.setFont(labelFont);
            lineTwo.setFont(labelFont);
            locationList.add(new HBox(lineOne));
            locationList.add(new HBox(lineTwo));
        }
        return locationList;
    }
}
