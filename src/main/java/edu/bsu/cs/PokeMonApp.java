package edu.bsu.cs;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokeMonApp extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable revisionTask = new locationTask();

    private final TextField userInput;
    private final Button searchLocationsButton;
    private final TextArea locationOutput;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(LocationsGUI());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PokeMonApp() {
        userInput = new TextField();
        searchLocationsButton = new Button("Search Locations");
        locationOutput = new TextArea();
        locationOutput.setPrefHeight(600);
        locationOutput.setEditable(false);
        searchLocationsButton.setOnAction(event -> executor.execute(revisionTask));
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
            locationOutput.setText("");

            UrlBuilder urlBuilder = new UrlBuilder();
            QuerySearcher querySearcher = new QuerySearcher();

            String formattedURL = urlBuilder.buildSearchUrl(userInput.getText());
            try {
                InputStream rawData = querySearcher.DataFromUrl(formattedURL);
                PokemonLocationBuilder pokemonLocationBuilder = new PokemonLocationBuilder(rawData);
                List<PokemonLocation> locationsList = pokemonLocationBuilder.buildLocationList();
                String formattedLocationString = PokemonLocationFormatter.formatLocationList(locationsList);
                locationOutput.setText(formattedLocationString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            enableEditing();
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
