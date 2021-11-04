package edu.bsu.cs;

import edu.bsu.cs.moves.PokemonMoveBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PokemonMoveBuilderTest {

    @ParameterizedTest
    @CsvSource({
            "ruby-sapphire, 5",
            "firered-leafgreen, 7",
            "heartgold-soulsilver, 10",
            "x-y, 15"
    })
    public void testFormatLocation(String location, Integer expected) {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(testingData);
        Integer result = moveBuilder.convertGeneration(location);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, red-blue",
            "67, omega-ruby-alpha-sapphire",
            "198, platinum",
            "398, gold-silver"
    })
    public void testGetRawGenerations(Integer index, String expected) throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(testingData);
        List<String> generationsList = moveBuilder.getRawGenerations();
        String result = generationsList.get(index);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, mega-punch",
            "29, reflect",
            "35, rock-slide",
            "72, dragon-claw",
            "92, power-up-punch"
    })
    public void testGetMoveNames(Integer index, String expected) throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(testingData);
        List<String> moveList = moveBuilder.getMoveNames();
        String result = moveList.get(index);
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 3",
            "1, 10",
            "10, 86",
            "67, 764",
            "90, 937"
    })
    public void testGetMoveIndexes(Integer index, Integer expected) throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(testingData);
        List<String> rawGenerations = moveBuilder.getRawGenerations();
        List<Integer> moveIndexes = moveBuilder.getMoveIndexes(rawGenerations);
        Integer result = moveIndexes.get(index);
        Assertions.assertEquals(result, expected);
    }
}
