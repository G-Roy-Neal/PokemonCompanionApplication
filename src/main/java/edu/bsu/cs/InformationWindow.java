package edu.bsu.cs;

import edu.bsu.cs.locations.LocationEngine;
import edu.bsu.cs.moves.MoveEngine;
import edu.bsu.cs.typeadvantage.TypeAdvantageEngine;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class InformationWindow extends VBox {
    private final Font labelFont = Font.loadFont("File:src/main/resources/fonts/november.ttf", 20);

    private InputStream pokemonData;

    public InformationWindow() {
        Label label = new Label("Please search For a Pokemon. ");
        label.setFont(labelFont);
        getChildren().add(label);
    }

    public void loadData(InputStream inputStream) throws IOException {
        this.pokemonData = inputStream;
    }

    private InputStream copyData() throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        this.pokemonData.transferTo(temporaryByteArray);
        this.pokemonData = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        return new ByteArrayInputStream(temporaryByteArray.toByteArray());
    }

    public void setLocation() throws IOException {
        InputStream inputData = copyData();
        getChildren().clear();
        LocationEngine locationEngine = new LocationEngine();
        List<HBox> locationList = locationEngine.generateLocationList(inputData);
        for(HBox location: locationList){
            getChildren().add(location);
        }
    }

    public void setMoves() throws IOException {
        InputStream inputData = copyData();
        getChildren().clear();
        MoveEngine moveEngine = new MoveEngine();
        List<HBox> moveList = moveEngine.generateMoveList(inputData);
        for(HBox move: moveList){
            getChildren().add(move);
        }
    }

    public void setDamageRelations() throws IOException {
        InputStream inputData = copyData();
        getChildren().clear();
        TypeAdvantageEngine typeAdvantageEngine = new TypeAdvantageEngine();
        List<HBox> typeAdvantageList = typeAdvantageEngine.generateTypeAdvantageList(inputData);
        for(HBox typeAdvantage: typeAdvantageList){
            getChildren().add(typeAdvantage);
        }
    }
    public void setPokemonNotFound() {
        getChildren().clear();
        Label label = new Label("The search is not a valid Pokemon");
        label.setFont(labelFont);
        getChildren().add(new HBox(label));
    }
}