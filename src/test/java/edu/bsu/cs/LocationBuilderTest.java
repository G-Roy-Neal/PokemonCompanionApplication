package edu.bsu.cs;

import edu.bsu.cs.locations.Location;
import edu.bsu.cs.locations.LocationBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LocationBuilderTest {

    @Test
    public void TestGetUrl () throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        LocationBuilder locationBuilder = new LocationBuilder(testingData);
        String locationUrl = locationBuilder.getUrl();
        Assertions.assertEquals("https://pokeapi.co/api/v2/pokemon/280/encounters", locationUrl);
    }

    @Test
    public void TestBuildLocationList () throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        Location knownLocation = new Location("sinnoh-route-209-area");
        LocationBuilder locationBuilder = new LocationBuilder(testingData);
        List<Location> locations = locationBuilder.buildLocationList();
        Assertions.assertEquals(knownLocation, locations.get(3));
    }
}
