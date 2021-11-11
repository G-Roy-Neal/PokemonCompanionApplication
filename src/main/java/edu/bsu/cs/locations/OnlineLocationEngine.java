package edu.bsu.cs.locations;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OnlineLocationEngine implements LocationEngine {
    @Override
    public String getLocations(InputStream inputData) throws IOException {
        String result;
        PokemonLocationFormatter formatter = new PokemonLocationFormatter();
        PokemonLocationBuilder pokemonLocationBuilder = new PokemonLocationBuilder(inputData);
        List<PokemonLocation> locationsList = pokemonLocationBuilder.buildLocationList();
        result = formatter.formatLocationList(locationsList);
        return result;
    }
}
