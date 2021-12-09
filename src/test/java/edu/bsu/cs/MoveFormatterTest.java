package edu.bsu.cs;

import edu.bsu.cs.moves.Move;
import edu.bsu.cs.moves.MoveBuilder;
import edu.bsu.cs.moves.MoveFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MoveFormatterTest {
    @Test
    public void testBuildLearnedMoves() throws IOException {
        List<Move> moves = buildMoveList();
        MoveFormatter moveFormatter = new MoveFormatter();
        moveFormatter.separateMoves(moves);
        moveFormatter.formatLearnedMoves();
        List<Move> learnedMoves = moveFormatter.getFormattedLearnedMoves();
        Move expectedMove = new Move.Builder().withLevel(7).withName("growl").build();
        Assertions.assertEquals(expectedMove, learnedMoves.get(2));
    }

    @Test
    public void testBuildTaughtMoves() throws IOException {
        List<Move> moves = buildMoveList();
        MoveFormatter moveFormatter = new MoveFormatter();
        moveFormatter.separateMoves(moves);
        List<Move> taughtMoves = moveFormatter.getFormattedTaughtMoves();
        Move expectedMove = new Move.Builder().withLevel(0).withName("headbutt").build();
        Assertions.assertEquals(expectedMove, taughtMoves.get(6));
    }


    public List<Move> buildMoveList() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        assert testingData != null;
        MoveBuilder moveBuilder = new MoveBuilder(testingData);
        return moveBuilder.buildMoves();
    }
}
