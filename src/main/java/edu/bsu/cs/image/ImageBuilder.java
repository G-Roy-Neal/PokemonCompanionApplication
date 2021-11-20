package edu.bsu.cs.image;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs.query.QuerySearcher;
import edu.bsu.cs.query.UrlBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageBuilder {
    public String getPokemonId (InputStream inputData) throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        JsonPath idPath = JsonPath.compile("$.id");
        StringBuilder id = new StringBuilder(idPath.read(new ByteArrayInputStream(temporaryByteArray.toByteArray())).toString());
        while (id.length() < 3){
            id.insert(0, "0");
        }
        return id.toString();
    }

    public InputStream getPokemonImage (String pokemonId) throws IOException {
        UrlBuilder urlBuilder = new UrlBuilder();
        QuerySearcher querySearcher = new QuerySearcher();
        return querySearcher.getInputStream(urlBuilder.buildImageUrl(pokemonId));
    }
}