package edu.bsu.cs;

import edu.bsu.cs.locations.Location;
import edu.bsu.cs.locations.LocationBuilder;
import edu.bsu.cs.locations.LocationFormatter;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InformationWindow extends VBox {


    public InformationWindow(){
        getChildren().add(new Label("Please search For a Pokemon. "));
    }

    public void setLocation(InputStream inputStream) throws IOException {
        LocationBuilder builder = new LocationBuilder(inputStream);
        List<Location> locationsList = builder.buildLocationList();
        getChildren().clear();
        LocationFormatter formatter = new LocationFormatter();
        for (Location location:locationsList){
            String formattedLocation = formatter.transformLocationDescription(location.getLocation());
            if (!formattedLocation.equals("")) {
                getChildren().add(new HBox(new Label(formattedLocation)));
            }
        }
        if (getChildren().isEmpty()) {
            getChildren().add(new HBox(new Label("This Pokemon can not be captured in the wild in the Sinnoh region")));
        }
    }


}
