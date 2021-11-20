package edu.bsu.cs;

import edu.bsu.cs.BasicInfo.BasicInfo;
import edu.bsu.cs.BasicInfo.BasicInfoBuilder;
import edu.bsu.cs.BasicInfo.BasicInfoFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BasicInfoFormatterTest {
    @Test
    public void testFormatName() throws IOException {
        BasicInfo basicInfo = initilizeRaltsData();
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(basicInfo);
        String formattedName = infoFormatter.formatName();
        Assertions.assertEquals("Ralts", formattedName);
    }

    @Test
    public void testFormatTypesForRalts() throws IOException {
        BasicInfo basicInfo = initilizeRaltsData();
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(basicInfo);
        String formattedTypes = infoFormatter.formatTypes();
        Assertions.assertEquals("Psychic, Fairy", formattedTypes);
    }

    @Test
    public void testFormatTypesForCharmander() throws IOException {
        BasicInfo basicInfo = initilizeCharmanderData();
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(basicInfo);
        String formattedTypes = infoFormatter.formatTypes();
        Assertions.assertEquals("Fire", formattedTypes);
    }

    @Test
    public void testFormatHeight() throws IOException {
        BasicInfo basicInfo = initilizeRaltsData();
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(basicInfo);
        String formattedHeight = infoFormatter.formatHeight();
        Assertions.assertEquals("0.4 m", formattedHeight);
    }

    @Test
    public void testFormatWeight() throws IOException {
        BasicInfo basicInfo = initilizeRaltsData();
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(basicInfo);
        String formattedWeight = infoFormatter.formatWeight();
        Assertions.assertEquals("6.6 Kg", formattedWeight);
    }

    @Test
    public void testFormatBasicInfo() throws IOException {
        BasicInfo basicInfo = initilizeRaltsData();
        BasicInfoFormatter infoFormatter = new BasicInfoFormatter(basicInfo);
        List<String> formattedInfo = infoFormatter.formatInfo();
        List<String> expectedList = new ArrayList<>();
        expectedList.add("Ralts");
        expectedList.add("Psychic, Fairy");
        expectedList.add("0.4 m");
        expectedList.add("6.6 Kg");
        Assertions.assertEquals(expectedList, formattedInfo);
    }

    public BasicInfo initilizeRaltsData() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        BasicInfoBuilder infoBuilder = new BasicInfoBuilder(testingData);
        return infoBuilder.buildBasicInfo();
    }

    public BasicInfo initilizeCharmanderData() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("charmander-test.json");
        assert testingData != null;
        BasicInfoBuilder infoBuilder = new BasicInfoBuilder(testingData);
        return infoBuilder.buildBasicInfo();
    }
}
