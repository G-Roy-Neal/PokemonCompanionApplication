package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PokemonLocationFormatterTest {


    @ParameterizedTest
    @CsvSource({
            "sinnoh-route-203-area, Route 203 area",
            "sinnoh-route-204-south-towards-jubilife-city, Route 204 south towards jubilife city"
    })
    public void testFormatLocation(String location, String expected) {
        PokemonLocationFormatter formatter = new PokemonLocationFormatter();
        String result = formatter.transformLocationDescription(location);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testBuildFormattedOutput() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        PokemonLocationBuilder locationBuilder = new PokemonLocationBuilder(testingData);
        List<PokemonLocation> locationsList = locationBuilder.buildLocationList();
        String formattedOutput = PokemonLocationFormatter.formatLocationList(locationsList);
        Assertions.assertEquals("""
                Route 203 area
                Route 204 south towards jubilife city
                Route 208 area
                Route 209 area
                Route 212 north towards hearthome city
                """, formattedOutput);
    }
}
