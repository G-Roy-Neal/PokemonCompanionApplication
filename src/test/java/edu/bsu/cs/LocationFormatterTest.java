package edu.bsu.cs;

import edu.bsu.cs.locations.Location;
import edu.bsu.cs.locations.LocationBuilder;
import edu.bsu.cs.locations.LocationFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LocationFormatterTest {


    @ParameterizedTest
    @CsvSource({
            "sinnoh-route-203-area, Route 203 area",
            "sinnoh-route-204-south-towards-jubilife-city, Route 204 south towards jubilife city"
    })
    public void testFormatLocation(String location, String expected) {
        LocationFormatter formatter = new LocationFormatter();
        String result = formatter.transformLocationDescription(location);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testBuildFormattedOutput() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        LocationBuilder locationBuilder = new LocationBuilder(testingData);
        List<Location> locationsList = locationBuilder.buildLocationList();
        LocationFormatter formatter = new LocationFormatter();
        String formattedOutput = formatter.formatLocationList(locationsList);
        Assertions.assertEquals("""
                Route 203 area
                Route 204 south towards jubilife city
                Route 208 area
                Route 209 area
                Route 212 north towards hearthome city
                """, formattedOutput);
    }
}
