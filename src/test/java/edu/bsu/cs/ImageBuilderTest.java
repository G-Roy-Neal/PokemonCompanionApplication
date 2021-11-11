package edu.bsu.cs;

import edu.bsu.cs.PokemonImage.ImageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageBuilderTest {
    @Test
    public void testGetPokemonId() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        ImageBuilder imageBuilder = new ImageBuilder();
        Assertions.assertEquals("280", imageBuilder.getPokemonId(testingData));
    }

    @Test
    public void testGetPokemonImage() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("Squirtle.png");
        ByteArrayOutputStream testingByteArray = new ByteArrayOutputStream();

        ImageBuilder imageBuilder = new ImageBuilder();
        ByteArrayOutputStream imageByteArray = new ByteArrayOutputStream();

        assert testingData != null;
        Assertions.assertEquals(testingData.transferTo(testingByteArray), imageBuilder.getPokemonImage("007").transferTo(imageByteArray));
    }
}
