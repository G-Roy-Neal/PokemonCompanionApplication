package edu.bsu.cs.moves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoveFormatter {
    private final List<Move> formattedLearnedMoves;
    private final List<Move> formattedTaughtMoves;
    private final List<Move> sortedMoves;

    public MoveFormatter() {
        this.formattedTaughtMoves = new ArrayList<>();
        this.formattedLearnedMoves = new ArrayList<>();
        this.sortedMoves = new ArrayList<>();
    }

    public List<Move> buildLearnedMoves(List<Move> moveList) {
        separateMoves(moveList);
        formatLearnedMoves();
        Collections.sort(formattedLearnedMoves);
        return new ArrayList<>(formattedLearnedMoves);
    }

    public List<Move> buildTaughtMoves() {
        formattedTaughtMoves.sort(Comparator.comparing(Move::getName));
        return new ArrayList<>(formattedTaughtMoves);
    }

    public void separateMoves(List<Move> movesList) {
        for (Move move : movesList) {
            if (move.getLevel() > 0) {
                this.sortedMoves.add(move);
            } else {
                this.formattedTaughtMoves.add(move);
            }
        }
    }

    public void formatLearnedMoves() {
        LevelComparator comparator = new LevelComparator();
        sortedMoves.sort(comparator); // We think the IDE is wrong
        this.formattedLearnedMoves.addAll(sortedMoves);
    }

    public List<Move> getFormattedLearnedMoves() {
        return new ArrayList<>(formattedLearnedMoves);
    }

    public List<Move> getFormattedTaughtMoves() {
        return new ArrayList<>(formattedTaughtMoves);
    }
}
