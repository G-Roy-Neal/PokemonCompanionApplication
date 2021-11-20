package edu.bsu.cs.image;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

public class OnlineImageEngine implements ImageEngine {

    @Override
    public Image getImage(InputStream inputData) throws IOException {
        ImageBuilder imageBuilder = new ImageBuilder();
        String id = imageBuilder.getPokemonId(inputData);
        return new Image(imageBuilder.getPokemonImage(id));
    }
}
