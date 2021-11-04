package edu.bsu.cs;

import edu.bsu.cs.locations.PokemonLocation;
import edu.bsu.cs.locations.PokemonLocationBuilder;
import edu.bsu.cs.locations.PokemonLocationFormatter;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PokemonApp extends Application {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Runnable revisionTask = new locationTask();

    private final TextField userInput;
    private Label pokemonName;
    private Label pokemonHeight;
    private Label pokemonType;
    private Label pokemonWeight;
    private final ComboBox<String> dropdownMenu;
    private final ImageView imageView;
    private final TextArea locationOutput;
    private final Button searchButton;

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(LocationsGUI());
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
            image = new Image(new FileInputStream("C:\\Users\\m910g\\OneDrive\\Desktop\\CS222\\PokemonCompanionApplication-wallace-vaal-neal-kring\\src\\main\\resources\\Squirtle.png"));
        } catch (FileNotFoundException e) {
            System.out.println("javaFX sucks");
        }
        imageView = new ImageView(image);

        locationOutput.setEditable(false);
        searchButton.setOnAction(event -> executor.execute(revisionTask));
    }

    private void setDataLabels(){
        pokemonName = new Label("Name");
        pokemonHeight = new Label("Height");
        pokemonType = new Label("Type");
        pokemonWeight = new Label("Weight");
    }

    private Parent LocationsGUI() {
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

        double height = imageView.getImage().heightProperty().getValue();
        double width = imageView.getImage().widthProperty().getValue();
        System.out.println(height);
        System.out.println(width);
        double imageRatio = height/width;
        System.out.println(imageRatio);
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
