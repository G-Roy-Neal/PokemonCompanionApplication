package edu.bsu.cs;

import edu.bsu.cs.BasicInfo.BasicInfoEngine;
import edu.bsu.cs.BasicInfo.OnlineBasicInfoEngine;
import edu.bsu.cs.PokemonImage.ImageEngine;
import edu.bsu.cs.PokemonImage.OnlineImageEngine;
import edu.bsu.cs.locations.LocationEngine;
import edu.bsu.cs.locations.OnlineLocationEngine;
import edu.bsu.cs.moves.MoveEngine;
import edu.bsu.cs.moves.OnlineMoveEngine;
import edu.bsu.cs.query.OnlineQueryEngine;
import edu.bsu.cs.query.QueryEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class App extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable queryTask = new queryTask();
    private final Runnable dropdownTask = new dropdownTask();

    private TextField userInput;
    private Label pokemonName;
    private Label pokemonHeight;
    private Label pokemonType;
    private Label pokemonWeight;
    private TextField pokemonNameOutput;
    private TextField pokemonHeightOutput;
    private TextField pokemonTypeOutput;
    private TextField pokemonWeightOutput;
    private ComboBox<String> dropdownMenu;
    private ImageView imageView = null;
    private TextArea informationOutput;
    private Button searchButton;
    private final LocationEngine locationEngine = new OnlineLocationEngine();
    private final QueryEngine queryEngine = new OnlineQueryEngine();
    private final MoveEngine moveEngine = new OnlineMoveEngine();
    private final ImageEngine imageEngine = new OnlineImageEngine();
    private final BasicInfoEngine baisicInfoEngine = new OnlineBasicInfoEngine();
    private String moveResult;
    private String locationResult;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createLocationsGUI());
        primaryStage.setTitle("Brilliant Diamond & Shining Pearl Companion App");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
    }

    public App() {
        initializeSearchComponents();
        initializeBasicInfoTextFields();
        initializePokeballImage();
        setDataLabels();
        setActionEvents();
    }

    private void setActionEvents() {
        searchButton.setOnAction(event -> executor.execute(queryTask));
        userInput.setOnAction(event -> executor.execute(queryTask));
        userInput.setOnMouseClicked(event -> userInput.clear());
        dropdownMenu.setOnAction(event -> executor.execute(queryTask));
    }

    private void initializePokeballImage() {
        Image image;
        image = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("pokeball-clipart-silhouette-5.png")));
        imageView = new ImageView(image);
    }

    private void initializeSearchComponents() {
        ObservableList<String> comboBoxArrayList = FXCollections.observableArrayList("Locations", "Moves");
        userInput = new TextField("Search");
        dropdownMenu = new ComboBox<>(comboBoxArrayList);
        searchButton = new Button("\uD83D\uDD0E");
        informationOutput = new TextArea();
        informationOutput.setEditable(false);
    }

    private void setDataLabels(){
        pokemonName = new Label("Name: ");
        pokemonHeight = new Label("Height: ");
        pokemonType = new Label("Type: ");
        pokemonWeight = new Label("Weight: ");
    }
    private void initializeBasicInfoTextFields() {
        pokemonNameOutput = new TextField();
        pokemonHeightOutput = new TextField();
        pokemonWeightOutput = new TextField();
        pokemonTypeOutput = new TextField();
        disableEditingBasicInfoTextFields();
    }

    private void disableEditingBasicInfoTextFields () {
        pokemonNameOutput.setEditable(false);
        pokemonHeightOutput.setEditable(false);
        pokemonWeightOutput.setEditable(false);
        pokemonTypeOutput.setEditable(false);
    }

    private Parent createLocationsGUI() {
        GridPane grid = new GridPane();
        grid.setPrefSize(852, 480);

        HBox querySearchButtonBox = new HBox();
        HBox pokemonNameBox = new HBox();
        HBox pokemonHeightBox = new HBox();
        HBox pokemonWeightBox = new HBox();
        HBox pokemonTypeBox = new HBox();

        pokemonNameBox.alignmentProperty().setValue(Pos.CENTER);
        pokemonHeightBox.alignmentProperty().setValue(Pos.CENTER);
        pokemonWeightBox.alignmentProperty().setValue(Pos.CENTER);
        pokemonTypeBox.alignmentProperty().setValue(Pos.CENTER);

        querySearchButtonBox.getChildren().addAll(userInput, searchButton);
        pokemonNameBox.getChildren().addAll(pokemonName, pokemonNameOutput);
        pokemonHeightBox.getChildren().addAll(pokemonHeight, pokemonHeightOutput);
        pokemonWeightBox.getChildren().addAll(pokemonWeight, pokemonWeightOutput);
        pokemonTypeBox.getChildren().addAll(pokemonType, pokemonTypeOutput);

        pokemonNameOutput.prefWidthProperty().bind(pokemonNameBox.widthProperty().multiply(.5));
        pokemonHeightOutput.prefWidthProperty().bind(pokemonHeightBox.widthProperty().multiply(.5));
        pokemonWeightOutput.prefWidthProperty().bind(pokemonWeightBox.widthProperty().multiply(.5));
        pokemonTypeOutput.prefWidthProperty().bind(pokemonTypeBox.widthProperty().multiply(.5));

        grid.add(dropdownMenu, 0,0,2,1);
        grid.add(informationOutput, 0,1,2,3);
        grid.add(querySearchButtonBox, 2,0,2,1);
        grid.add(imageView, 2,1,2,1);
        grid.add(pokemonNameBox, 2,2,1,1);
        grid.add(pokemonTypeBox, 2,3,1,1);
        grid.add(pokemonHeightBox, 3,2,1,1);
        grid.add(pokemonWeightBox, 3,3,1,1);

        querySearchButtonBox.alignmentProperty().setValue(Pos.CENTER);

        ColumnConstraints columnWidthConstraint = new ColumnConstraints();
        columnWidthConstraint.setPercentWidth(25);
        columnWidthConstraint.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().addAll(columnWidthConstraint, columnWidthConstraint, columnWidthConstraint, columnWidthConstraint);

        RowConstraints smallRowConstraint = new RowConstraints();
        RowConstraints largeRowConstraint = new RowConstraints();
        smallRowConstraint.setValignment(VPos.CENTER);
        smallRowConstraint.setPercentHeight(5);
        largeRowConstraint.setPercentHeight(85);
        grid.getRowConstraints().addAll(smallRowConstraint, largeRowConstraint, smallRowConstraint, smallRowConstraint);

        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(informationOutput.heightProperty().multiply(.85));
        imageView.fitWidthProperty().bind(informationOutput.widthProperty().multiply(.85));

        dropdownMenu.prefWidthProperty().bind(informationOutput.widthProperty());
        userInput.prefWidthProperty().bind(informationOutput.widthProperty().subtract(searchButton.widthProperty()));

        return grid;
    }
    private final class dropdownTask implements Runnable {
        @Override
        public void run(){
            int selectedIndex = dropdownMenu.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0){
                informationOutput.setText(locationResult);
            }
            else if (selectedIndex == 1){
                informationOutput.setText(moveResult);
            }
        }
    }

    private final class queryTask implements Runnable {

        @Override
        public void run() {
            disableEditing();
            informationOutput.setText("");
            checkIfEmpty();
            try {
                InputStream inputData = queryEngine.getInputStream(userInput.getText());
                ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
                inputData.transferTo(temporaryByteArray);
                InputStream firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                locationResult = locationEngine.getLocations(firstClone);
                InputStream secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                moveResult = moveEngine.getMoves(secondClone);
                InputStream thirdClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                imageView.setImage(imageEngine.getImage(thirdClone));
                InputStream fourthClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
                setBasicInfo(fourthClone);
                executor.execute(dropdownTask);

            } catch (IOException e) {
                informationOutput.setText("Search is not a valid Pokemon");
            }
            enableEditing();
        }

        private void setBasicInfo(InputStream fourthClone) throws IOException {
            List<String> infoList = baisicInfoEngine.getBaisicInfo(fourthClone);
            pokemonNameOutput.setText(infoList.get(0));
            pokemonTypeOutput.setText(infoList.get(1));
            pokemonHeightOutput.setText(infoList.get(2));
            pokemonWeightOutput.setText(infoList.get(3));
        }

        private void enableEditing() {
            userInput.setEditable(true);
            searchButton.setDisable(false);
        }

        private void disableEditing() {
            userInput.setEditable(false);
            searchButton.setDisable(true);

        }

        private void checkIfEmpty() {
            if (userInput.getText().equals("")) {
                informationOutput.setText("The Search Box is Empty");
                enableEditing();
            }
        }
    }
}
