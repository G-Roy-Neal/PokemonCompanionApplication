package edu.bsu.cs.query;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class UrlBuilder {
    public String buildSearchUrl(String search) {
        String formattedSearch = formatSearch(search);
        return "https://pokeapi.co/api/v2/pokemon/" + formattedSearch;
    }

    public String buildImageUrl(String pokemonId) {
        String formsttedSearch = formatSearch(pokemonId);
        return "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + formsttedSearch + ".png";
    }

    private String formatSearch(String unformattedSearch) {
        String formattedSearch;
        String lowerCase = unformattedSearch.toLowerCase(Locale.ROOT);
        String noSpace = lowerCase.replace(" ", "-");
        String noChar = noSpace.replace(".", "");
        noChar = noChar.replace("/", "");
        if (noChar.isBlank()) {
            noChar = "foo";
        }
        formattedSearch = URLEncoder.encode(noChar, StandardCharsets.UTF_8);
        return formattedSearch;
    }
}
