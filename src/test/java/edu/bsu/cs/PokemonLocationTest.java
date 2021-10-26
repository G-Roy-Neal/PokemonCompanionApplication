package edu.bsu.cs;

import edu.bsu.locations.PokemonLocation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokemonLocationTest {

    @Test
    public void testPokemonLocation(){
        String location = "sinnoh route 205 area";
        PokemonLocation location1 = new PokemonLocation(location);
        Assertions.assertEquals(location1.getLocation(), location);
    }
}
