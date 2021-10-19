package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class QuerySearcherTest {
    @Test
    public void querySearcherTest() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        ByteArrayOutputStream tempByteArray = new ByteArrayOutputStream();
        testingData.transferTo(tempByteArray);

        QuerySearcher querySearcher = new QuerySearcher();
        ByteArrayOutputStream outputStream = querySearcher.DataFromUrl("https://pokeapi.co/api/v2/pokemon/ralts");

        Assertions.assertEquals(tempByteArray.toString(), outputStream.toString());
    }

    @Test
    public void imageSearcherTest() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("Squirtle.png");
        assert testingData != null;
        ByteArrayOutputStream tempByteArray = new ByteArrayOutputStream();
        testingData.transferTo(tempByteArray);

        QuerySearcher querySearcher = new QuerySearcher();
        ByteArrayOutputStream outputStream = querySearcher.DataFromUrl("https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png");

        Assertions.assertEquals(tempByteArray.toString(), outputStream.toString());
    }
}
