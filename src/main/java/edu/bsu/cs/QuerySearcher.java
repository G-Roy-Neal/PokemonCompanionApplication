package edu.bsu.cs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class QuerySearcher {
    public ByteArrayOutputStream JsonFromUrl (String stringUrl) throws IOException {
        URL Url = new URL(stringUrl);
        URLConnection connection = Url.openConnection();
        ByteArrayOutputStream tempByteArray = new ByteArrayOutputStream();
        connection.getInputStream().transferTo(tempByteArray);
        return tempByteArray;
    }
}
