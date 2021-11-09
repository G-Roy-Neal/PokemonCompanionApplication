package edu.bsu.cs.PokemonImage;

import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

public class OnlineImageEngine implements ImageEngine {

    @Override
    public Image getImage(InputStream inputData) throws IOException {
        PokemonImageBuilder imageBuilder = new PokemonImageBuilder();
        String id = imageBuilder.getPokemonId(inputData);
        return new Image(imageBuilder.getPokemonImage(id));
    }
}
