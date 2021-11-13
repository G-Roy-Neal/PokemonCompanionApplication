package edu.bsu.cs.query;

import java.io.IOException;
import java.io.InputStream;

public class OnlineQueryEngine implements QueryEngine {
    @Override
    public InputStream getInputStream(String search) throws IOException {
        UrlBuilder builder = new UrlBuilder();
        QuerySearcher searcher = new QuerySearcher();
        String searchUrl = builder.buildSearchUrl(search);
        return searcher.getInputStream(searchUrl);
    }
}
