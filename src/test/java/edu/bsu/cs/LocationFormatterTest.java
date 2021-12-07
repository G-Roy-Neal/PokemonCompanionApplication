package edu.bsu.cs;

import edu.bsu.cs.locations.LocationFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
}
