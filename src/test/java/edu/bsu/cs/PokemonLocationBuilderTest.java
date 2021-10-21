package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class PokemonLocationBuilderTest {

    @Test
    public void TestGetUrl () throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        PokemonLocationBuilder locationBuilder = new PokemonLocationBuilder();
        String locationUrl = locationBuilder.getUrl(testingData);
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/280/encounters", locationUrl);
    }
}
