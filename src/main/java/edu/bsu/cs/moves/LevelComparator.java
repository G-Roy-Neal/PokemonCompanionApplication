package edu.bsu.cs.moves;

import java.util.Comparator;

public class LevelComparator implements Comparator<Move> {

    @Override
    public int compare(Move move1, Move move2) {
        return move1.getLevel() - move2.getLevel();
    }
}
