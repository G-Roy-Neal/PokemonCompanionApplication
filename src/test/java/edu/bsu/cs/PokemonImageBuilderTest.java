package edu.bsu.cs;

import edu.bsu.cs.PokemonImage.PokemonImageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PokemonImageBuilderTest {
    @Test
    public void testGetPokemonId() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        PokemonImageBuilder pokemonImageBuilder = new PokemonImageBuilder();
        Assertions.assertEquals("280", pokemonImageBuilder.getPokemonId(testingData));
    }

    @Test
    public void testGetPokemonImage() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("Squirtle.png");
        ByteArrayOutputStream testingByteArray = new ByteArrayOutputStream();

        PokemonImageBuilder pokemonImageBuilder = new PokemonImageBuilder();
        ByteArrayOutputStream imageByteArray = new ByteArrayOutputStream();

        assert testingData != null;
        Assertions.assertEquals(testingData.transferTo(testingByteArray), pokemonImageBuilder.getPokemonImage("007").transferTo(imageByteArray));
    }
}
