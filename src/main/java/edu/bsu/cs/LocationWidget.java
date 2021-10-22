package edu.bsu.cs;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LocationWidget extends VBox {
    public LocationWidget() {
        final Executor executor = Executors.newSingleThreadExecutor();
        final Runnable locationTask = new locationTask();

        final TextField userInput;
        final Button searchLocationsButton;
        final TextArea locationOutput;

        userInput = new TextField();
        searchLocationsButton = new Button("Search Locations");
        locationOutput = new TextArea();
        locationOutput.setPrefHeight(600);
        locationOutput.setEditable(false);
        searchLocationsButton.setOnAction(event -> executor.execute(locationTask));
    }

    private Parent LocationsGUI() {
        VBox vBox = new VBox();
        vBox.setPrefSize(1280, 720);
        vBox.getChildren().add(userInput);
        vBox.getChildren().add(searchLocationsButton);
        vBox.getChildren().add(locationOutput);
        return vBox;
    }

    private final class locationTask implements Runnable {

        @Override
        public void run() {
            disableEditing();
            pauseThreadFor1Second();
            String locations = "locations";
            locationOutput.setText(locations);
            enableEditing();
        }

        private void pauseThreadFor1Second () {
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        private void enableEditing() {
            userInput.setEditable(true);
            searchLocationsButton.setDisable(false);
        }
        private void disableEditing() {
            userInput.setEditable(false);
            searchLocationsButton.setDisable(true);
        }
    }
}
