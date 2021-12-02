package edu.bsu.cs.typeadvantage;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs.query.QuerySearcher;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DamageRelations {
    private final Type type;

    public DamageRelations(Type type) {
        this.type = type;
    }

    public List<Double> formatDamage() throws IOException {
        List<Double> damageRelations = new ArrayList<>(Arrays.asList(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0));
        URL typeUrl = this.type.getTypeUrl();
        InputStream inputData = searchTypeUrl(typeUrl);
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        inputData.transferTo(temporaryByteArray);
        InputStream firstClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        InputStream secondClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        InputStream thirdClone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        List<Double> doubleDamage = doubleDamageFrom(firstClone);
        List<Double> halfDamage = halfDamageFrom(secondClone);
        List<Double> noDamage = noDamageFrom(thirdClone);
        for (int i = 0; i < damageRelations.size(); i++){
            Double newDamage = damageRelations.get(i) * doubleDamage.get(i) * halfDamage.get(i) * noDamage.get(i);
            damageRelations.set(i, newDamage);
        }
        return damageRelations;
    }

    public InputStream searchTypeUrl(URL typeUrl) throws IOException {
        QuerySearcher searcher = new QuerySearcher();
        return searcher.getInputStream(typeUrl.toString());
    }

    public List<Double> doubleDamageFrom(InputStream inputData) throws IOException {
        List<Double> damageRelations = new ArrayList<>(Arrays.asList(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0));
        JsonPath typePath = JsonPath.compile("$..damage_relations..double_damage_from..name");
        JSONArray doubleDamageArray = typePath.read(inputData);
        for(Object type: doubleDamageArray){
            Integer address = formatTypeIndex((String) type);
            damageRelations.set(address - 1, 2.0);
        }
        return damageRelations;
    }

    public List<Double> halfDamageFrom(InputStream inputData) throws IOException {
        List<Double> damageRelations = new ArrayList<>(Arrays.asList(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0));
        JsonPath typePath = JsonPath.compile("$..damage_relations..half_damage_from..name");
        JSONArray halfDamageArray = typePath.read(inputData);
        for(Object type: halfDamageArray){
            Integer address = formatTypeIndex((String) type);
            damageRelations.set(address - 1, 0.5);
        }
        return damageRelations;
    }

    public List<Double> noDamageFrom(InputStream inputData) throws IOException {
        List<Double> damageRelations = new ArrayList<>(Arrays.asList(1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0));
        JsonPath typePath = JsonPath.compile("$..damage_relations..no_damage_from..name");
        JSONArray noDamageArray = typePath.read(inputData);
        for(Object type: noDamageArray){
            Integer address = formatTypeIndex((String) type);
            damageRelations.set(address - 1, 0.0);
        }
        return damageRelations;
    }

    public Integer formatTypeIndex(String type){
        return switch (type) {
            case "normal" -> 1;
            case "fighting" -> 2;
            case "flying" -> 3;
            case "poison" -> 4;
            case "ground" -> 5;
            case "rock" -> 6;
            case "bug" -> 7;
            case "ghost" -> 8;
            case "steel" -> 9;
            case "fire" -> 10;
            case "water" -> 11;
            case "grass" -> 12;
            case "electric" -> 13;
            case "psychic" -> 14;
            case "ice" -> 15;
            case "dragon" -> 16;
            case "dark" -> 17;
            case "fairy" -> 18;
            default -> 0;
        };
    }
}
