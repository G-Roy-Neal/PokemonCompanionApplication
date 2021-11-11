package edu.bsu.cs;

import edu.bsu.cs.PokemonImage.ImageEngine;
import edu.bsu.cs.PokemonImage.OnlineImageEngine;
import edu.bsu.cs.locations.LocationEngine;
import edu.bsu.cs.locations.OnlineLocationEngine;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class App extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable queryTask = new locationTask();
    private final Runnable dropdownTask = new dropdownTask();

    private final TextField userInput;
    private Label pokemonName;
    private Label pokemonHeight;
    private Label pokemonType;
    private Label pokemonWeight;
    private final ComboBox<String> dropdownMenu;
    private final ImageView imageView;
    private final TextArea informationOutput;
    private final Button searchButton;
    private final LocationEngine locationEngine = new OnlineLocationEngine();
    private final QueryEngine queryEngine = new OnlineQueryEngine();
    private final MoveEngine moveEngine = new OnlineMoveEngine();
    private final ImageEngine imageEngine = new OnlineImageEngine();
    private String moveResult;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createLocationsGUI());
        primaryStage.setTitle("Brilliant Diamond & Shining Pearl Companion App");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public App() {
        ObservableList<String> comboBoxArrayList = FXCollections.observableArrayList("Locations", "Moves");
        userInput = new TextField("Search");
        setDataLabels();
        dropdownMenu = new ComboBox<>(comboBoxArrayList);
        searchButton = new Button("\uD83D\uDD0E");
        informationOutput = new TextArea();

        Image image;
        image = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("pokeball-clipart-silhouette-5.png")));
        imageView = new ImageView(image);


        informationOutput.setEditable(false);
        searchButton.setOnAction(event -> executor.execute(queryTask));
        userInput.setOnAction(event -> executor.execute(queryTask));
        userInput.setOnMouseClicked(event -> userInput.clear());
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
        grid.add(informationOutput, 0,1,2,3);
        grid.add(querySearchButtonBox, 2,0,2,1);
        grid.add(imageView, 2,1,2,1);
        grid.add(pokemonName, 2,2,1,1);
        grid.add(pokemonType, 2,3,1,1);
        grid.add(pokemonHeight, 3,2,1,1);
        grid.add(pokemonWeight, 3,3,1,1);

        ColumnConstraints column1 = new ColumnConstraints();
        ColumnConstraints column2 = new ColumnConstraints();
        ColumnConstraints column3 = new ColumnConstraints();
        ColumnConstraints column4 = new ColumnConstraints();
        column1.setPercentWidth(25);
        column2.setPercentWidth(25);
        column3.setPercentWidth(25);
        column4.setPercentWidth(25);
        grid.getColumnConstraints().addAll(column1, column2, column3, column4);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(500);

        return grid;
    }
    private final class dropdownTask implements Runnable {
        @Override
        public void run(){
            int selectedIndex = dropdownMenu.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0){
                informationOutput.setText(moveResult);
            }
            if (selectedIndex == 1){
                informationOutput.setText(moveResult);
            }
        }
    }

    private final class locationTask implements Runnable {

        @Override
        public void run() {
            disableEditing();
            informationOutput.setText("");
            try {
                InputStream inputData = queryEngine.getInputStream(userInput.getText());
                ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
                inputData.transferTo(temporaryByteArray);
                InputStream firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                InputStream secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                InputStream thirdClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                imageView.setImage(imageEngine.getImage(thirdClone));
                String locationResult = locationEngine.getLocations(firstClone);
                moveResult = moveEngine.getMoves(secondClone);
                executor.execute(dropdownTask);

            } catch (IOException e) {
                informationOutput.setText("Search is not a valid Pokemon");
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
