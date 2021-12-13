package edu.bsu.cs;

import edu.bsu.cs.typeadvantage.DamageRelations;
import edu.bsu.cs.typeadvantage.Type;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamageRelationsTest {

    private final InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("fairy-test.json");

    @Test
    public void testDoubleDamageFrom() throws IOException {
        assert testingData != null;
        Type testType = new Type.Builder().withType("fairy").withURL(new URL("https://pokeapi.co/api/v2/type/18/")).build();
        DamageRelations damageRelations = new DamageRelations(testType);
        List<Double> actual = damageRelations.doubleDamageFrom(testingData);
        List<Double> expected = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testHalfDamageFrom() throws IOException {
        assert testingData != null;
        Type testType = new Type.Builder().withType("fairy").withURL(new URL("https://pokeapi.co/api/v2/type/18/")).build();
        DamageRelations damageRelations = new DamageRelations(testType);
        List<Double> actual = damageRelations.halfDamageFrom(testingData);
        List<Double> expected = new ArrayList<>(Arrays.asList(1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNoDamageFrom() throws IOException {
        assert testingData != null;
        Type testType = new Type.Builder().withType("fairy").withURL(new URL("https://pokeapi.co/api/v2/type/18/")).build();
        DamageRelations damageRelations = new DamageRelations(testType);
        List<Double> actual = damageRelations.noDamageFrom(testingData);
        List<Double> expected = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0));
        Assertions.assertEquals(expected, actual);
    }

}
