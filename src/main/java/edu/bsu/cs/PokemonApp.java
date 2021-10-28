package edu.bsu.cs;

import edu.bsu.cs.locations.PokemonLocation;
import edu.bsu.cs.locations.PokemonLocationBuilder;
import edu.bsu.cs.locations.PokemonLocationFormatter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonApp extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable revisionTask = new locationTask();

    private final TextField userInput;
    private final TextField pokemonName;
    private final TextField pokemonHeight;
    private final TextField pokemonType;
    private final TextField pokemonWeight;
    private final ComboBox<String> dropdownMenu;
    private final TextArea locationOutput;
    private final TextArea image;
    private final Button searchButton;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(LocationsGUI());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public PokemonApp() {
        ObservableList<String> comboBoxArrayList = FXCollections.observableArrayList("Locations", "Moves", "Type Advantages");
        userInput = new TextField("Search");
        pokemonName = new TextField("Name");
        pokemonHeight = new TextField("Height");
        pokemonType = new TextField("Type");
        pokemonWeight = new TextField("Weight");
        dropdownMenu = new ComboBox<>(comboBoxArrayList);
        searchButton = new Button("\uD83D\uDD0E");
        locationOutput = new TextArea();
        image = new TextArea("Image");

        locationOutput.setEditable(false);
        searchButton.setOnAction(event -> executor.execute(revisionTask));
    }

    private Parent LocationsGUI() {
        HBox parentBox = new HBox();
        HBox nameHeightBox = new HBox();
        HBox typeWeightBox = new HBox();
        HBox querySearchButtonBox = new HBox();
        VBox dropDownOutputBox = new VBox();
        VBox searchImageDataBox = new VBox();

        parentBox.getChildren().add(dropDownOutputBox);
        parentBox.getChildren().add(searchImageDataBox);

        dropDownOutputBox.getChildren().add(dropdownMenu);
        dropDownOutputBox.getChildren().add(locationOutput);

        searchImageDataBox.getChildren().add(querySearchButtonBox);
        searchImageDataBox.getChildren().add(image);
        searchImageDataBox.getChildren().add(nameHeightBox);
        searchImageDataBox.getChildren().add(typeWeightBox);

        querySearchButtonBox.getChildren().add(userInput);
        querySearchButtonBox.getChildren().add(searchButton);

        nameHeightBox.getChildren().add(pokemonName);
        nameHeightBox.getChildren().add(pokemonHeight);

        typeWeightBox.getChildren().add(pokemonType);
        typeWeightBox.getChildren().add(pokemonWeight);

        return parentBox;
    }

    private final class locationTask implements Runnable {

        @Override
        public void run() {
            disableEditing();
            locationOutput.setText("");
            UrlBuilder urlBuilder = new UrlBuilder();
            QuerySearcher querySearcher = new QuerySearcher();
            PokemonLocationFormatter formatter = new PokemonLocationFormatter();
            String formattedURL = urlBuilder.buildSearchUrl(userInput.getText());
            try {
                InputStream rawData = querySearcher.getInputStream(formattedURL);
                PokemonLocationBuilder pokemonLocationBuilder = new PokemonLocationBuilder(rawData);
                List<PokemonLocation> locationsList = pokemonLocationBuilder.buildLocationList();
                String formattedLocationString = formatter.formatLocationList(locationsList);
                locationOutput.setText(formattedLocationString);
            } catch (IOException e) {
                locationOutput.setText("Search is not a valid Pokemon");
            }
            enableEditing();
        }

        private void enableEditing() {
            userInput.setEditable(true);
            searchButton.setDisable(false);
        }

        private void disableEditing() {
            userInput.setEditable(false);
            searchButton.setDisable(true);
        }
    }
}
