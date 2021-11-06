package edu.bsu.cs;

import edu.bsu.cs.moves.MoveFormatter;
import edu.bsu.cs.moves.PokemonMove;
import edu.bsu.cs.moves.PokemonMoveBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MoveFormatterTest {

    @Test
    public void testBuildMoveHyperbeam(){
        MoveFormatter moveFormatter = new MoveFormatter();
        PokemonMove move1 = new PokemonMove.Builder().withName("Hyperbeam").withLevel(20).build();
        Assertions.assertEquals("20, Hyperbeam", moveFormatter.format(move1));
    }
    @Test
    public void testBuildMoveWaterCannon(){
        MoveFormatter moveFormatter = new MoveFormatter();
        PokemonMove move1 = new PokemonMove.Builder().withName("Water Cannon").withLevel(0).build();
        Assertions.assertEquals("Water Cannon", moveFormatter.format(move1));
    }


    @Test
    public void testBuildFormattedOutput() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(testingData);
        String expectedMoves = """
                This Pokemon will learn at level
                20, Hyperbeam
                This pokemon has no moves it can be taught.
                """;
    }
}
