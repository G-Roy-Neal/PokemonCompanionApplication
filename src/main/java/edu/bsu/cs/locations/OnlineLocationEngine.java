package edu.bsu.cs.locations;

import edu.bsu.cs.QuerySearcher;
import edu.bsu.cs.UrlBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OnlineLocationEngine implements LocationEngine {
    @Override
    public String getLocations(String search){
        String result;
        UrlBuilder urlBuilder = new UrlBuilder();
        QuerySearcher querySearcher = new QuerySearcher();
        PokemonLocationFormatter formatter = new PokemonLocationFormatter();
        String formattedURL = urlBuilder.buildSearchUrl(search);
        try {
            InputStream rawData = querySearcher.getInputStream(formattedURL);
            PokemonLocationBuilder pokemonLocationBuilder = new PokemonLocationBuilder(rawData);
            List<PokemonLocation> locationsList = pokemonLocationBuilder.buildLocationList();
            result = formatter.formatLocationList(locationsList);
        } catch (IOException e) {
            result = "Search is not a valid Pokemon";
        }
        return result;
    }
}
