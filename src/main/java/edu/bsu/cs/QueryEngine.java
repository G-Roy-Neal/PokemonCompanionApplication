package edu.bsu.cs;

import java.io.IOException;
import java.io.InputStream;

public interface QueryEngine {
    InputStream getInputStream(String search) throws IOException;
}

