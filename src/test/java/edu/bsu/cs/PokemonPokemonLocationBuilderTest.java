package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PokemonPokemonLocationBuilderTest {

    @Test
    public void TestGetUrl () throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        PokemonLocationBuilder locationBuilder = new PokemonLocationBuilder(testingData);
        String locationUrl = locationBuilder.getUrl();
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/280/encounters", locationUrl);
    }

    @Test
    public void TestBuildLocationList () throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        PokemonLocation knownLocation = new PokemonLocation("sinnoh-route-209-area");
        PokemonLocationBuilder locationBuilder = new PokemonLocationBuilder(testingData);
        List<PokemonLocation> locations = locationBuilder.buildLocationList();
        Assertions.assertEquals(knownLocation, locations.get(3));
    }
}
