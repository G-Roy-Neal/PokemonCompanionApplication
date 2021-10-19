package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlBuilderTest {

    @Test
        public void testBuildUrl() {
        UrlBuilder builder = new UrlBuilder();
        String result = builder.buildSearchUrl("Ralts");
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/ralts", result);
    }
}
