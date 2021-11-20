package edu.bsu.cs;

import edu.bsu.cs.locations.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void testPokemonLocation(){
        String location = "sinnoh route 205 area";
        Location location1 = new Location(location);
        Assertions.assertEquals(location1.getLocation(), location);
    }
}