package edu.bsu.cs;

import edu.bsu.cs.basicinfo.BasicInfo;
import edu.bsu.cs.basicinfo.BasicInfoReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BasicInfoReaderTest {
    InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");

    @Test
    public void testReadName() throws IOException {
        BasicInfoReader infoBuilder = new BasicInfoReader(testingData);
        Assertions.assertEquals("ralts", infoBuilder.readName());
    }

    @Test
    public void testReadTypes() throws IOException {
        BasicInfoReader infoBuilder = new BasicInfoReader(testingData);
        List<String> expected = new ArrayList<>();
        expected.add("psychic");
        expected.add("fairy");
        Assertions.assertEquals(expected, infoBuilder.readTypes());
    }

    @Test
    public void testReadHeight() throws IOException {
        BasicInfoReader infoBuilder = new BasicInfoReader(testingData);
        Assertions.assertEquals(4, infoBuilder.readHeight());
    }

    @Test
    public void testReadWeight() throws IOException {
        BasicInfoReader infoBuilder = new BasicInfoReader(testingData);
        Assertions.assertEquals(66, infoBuilder.readWeight());
    }

    @Test
    public void testBuildBasicInfo() throws IOException {
        BasicInfoReader infoBuilder = new BasicInfoReader(testingData);
        List<String> types = new ArrayList<>();
        types.add("psychic");
        types.add("fairy");
        BasicInfo expected = new BasicInfo.Builder().withName("ralts").withHeight(4).withWeight(66).withTypes(types).build();
        Assertions.assertEquals(expected, infoBuilder.buildBasicInfo());
    }
}
