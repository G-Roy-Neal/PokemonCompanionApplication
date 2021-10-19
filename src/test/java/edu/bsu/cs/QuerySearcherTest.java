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
        ByteArrayOutputStream outputStream = querySearcher.JsonFromUrl("https://pokeapi.co/api/v2/pokemon/ralts");

        Assertions.assertEquals(tempByteArray.toString(), outputStream.toString());
    }
}
