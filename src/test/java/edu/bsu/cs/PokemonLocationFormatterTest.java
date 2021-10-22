package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PokemonLocationFormatterTest {

    @Test
    public void testPokemonLocationFormatter(){
        PokemonLocationFormatter formatter = new PokemonLocationFormatter();
        String realLocation = "Route 203 area";
        String testLocation = formatter.formatLocation("sinnoh-route-203-area");
        Assertions.assertEquals(realLocation,testLocation);

    }
}
