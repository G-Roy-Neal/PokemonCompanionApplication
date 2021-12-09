package edu.bsu.cs;

import edu.bsu.cs.basicinfo.BasicInfoEngine;
import edu.bsu.cs.image.ImageBuilder;
import edu.bsu.cs.query.QueryEngine;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class App extends Application {
    private final Font labelFont = Font.loadFont("File:src/main/resources/fonts/november.ttf", 20);
    private final Font dataFont = Font.loadFont("File:src/main/resources/fonts/pokemon_pixel_font.ttf", 24);

    private final QueryEngine queryEngine = new QueryEngine();
    private final ImageBuilder imageBuilder = new ImageBuilder();
    private final BasicInfoEngine basicInfoEngine = new BasicInfoEngine();
    private final InformationWindow window = new InformationWindow();
    private TextField userInput;
    private Label pokemonName;
    private Label pokemonHeight;
    private Label pokemonType;
    private Label pokemonWeight;
    private TextField pokemonNameOutput;
    private TextField pokemonHeightOutput;
    private TextField pokemonTypeOutput;
    private TextField pokemonWeightOutput;
    private ImageView imageView = null;
    private Button searchButton;
    private Button locationButton;
    private Button movesButton;
    private Button typeButton;

    ScrollPane outPutDataScrollPane = new ScrollPane();

    HBox querySearchButtonBox = new HBox();
    HBox infoSelectorBox = new HBox();
    HBox pokemonNameBox = new HBox();
    HBox pokemonHeightBox = new HBox();
    HBox pokemonWeightBox = new HBox();
    HBox pokemonTypeBox = new HBox();
    GridPane grid = new GridPane();

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createLocationsGUI());
        primaryStage.setTitle("Brilliant Diamond & Shining Pearl Companion App");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setOnShown(e ->
                outPutDataScrollPane.lookup(".viewport").setStyle("-fx-background-color: azure;"));
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
    }

    private void setActionEvents() {
        searchButton.setOnAction(event -> queryTask(userInput.getText()));
        userInput.setOnAction(event -> queryTask(userInput.getText()));
        userInput.setOnMouseClicked(event -> userInput.clear());
        setInfoButtonEvents();
    }

    private void setInfoButtonEvents() {
        locationButton.setOnAction(event -> locationButtonEvent());
        movesButton.setOnAction(event -> moveButtonEvent());
        typeButton.setOnAction(event -> typeButtonEvent());
    }

    private void locationButtonEvent() {
        try {
            window.setLocation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveButtonEvent(){
        try {
            window.setMoves();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void typeButtonEvent(){
        try {
            window.setDamageRelations();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializePokeballImage() {
        Image image;
        image = new Image(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("pokeball-clipart-silhouette-5.png")));
        imageView = new ImageView(image);
    }

    private void initializeSearchComponents() {
        userInput = new TextField("Search");
        userInput.setFont(dataFont);
        searchButton = new Button("\uD83D\uDD0E");
        searchButton.setFont(Font.font(17));
        initializeInfoButtons();
    }

    private void initializeInfoButtons(){
        locationButton = new Button("Locations");
        movesButton = new Button("Moves");
        typeButton = new Button("Types");
        setInfoButtonsFont();
    }

    private void setInfoButtonsFont() {
        locationButton.setFont(labelFont);
        movesButton.setFont(labelFont);
        typeButton.setFont(labelFont);
    }

    private void setDataLabels(){
        pokemonName = new Label("Name: ");
        pokemonHeight = new Label("Height: ");
        pokemonType = new Label("Type: ");
        pokemonWeight = new Label("Weight: ");
        setDataLabelFont();
    }

    private void setDataLabelFont(){
        pokemonName.setFont(labelFont);
        pokemonHeight.setFont(labelFont);
        pokemonType.setFont(labelFont);
        pokemonWeight.setFont(labelFont);
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
        grid.setPrefSize(1080, 720);
        grid.setStyle("-fx-background-color: azure;");

        alignUiBoxes();
        addElementsToUiBoxes();
        addElementsToGrid();
        createColumnConstraints();
        createRowConstraints();
        scaleImage();
        scaleUiElements();
        setActionEvents();

        return grid;
    }

    private void alignUiBoxes() {
        pokemonNameBox.alignmentProperty().setValue(Pos.CENTER);
        pokemonHeightBox.alignmentProperty().setValue(Pos.CENTER);
        pokemonWeightBox.alignmentProperty().setValue(Pos.CENTER);
        pokemonTypeBox.alignmentProperty().setValue(Pos.CENTER);
        infoSelectorBox.alignmentProperty().setValue(Pos.CENTER);
        querySearchButtonBox.alignmentProperty().setValue(Pos.CENTER);
    }

    private void addElementsToUiBoxes() {
        querySearchButtonBox.getChildren().addAll(userInput, searchButton);
        pokemonNameBox.getChildren().addAll(pokemonName, pokemonNameOutput);
        pokemonHeightBox.getChildren().addAll(pokemonHeight, pokemonHeightOutput);
        pokemonWeightBox.getChildren().addAll(pokemonWeight, pokemonWeightOutput);
        pokemonTypeBox.getChildren().addAll(pokemonType, pokemonTypeOutput);
        infoSelectorBox.getChildren().addAll(locationButton, movesButton, typeButton);
        outPutDataScrollPane.setStyle("-fx-background-color: azure;");
        window.setStyle("-fx-background-color: azure;");
        outPutDataScrollPane.setContent(window);
    }

    private void scaleNameHeightWeightTypeBoxes () {
        pokemonNameOutput.prefWidthProperty().bind(pokemonNameBox.widthProperty().multiply(.5));
        pokemonHeightOutput.prefWidthProperty().bind(pokemonHeightBox.widthProperty().multiply(.5));
        pokemonWeightOutput.prefWidthProperty().bind(pokemonWeightBox.widthProperty().multiply(.5));
        pokemonTypeOutput.prefWidthProperty().bind(pokemonTypeBox.widthProperty().multiply(.5));
    }

    private void addElementsToGrid() {
        grid.add(infoSelectorBox, 2,0,2,1);
        grid.add(outPutDataScrollPane, 2,1,2,3);
        grid.add(querySearchButtonBox, 0,0,2,1);
        grid.add(imageView, 0,1,2,1);
        grid.add(pokemonNameBox, 0,2,1,1);
        grid.add(pokemonTypeBox, 0,3,1,1);
        grid.add(pokemonHeightBox, 1,2,1,1);
        grid.add(pokemonWeightBox, 1,3,1,1);
    }

    private void createColumnConstraints() {
        ColumnConstraints columnWidthConstraint = new ColumnConstraints();
        columnWidthConstraint.setPercentWidth(25);
        columnWidthConstraint.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().addAll(columnWidthConstraint, columnWidthConstraint, columnWidthConstraint, columnWidthConstraint);
    }

    private void createRowConstraints() {
        RowConstraints smallRowConstraint = new RowConstraints();
        RowConstraints largeRowConstraint = new RowConstraints();
        smallRowConstraint.setValignment(VPos.CENTER);
        smallRowConstraint.setPercentHeight(5);
        largeRowConstraint.setPercentHeight(85);
        grid.getRowConstraints().addAll(smallRowConstraint, largeRowConstraint, smallRowConstraint, smallRowConstraint);
    }

    private void scaleImage () {
        imageView.setPreserveRatio(true);
        imageView.fitHeightProperty().bind(outPutDataScrollPane.heightProperty().multiply(.85));
        imageView.fitWidthProperty().bind(outPutDataScrollPane.widthProperty().multiply(.85));
    }

    private void scaleUiElements () {
        scaleImage();
        scaleNameHeightWeightTypeBoxes();
        infoSelectorBox.prefWidthProperty().bind(outPutDataScrollPane.widthProperty());
        userInput.prefWidthProperty().bind(outPutDataScrollPane.widthProperty().subtract(searchButton.widthProperty()));
        locationButton.prefWidthProperty().bind(infoSelectorBox.widthProperty().divide(3));
        movesButton.prefWidthProperty().bind(infoSelectorBox.widthProperty().divide(3));
        typeButton.prefWidthProperty().bind(infoSelectorBox.widthProperty().divide(3));
    }


    private void queryTask (String text) {
        checkIfEmpty();
        disableEditing();
        InputStream inputData;
        try {
            inputData = queryEngine.getInputStream(text);
            ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
            inputData.transferTo(temporaryByteArray);
            InputStream firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
            imageView.setImage(imageBuilder.getPokemonImage(imageBuilder.getPokemonId(firstClone)));
            InputStream secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
            setBasicInfo(secondClone);
            InputStream thirdClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
            window.loadData(thirdClone);
            window.setLocation();
        } catch (IOException e) {
            window.setPokemonNotFound();
        }
        enableEditing();
    }
        private void setBasicInfo (InputStream fourthClone) throws IOException {
            List<String> infoList = basicInfoEngine.getBaisicInfo(fourthClone);
            pokemonNameOutput.setText(infoList.get(0));
            pokemonTypeOutput.setText(infoList.get(1));
            pokemonHeightOutput.setText(infoList.get(2));
            pokemonWeightOutput.setText(infoList.get(3));
            setBasicInfoFont();
        }

        private void setBasicInfoFont () {
            pokemonNameOutput.setFont(dataFont);
            pokemonTypeOutput.setFont(dataFont);
            pokemonHeightOutput.setFont(dataFont);
            pokemonWeightOutput.setFont(dataFont);
        }

        private void enableEditing () {
            userInput.setEditable(true);
            searchButton.setDisable(false);
        }

        private void disableEditing () {
            userInput.setEditable(false);
            searchButton.setDisable(true);

        }

        private void checkIfEmpty () {
            if (userInput.getText().equals("")) {
                enableEditing();
            }
        }
    }