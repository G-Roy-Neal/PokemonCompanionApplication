package edu.bsu.cs;

import edu.bsu.cs.CheckGeneration;
import edu.bsu.cs.QuerySearcher;
import edu.bsu.cs.UrlBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CheckGenerationTest {
    @Test
    public void testCheckGeneration () throws IOException {
        CheckGeneration checkGeneration = new CheckGeneration();
        QuerySearcher querySearcher = new QuerySearcher();

        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        ArrayList<String> urlList = JsonPath.read(testingData, "$..moves..move..url");

        Assertions.assertFalse(checkGeneration.checkGeneration(querySearcher.DataFromUrl(urlList.get(4))));
        Assertions.assertTrue(checkGeneration.checkGeneration(querySearcher.DataFromUrl(urlList.get(90))));
    }

    @Test
    public void raltsGenSixMoves () throws IOException {
        CheckGeneration checkGeneration = new CheckGeneration();
        QuerySearcher querySearcher = new QuerySearcher();
        UrlBuilder urlBuilder = new UrlBuilder();

        ArrayList<String> urlList = JsonPath.read(querySearcher.DataFromUrl(urlBuilder.buildSearchUrl("ralts")).toString(), "$..moves..move..url");
        ArrayList<String> allMoves = JsonPath.read(querySearcher.DataFromUrl(urlBuilder.buildSearchUrl("ralts")).toString(), "$..moves..move..name");
        ArrayList<String> gen6Moves = new ArrayList<>();

        for (int i = 0; i<urlList.size(); i++) {
            if (checkGeneration.checkGeneration(querySearcher.DataFromUrl(urlList.get(i)))) {
                gen6Moves.add(allMoves.get(i));
            }
        }

        System.out.println(gen6Moves);
    }
}
