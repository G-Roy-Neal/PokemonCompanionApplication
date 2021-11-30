package edu.bsu.cs;

import edu.bsu.cs.typeadvantage.TypeBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeBuilderTest {

    @Test
    public void testGetUrl() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        TypeBuilder typeBuilder = new TypeBuilder(testingData);
        List<URL> expected = typeBuilder.getUrl();
        URL urlOne = new URL("https://pokeapi.co/api/v2/type/14/");
        URL urlTwo = new URL("https://pokeapi.co/api/v2/type/18/");
        List<URL> actual = new ArrayList<>(Arrays.asList(urlOne, urlTwo));
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testGetType() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        TypeBuilder typeBuilder = new TypeBuilder(testingData);
        List<String> expected = typeBuilder.getType();
        List<String> actual = new ArrayList<>(Arrays.asList("psychic", "fairy"));
        Assertions.assertEquals(actual, expected);
    }
}
