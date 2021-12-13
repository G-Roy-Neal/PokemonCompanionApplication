package edu.bsu.cs.query;

import java.io.IOException;
import java.io.InputStream;

public class QueryEngine {
    public InputStream getInputStream(String search) throws IOException {
        UrlBuilder builder = new UrlBuilder();
        InputStreamSummoner summoner = new InputStreamSummoner();
        String searchUrl = builder.buildSearchUrl(search.strip());
        return summoner.getInputStream(searchUrl);
    }
}
