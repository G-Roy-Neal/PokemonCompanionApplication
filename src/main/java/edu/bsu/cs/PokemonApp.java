package edu.bsu.cs;

import edu.bsu.cs.locations.*;
import edu.bsu.cs.moves.MoveEngine;
import edu.bsu.cs.moves.OnlineMoveEngine;
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
import javafx.stage.Stage;
import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonApp extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable locationTask = new locationTask();
    private final Runnable dropdownTask = new dropdownTask();

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
    private final MoveEngine moveEngine = new OnlineMoveEngine();
    private String moveResult;
    private String locationResult;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createLocationsGUI());
        primaryStage.setTitle("Brilliant Diamond & Shining Pearl Companion App");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public PokemonApp() {
        ObservableList<String> comboBoxArrayList = FXCollections.observableArrayList("Locations", "Moves");
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
        dropdownMenu.setOnAction(event -> executor.execute(dropdownTask));
    }

    private void setDataLabels(){
        pokemonName = new Label("Name");
        pokemonHeight = new Label("Height");
        pokemonType = new Label("Type");
        pokemonWeight = new Label("Weight");
    }

    private Parent createLocationsGUI() {
        GridPane grid = new GridPane();
        HBox querySearchButtonBox = new HBox();
        querySearchButtonBox.getChildren().addAll(userInput, searchButton);
        
        grid.setGridLinesVisible(true);
        grid.add(dropdownMenu, 0,0,2,1);
        grid.add(locationOutput, 0,1,2,3);
        grid.add(querySearchButtonBox, 2,0,2,1);
        grid.add(imageView, 2,1,2,1);
        grid.add(pokemonName, 2,2,1,1);
        grid.add(pokemonType, 2,3,1,1);
        grid.add(pokemonHeight, 3,2,1,1);
        grid.add(pokemonWeight, 3,3,1,1);

        return grid;
    }
    private final class dropdownTask implements Runnable {
        @Override
        public void run(){
            int selectedIndex = dropdownMenu.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0){
                locationOutput.setText(locationResult);
            }
            else if (selectedIndex == 1){
                locationOutput.setText(moveResult);
            }
        }
    }

    private final class locationTask implements Runnable {

        @Override
        public void run() {
            disableEditing();
            locationOutput.setText("");
            try {
                InputStream inputData = queryEngine.getInputStream(userInput.getText());
                ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
                inputData.transferTo(temporaryByteArray);
                InputStream firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                InputStream secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                locationResult = locationEngine.getLocations(firstClone);
                moveResult = moveEngine.getMoves(secondClone);
                locationOutput.setText(locationResult);
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
