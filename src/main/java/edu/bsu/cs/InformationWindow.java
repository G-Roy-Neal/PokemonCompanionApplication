package edu.bsu.cs;

import edu.bsu.cs.locations.Location;
import edu.bsu.cs.locations.LocationBuilder;
import edu.bsu.cs.locations.LocationFormatter;
import edu.bsu.cs.moves.Move;
import edu.bsu.cs.moves.MoveBuilder;
import edu.bsu.cs.moves.MoveFormatter;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class InformationWindow extends VBox {


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
        InputStream inputData = copyData();
        MoveBuilder moveBuilder = new MoveBuilder(inputData);
        MoveFormatter moveFormatter = new MoveFormatter();
        List<Move> pokemonMoves = moveBuilder.buildMoves();
        pokemonMoves = moveFormatter.buildLearnedMoves(pokemonMoves);
        VBox movesBox = new VBox();
        for (Move move : pokemonMoves) {
            HBox moveBox = new HBox(new Label((move.getLevel().toString()), new Label(move.getName())));
            getChildren().add(moveBox);
            moveBox.prefWidthProperty().bind(movesBox.prefWidthProperty());
        }
    }
}

