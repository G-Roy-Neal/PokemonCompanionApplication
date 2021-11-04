package edu.bsu.cs.moves;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PokemonMoveBuilder {
    private final InputStream inputData;

    public PokemonMoveBuilder(InputStream inputData) {
        this.inputData = inputData;
    }

    public List<Integer> getSelectLevels() throws IOException {
        List<Integer> selectLevels = new ArrayList<>();
        List<String> rawGenerations = getRawGenerations();
        List<Integer> moveIndexes = getMoveIndexes(rawGenerations);
        List<Integer> rawLevels = getRawLevels();
        for (Integer moveIndex : moveIndexes) {
            selectLevels.add(rawLevels.get(moveIndex));
        }
        return selectLevels;
    }

    public List<Integer> getMoveIndexes(List<String> rawGenerations) {
        List<Integer> generationsNumbers = new ArrayList<>();
        for (int i = 0; i < rawGenerations.size(); i++) {
            if (i == rawGenerations.size() - 1) {
                generationsNumbers.add(i);
            } else {
                Integer currentNum = convertGeneration(rawGenerations.get(i));
                Integer nextNum = convertGeneration(rawGenerations.get(i + 1));
                if (currentNum > nextNum) {
                    generationsNumbers.add(i);
                }
            }
        }
        return generationsNumbers;
    }

    public Integer convertGeneration(String generation) {
        return switch (generation) {
            case "red-blue" -> 1;
            case "yellow" -> 2;
            case "gold-silver" -> 3;
            case "crystal" -> 4;
            case "ruby-sapphire" -> 5;
            case "emerald" -> 6;
            case "firered-leafgreen" -> 7;
            case "diamond-pearl" -> 8;
            case "platinum" -> 9;
            case "heartgold-soulsilver" -> 10;
            case "black-white" -> 11;
            case "colosseum" -> 12;
            case "xd" -> 13;
            case "black-2-white-2" -> 14;
            case "x-y" -> 15;
            case "omega-ruby-alpha-sapphire" -> 16;
            case "sun-moon" -> 17;
            case "ultra-sun-ultra-moon" -> 18;
            default -> 0;
        };
    }

    public List<String> getRawGenerations() throws IOException {
        List<String> rawGenerationList = new ArrayList<>();
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        this.inputData.transferTo(temporaryByteArray);
        InputStream clone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        JsonPath generationPath = JsonPath.compile("$..moves..version_group_details..version_group..name");
        JSONArray generationsArray = generationPath.read(clone);
        for (Object o : generationsArray) {
            rawGenerationList.add(o.toString());
        }
        return rawGenerationList;
    }

    public List<String> getMoveNames() throws IOException {
        List<String> movesList = new ArrayList<>();
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        this.inputData.transferTo(temporaryByteArray);
        InputStream clone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        JsonPath moveNamePath = JsonPath.compile("$..moves..move..name");
        JSONArray moveArray = moveNamePath.read(clone);
        for (Object o : moveArray) {
            movesList.add(o.toString());
        }
        return movesList;
    }

    public List<Integer> getRawLevels() throws IOException {
        List<Integer> rawLevelLearned = new ArrayList<>();
        ByteArrayOutputStream temporaryByteArray = new ByteArrayOutputStream();
        this.inputData.transferTo(temporaryByteArray);
        InputStream clone = new ByteArrayInputStream(temporaryByteArray.toByteArray());
        JsonPath levelLearnedPath = JsonPath.compile("$..moves..level_learned_at");
        JSONArray rawLevels = levelLearnedPath.read(clone);
        for (Object rawLevel : rawLevels) {
            rawLevelLearned.add((Integer) rawLevel);
        }
        return rawLevelLearned;
    }
}
