package edu.bsu.cs.image;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs.query.InputStreamSummoner;
import edu.bsu.cs.query.UrlBuilder;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageBuilder {
    public String getPokemonId(InputStream inputData) throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        JsonPath idPath = JsonPath.compile("$.id");
        StringBuilder id = new StringBuilder(idPath.read(new ByteArrayInputStream(temporaryByteArray.toByteArray())).toString());
        while (id.length() < 3) {
            id.insert(0, "0");
        }
        return id.toString();
    }

    public Image getPokemonImage(String pokemonId) throws IOException {
        UrlBuilder urlBuilder = new UrlBuilder();
        InputStreamSummoner inputStreamSummoner = new InputStreamSummoner();
        return new Image(inputStreamSummoner.getInputStream(urlBuilder.buildImageUrl(pokemonId)));
    }
}