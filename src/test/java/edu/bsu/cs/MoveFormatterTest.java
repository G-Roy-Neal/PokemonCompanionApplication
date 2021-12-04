package edu.bsu.cs;

import edu.bsu.cs.moves.Move;
import edu.bsu.cs.moves.MoveBuilder;
import edu.bsu.cs.moves.MoveFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MoveFormatterTest {

    @Test
    public void testBuildMoveHyperbeam() {
        MoveFormatter moveFormatter = new MoveFormatter();
        Move move1 = new Move.Builder().withName("Hyperbeam").withLevel(20).build();
        Assertions.assertEquals("20, Hyperbeam", moveFormatter.format(move1));
    }

    @Test
    public void testBuildMoveWaterCannon() {
        MoveFormatter moveFormatter = new MoveFormatter();
        Move move2 = new Move.Builder().withName("Water Cannon").withLevel(0).build();
        Assertions.assertEquals("Water Cannon", moveFormatter.format(move2));
    }

}
