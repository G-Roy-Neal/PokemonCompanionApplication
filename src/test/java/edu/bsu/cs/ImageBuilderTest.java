package edu.bsu.cs;

import edu.bsu.cs.PokemonImage.ImageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
