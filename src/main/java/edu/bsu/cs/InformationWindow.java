package edu.bsu.cs;

import edu.bsu.cs.locations.Location;
import edu.bsu.cs.locations.LocationBuilder;
import edu.bsu.cs.locations.LocationFormatter;
import edu.bsu.cs.moves.Move;
import edu.bsu.cs.moves.MoveBuilder;
import edu.bsu.cs.moves.MoveFormatter;
import edu.bsu.cs.typeadvantage.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class InformationWindow extends VBox {
    private final Font labelFont = Font.font("Verdana", FontWeight.BOLD, 12);

    private InputStream pokemonData;

    public InformationWindow() {
        getChildren().add(new Label("Please search For a Pokemon. "));
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
        LocationBuilder builder = new LocationBuilder(inputData);
        List<Location> locationsList = builder.buildLocationList();
        getChildren().clear();
        LocationFormatter formatter = new LocationFormatter();
        for (Location location : locationsList) {
            String formattedLocation = formatter.transformLocationDescription(location.getLocation());
            if (!formattedLocation.equals("")) {
                getChildren().add(new HBox(new Label(formattedLocation)));
            }
        }
        if (getChildren().isEmpty()) {
            getChildren().add(new HBox(new Label("This Pokemon can not be captured in the wild in the Sinnoh region")));
        }
    }

    public void setMoves() throws IOException {
        InputStream inputDataForLearnedMoves = copyData();
        MoveBuilder moveBuilderForLearnedMoves = new MoveBuilder(inputDataForLearnedMoves);
        MoveFormatter moveFormatter = new MoveFormatter();
        List<Move> learnedMovesList = moveBuilderForLearnedMoves.buildMoves();
        learnedMovesList = moveFormatter.buildLearnedMoves(learnedMovesList);
        VBox movesBox = new VBox();
        for (Move move : learnedMovesList) {
            HBox moveBox = new HBox(new Label((move.getLevel().toString()), new Label(move.getName())));
            getChildren().add(moveBox);
            moveBox.prefWidthProperty().bind(movesBox.prefWidthProperty());
        }
        getChildren().add(new HBox(new Label("")));
        getChildren().add(new HBox(new Label("Taught Moves:")));
        getChildren().add(new HBox(new Label("")));

        InputStream inputDataForTaughtMoves = copyData();
        MoveBuilder moveBuilderForTaughtMoves = new MoveBuilder(inputDataForTaughtMoves);
        List<Move> taughtMovesList = moveBuilderForTaughtMoves.buildMoves();
        taughtMovesList = moveFormatter.buildTaughtMoves(taughtMovesList);
        for (Move move : taughtMovesList) {
            HBox taughtMovesBox = new HBox(new Label(move.getName()));
            getChildren().add(taughtMovesBox);
        }
    }

    public void setDamageRelations() throws IOException {
        InputStream inputData = copyData();
        getChildren().clear();
        TypeBuilder typeBuilder = new TypeBuilder(inputData);
        List<Type> typeList = typeBuilder.buildTypes();
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(typeList);
        TypeAdvantage typeAdvantage = advantageBuilder.buildTypeAdvantage();

        if (typeAdvantage.getFourTimesEffective().size() > 0){
            Label effectiveness = new Label("Four Times Effective");
            effectiveness.setFont(labelFont);
            getChildren().add(new HBox(effectiveness));
            for (String type: typeAdvantage.getFourTimesEffective()){
                getChildren().add(new HBox(new Label(type)));
            }
        }
        if (typeAdvantage.getTwoTimesEffective().size() > 0){
            Label effectiveness = new Label("Two Times Effective");
            effectiveness.setFont(labelFont);
            getChildren().add(new HBox(effectiveness));
            for (String type: typeAdvantage.getTwoTimesEffective()){
                getChildren().add(new HBox(new Label(type)));
            }
        }
        if (typeAdvantage.getRegularEffective().size() > 0){
            Label effectiveness = new Label("Normally Effective");
            effectiveness.setFont(labelFont);
            getChildren().add(new HBox(effectiveness));
            for (String type: typeAdvantage.getRegularEffective()){
                getChildren().add(new HBox(new Label(type)));
            }
        }
        if (typeAdvantage.getHalfEffective().size() > 0){
            Label effectiveness = new Label("Half Effective");
            effectiveness.setFont(labelFont);
            getChildren().add(new HBox(effectiveness));
            for (String type: typeAdvantage.getRegularEffective()){
                getChildren().add(new HBox(new Label(type)));
            }
        }
        if (typeAdvantage.getNotEffective().size() > 0){
            Label effectiveness = new Label("Not Effective");
            effectiveness.setFont(labelFont);
            getChildren().add(new HBox(effectiveness));
            for (String type: typeAdvantage.getNotEffective()){
                getChildren().add(new HBox(new Label(type)));
            }
        }
    }
    public void setPokemonNotFound() {
        getChildren().clear();
        Label label = new Label("The search is not a valid Pokemon");
        label.setFont(labelFont);
        getChildren().add(new HBox(label));
    }
}

