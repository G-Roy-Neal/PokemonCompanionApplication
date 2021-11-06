package edu.bsu.cs.moves;

import java.io.IOException;
import java.io.InputStream;

public interface MoveEngine {
    String getMoves(InputStream inputData) throws IOException;
}
