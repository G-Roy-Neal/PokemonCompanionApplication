package edu.bsu.cs;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class UrlBuilder {
    public String buildSearchUrl(String search) {
        String formattedSearch = URLEncoder.encode(search.toLowerCase(Locale.ROOT), StandardCharsets.UTF_8);
        return "https://pokeapi.co/api/v2/pokemon/" + formattedSearch;
    }
}
