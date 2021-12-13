package edu.bsu.cs;

import edu.bsu.cs.typeadvantage.Type;
import edu.bsu.cs.typeadvantage.TypeAdvantageBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeAdvantageBuilderTest {

    @Test
    public void testGetFourTimesEffective() throws IOException {
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(buildTypesList());
        List<String> actual = advantageBuilder.getFourTimesEffective(buildDamageRelations());
        List<String> expected = new ArrayList<>(Arrays.asList("Fighting", "Electric"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testGetTwoTimesEffective() throws IOException {
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(buildTypesList());
        List<String> actual = advantageBuilder.getTwoTimesEffective(buildDamageRelations());
        List<String> expected = new ArrayList<>(Arrays.asList("Normal", "Ghost", "Steel", "Psychic"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNormalEffective() throws IOException {
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(buildTypesList());
        List<String> actual = advantageBuilder.getNormalEffective(buildDamageRelations());
        List<String> expected = new ArrayList<>(Arrays.asList("Flying", "Fire", "Water", "Grass", "Ice", "Fairy"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testHalfEffective() throws IOException {
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(buildTypesList());
        List<String> actual = advantageBuilder.getHalfEffective(buildDamageRelations());
        List<String> expected = new ArrayList<>(Arrays.asList("Poison", "Rock", "Dragon"));
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testNotEffective() throws IOException {
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(buildTypesList());
        List<String> actual = advantageBuilder.getNotEffective(buildDamageRelations());
        List<String> expected = new ArrayList<>(Arrays.asList("Ground", "Bug", "Dark"));
        Assertions.assertEquals(expected, actual);
    }

    public List<Type> buildTypesList() throws IOException {
        Type fairy = new Type.Builder().withType("fairy").withURL(new URL("https://pokeapi.co/api/v2/type/18/")).build();
        Type psychic = new Type.Builder().withType("psychic").withURL(new URL("https://pokeapi.co/api/v2/type/14/")).build();
        return new ArrayList<>(Arrays.asList(fairy, psychic));
    }

    public List<Double> buildDamageRelations() {
        return new ArrayList<>(Arrays.asList(2.0, 4.0, 1.0, 0.5, 0.0, 0.5, 0.0, 2.0, 2.0, 1.0, 1.0, 1.0, 4.0, 2.0, 1.0, 0.5, 0.0, 1.0));
    }
}
