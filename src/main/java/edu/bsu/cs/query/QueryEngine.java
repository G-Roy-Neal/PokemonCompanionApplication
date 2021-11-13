package edu.bsu.cs.query;

import java.io.IOException;
import java.io.InputStream;

public interface QueryEngine {
    InputStream getInputStream(String search) throws IOException;
}

