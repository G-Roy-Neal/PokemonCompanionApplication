package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class QuerySearcher {
    public InputStream getInputStream(String stringUrl) throws IOException {
        URL Url = new URL(stringUrl);
        URLConnection connection = Url.openConnection();
        return connection.getInputStream();
    }
}
