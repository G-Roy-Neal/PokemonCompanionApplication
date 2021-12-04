package edu.bsu.cs.moves;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MoveBuilder {
    private final String data;

    public MoveBuilder(InputStream inputData) throws IOException {
        this.data = new String(inputData.readAllBytes());
    }

    public List<Move> buildMoves() {
        List<Move> pokemonMoves = new ArrayList<>();
        List <Integer> selectLevels = getSelectLevels();
        List <String> moveNames = getMoveNames();
        int length = Math.min(moveNames.size(), selectLevels.size());
        for (int i = 0; i < length; i++){
            Move newMove = new Move.Builder().withLevel(selectLevels.get(i)).withName(moveNames.get(i)).build();
            pokemonMoves.add(newMove);
        }
        return pokemonMoves;
    }

    public List<Integer> getSelectLevels() {
        List<Integer> selectLevels = new ArrayList<>();
        List<String> rawGenerations = getRawGenerations();
        List<Integer> moveIndexes = getMoveIndices(rawGenerations);
        List<Integer> rawLevels = getRawLevels();
        for (int index : moveIndexes) {
            int selectedLevel = rawLevels.get(index);
            selectLevels.add(selectedLevel);
        }
        return selectLevels;
    }

    public List<Integer> getMoveIndices(List<String> rawGenerations) {
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

    public List<String> getRawGenerations() {
        List<String> rawGenerationList = new ArrayList<>();
        JsonPath generationPath = JsonPath.compile("$..moves..version_group_details..version_group..name");
        JSONArray generationsArray = generationPath.read(data);
        for (Object o : generationsArray) {
            rawGenerationList.add(o.toString());
        }
        return rawGenerationList;
    }

    public List<String> getMoveNames() {
        List<String> movesList = new ArrayList<>();
        JsonPath moveNamePath = JsonPath.compile("$..moves..move..name");
        JSONArray moveArray = moveNamePath.read(data);
        for (Object o : moveArray) {
            movesList.add(o.toString());
        }
        return movesList;
    }

    public List<Integer> getRawLevels() {
        List<Integer> rawLevelLearned = new ArrayList<>();
        JsonPath levelLearnedPath = JsonPath.compile("$..moves..level_learned_at");
        JSONArray rawLevels = levelLearnedPath.read(data);
        for (Object rawLevel : rawLevels) {
            rawLevelLearned.add((Integer) rawLevel);
        }
        return rawLevelLearned;
    }
}
