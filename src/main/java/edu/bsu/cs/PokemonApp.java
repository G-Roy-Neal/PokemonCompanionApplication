package edu.bsu.cs;

import edu.bsu.cs.locations.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.stream.Location;
import java.io.*;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonApp extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable locationTask = new locationTask();

    private final TextField userInput;
    private Label pokemonName;
    private Label pokemonHeight;
    private Label pokemonType;
    private Label pokemonWeight;
    private final ComboBox<String> dropdownMenu;
    private final ImageView imageView;
    private final TextArea locationOutput;
    private final Button searchButton;
    private final LocationEngine locationEngine = new OnlineLocationEngine();
    private final QueryEngine queryEngine = new OnlineQueryEngine();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createLocationsGUI());
        primaryStage.setTitle("Brilliant Diamond & Shining Pearl Companion App");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public PokemonApp() {
        ObservableList<String> comboBoxArrayList = FXCollections.observableArrayList("Locations", "Moves", "Type Advantages");
        userInput = new TextField("Search");
        setDataLabels();
        dropdownMenu = new ComboBox<>(comboBoxArrayList);
        searchButton = new Button("\uD83D\uDD0E");
        locationOutput = new TextArea();

        Image image = null;
        try {
            image = new Image(new FileInputStream("src/main/resources/Squirtle.png"));
        } catch (FileNotFoundException e) {
            System.out.println("javaFX sucks");
        }
        imageView = new ImageView(image);

        locationOutput.setEditable(false);
        searchButton.setOnAction(event -> executor.execute(locationTask));
        userInput.setOnAction(event -> executor.execute(locationTask));
    }

    private void setDataLabels(){
        pokemonName = new Label("Name");
        pokemonHeight = new Label("Height");
        pokemonType = new Label("Type");
        pokemonWeight = new Label("Weight");
    }

    private Parent createLocationsGUI() {
        HBox parentBox = new HBox();
        HBox nameHeightBox = new HBox();
        HBox typeWeightBox = new HBox();
        HBox querySearchButtonBox = new HBox();
        VBox dropDownOutputBox = new VBox();
        VBox searchImageDataBox = new VBox();

        parentBox.prefWidth(Double.MAX_VALUE);
        parentBox.prefHeight(Double.MAX_VALUE);

        dropDownOutputBox.prefWidthProperty().bind(parentBox.widthProperty().multiply(0.5));
        dropDownOutputBox.prefHeightProperty().bind(parentBox.heightProperty().multiply(0.1));

        searchImageDataBox.prefWidthProperty().bind(parentBox.widthProperty().multiply(0.5));
        searchImageDataBox.prefHeightProperty().bind(parentBox.heightProperty().multiply(0.5));


        parentBox.getChildren().addAll(dropDownOutputBox, searchImageDataBox);
        dropDownOutputBox.getChildren().addAll(dropdownMenu, locationOutput);
        searchImageDataBox.getChildren().addAll(querySearchButtonBox, imageView, nameHeightBox, typeWeightBox);
        querySearchButtonBox.getChildren().addAll(userInput, searchButton);
        nameHeightBox.getChildren().addAll(pokemonName, pokemonHeight);
        typeWeightBox.getChildren().addAll(pokemonType, pokemonWeight);


        dropdownMenu.prefWidthProperty().bind(locationOutput.widthProperty());
        locationOutput.prefHeightProperty().bind(parentBox.heightProperty());
        userInput.prefWidthProperty().bind((parentBox.widthProperty().multiply(0.5)).subtract(30));
        imageView.fitWidthProperty().bind(parentBox.heightProperty().multiply(0.8));
        imageView.fitHeightProperty().bind((parentBox.heightProperty().multiply(0.8)));
        return parentBox;
    }

    private final class locationTask implements Runnable {

        @Override
        public void run() {
            disableEditing();
            locationOutput.setText("");
            try {
                InputStream inputData = queryEngine.getInputStream(userInput.getText());
                String result = locationEngine.getLocations(inputData);
                locationOutput.setText(result);
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
