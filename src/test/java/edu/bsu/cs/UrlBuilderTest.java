package edu.bsu.cs;

import edu.bsu.cs.query.UrlBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UrlBuilderTest {

    @ParameterizedTest
    @CsvSource({
            "Ralts, https://pokeapi.co/api/v2/pokemon/ralts",
            "Mr. Mime, https://pokeapi.co/api/v2/pokemon/mr-mime"
    })
    public void testBuildPokemonUrl(String pokemon, String url) {
        UrlBuilder builder = new UrlBuilder();
        String result = builder.buildSearchUrl(pokemon);
        Assertions.assertEquals(url, result);
    }

    @Test
    public void testBuildImageUrl() {
        UrlBuilder builder = new UrlBuilder();
        String result = builder.buildImageUrl("Ralts");
        Assertions.assertEquals("https://assets.pokemon.com/assets/cms2/img/pokedex/full/ralts.png", result);
    }
}
