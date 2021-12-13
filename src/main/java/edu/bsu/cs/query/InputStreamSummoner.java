package edu.bsu.cs.query;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class InputStreamSummoner {
    public InputStream getInputStream(String stringUrl) throws IOException {
        URL Url = new URL(stringUrl);
        URLConnection connection = Url.openConnection();
        return connection.getInputStream();
    }
}
