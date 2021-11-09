package edu.bsu.cs.PokemonImage;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs.QuerySearcher;
import edu.bsu.cs.UrlBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PokemonImageBuilder {
    public String getPokemonId (InputStream inputData) throws IOException {
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        JsonPath idPath = JsonPath.compile("$.id");
        return idPath.read(new ByteArrayInputStream(temporaryByteArray.toByteArray())).toString();
    }

    public InputStream getPokemonImage (String pokemonId) throws IOException {
        UrlBuilder urlBuilder = new UrlBuilder();
        QuerySearcher querySearcher = new QuerySearcher();
        return querySearcher.getInputStream(urlBuilder.buildImageUrl(pokemonId));
    }
}