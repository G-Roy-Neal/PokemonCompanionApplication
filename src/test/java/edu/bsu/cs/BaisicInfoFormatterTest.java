package edu.bsu.cs;

import edu.bsu.cs.BaisicInfo.BaisicInfo;
import edu.bsu.cs.BaisicInfo.BaisicInfoBuilder;
import edu.bsu.cs.BaisicInfo.BaisicInfoFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaisicInfoFormatterTest {
    @Test
    public void testFormatName() throws IOException {
        BaisicInfo baisicInfo = initilizeData1();
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(baisicInfo);
        String formattedName = infoFormatter.formatName();
        Assertions.assertEquals("Ralts", formattedName);
    }

    @Test
    public void testFormatTypes1() throws IOException {
        BaisicInfo baisicInfo = initilizeData1();
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(baisicInfo);
        String formattedTypes = infoFormatter.formatTypes();
        Assertions.assertEquals("Psychic, Fairy", formattedTypes);
    }

    @Test
    public void testFormatTypes2() throws IOException {
        BaisicInfo baisicInfo = initilizeData2();
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(baisicInfo);
        String formattedTypes = infoFormatter.formatTypes();
        Assertions.assertEquals("Fire", formattedTypes);
    }

    @Test
    public void testFormatHeight() throws IOException {
        BaisicInfo baisicInfo = initilizeData1();
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(baisicInfo);
        String formattedHeight = infoFormatter.formatHeight();
        Assertions.assertEquals("0.4 m", formattedHeight);
    }

    @Test
    public void testFormatWeight() throws IOException {
        BaisicInfo baisicInfo = initilizeData1();
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(baisicInfo);
        String formattedWeight = infoFormatter.formatWeight();
        Assertions.assertEquals("6.6 Kg", formattedWeight);
    }

    @Test
    public void testFormatBaisicInfo() throws IOException {
        BaisicInfo baisicInfo = initilizeData1();
        BaisicInfoFormatter infoFormatter = new BaisicInfoFormatter(baisicInfo);
        List<String> formattedInfo = infoFormatter.formatInfo();
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Ralts");
        expectedList.add("Psychic, Fairy");
        expectedList.add("0.4 m");
        expectedList.add("6.6 Kg");
        Assertions.assertEquals(expectedList, formattedInfo);
    }

    public BaisicInfo initilizeData1() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        BaisicInfoBuilder infoBuilder = new BaisicInfoBuilder(testingData);
        return infoBuilder.buildBaisicInfo();
    }
    public BaisicInfo initilizeData2() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        assert testingData != null;
        BaisicInfoBuilder infoBuilder = new BaisicInfoBuilder(testingData);
        return infoBuilder.buildBaisicInfo();
    }
}
