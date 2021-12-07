package edu.bsu.cs.moves;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MoveEngine {
    private final Font labelFont = Font.font("Verdana", FontWeight.BOLD, 16);
    private final Font dataFont = Font.font("Verdana", 16);

    public List<HBox> generateMoveList(InputStream inputData) throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        ByteArrayInputStream inputDataForLearnedMoves = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        ByteArrayInputStream inputDataForTaughtMoves = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        List<HBox> moveList = new ArrayList<>();
        Label learned = new Label("Moves That Can Be Learned:");
        learned.setFont(labelFont);
        moveList.add(new HBox(learned));
        MoveBuilder moveBuilderForLearnedMoves = new MoveBuilder(inputDataForLearnedMoves);
        MoveFormatter moveFormatter = new MoveFormatter();
        List<Move> learnedMovesList = moveBuilderForLearnedMoves.buildMoves();
        learnedMovesList = moveFormatter.buildLearnedMoves(learnedMovesList);
        VBox movesBox = new VBox();
        for (Move move : learnedMovesList) {
            Label name = new Label(move.getName());
            Label level = new Label(move.getLevel().toString());
            Label levelWord = new Label("Level ");
            Label colon = new Label(": ");
            name.setFont(dataFont);
            level.setFont(labelFont);
            levelWord.setFont(labelFont);
            colon.setFont(labelFont);
            HBox moveBox = new HBox(levelWord, level, colon, name);
            moveList.add(moveBox);
            moveBox.prefWidthProperty().bind(movesBox.prefWidthProperty());
        }

        moveList.add(new HBox(new Label("")));
        Label taught = new Label("Moves That Can Be Taught:");
        taught.setFont(labelFont);
        moveList.add(new HBox(taught));
        MoveBuilder moveBuilderForTaughtMoves = new MoveBuilder(inputDataForTaughtMoves);
        List<Move> taughtMovesList = moveBuilderForTaughtMoves.buildMoves();
        taughtMovesList = moveFormatter.buildTaughtMoves(taughtMovesList);
        for (Move move : taughtMovesList) {
            Label data = new Label(move.getName());
            data.setFont(dataFont);
            HBox taughtMovesBox = new HBox(data);
            moveList.add(taughtMovesBox);
        }
        return moveList;
    }
}
