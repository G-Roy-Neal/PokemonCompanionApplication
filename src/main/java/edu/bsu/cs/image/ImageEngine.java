package edu.bsu.cs.image;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

public interface ImageEngine {
    Image getImage(InputStream inputData) throws IOException;
}
