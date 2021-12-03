package edu.bsu.cs.moves;

import java.util.ArrayList;
import java.util.List;

public class MoveFormatter {
    private final List<Move> formattedLearnedMoves;
    private final List<Move> formattedTaughtMoves;
    private final List<Move> sortedMoves;

    public MoveFormatter(){
        this.formattedTaughtMoves = new ArrayList<>();
        this.formattedLearnedMoves = new ArrayList<>();
        this.sortedMoves = new ArrayList<>();
    }

    public List<Move> buildLearnedMoves(List<Move> moveList){
        separateMoves(moveList);
        formatLearnedMoves();
        java.util.Collections.sort(formattedLearnedMoves);
        return formattedLearnedMoves;
    }

    public String buildFormattedMoves(List<Move> moveList){
        StringBuilder formattedString = new StringBuilder("Moves that can be Learned:\n");
        separateMoves(moveList);
        formatLearnedMoves();
        java.util.Collections.sort(formattedTaughtMoves);
        for (Move learned: this.formattedLearnedMoves){
            formattedString.append(learned).append("\n");
        }
        formattedString.append("\nMoves that can be Taught:\n");
        java.util.Collections.sort(formattedTaughtMoves);
        for (Move taughtMove: this.formattedTaughtMoves){
            formattedString.append(taughtMove).append("\n");
        }
        return formattedString.toString();
    }

    private void separateMoves(List<Move> movesList){
        for(Move move: movesList){
            if (move.getLevel() > 0){
                this.sortedMoves.add(move);
            }
            else{
                this.formattedTaughtMoves.add(move);
            }
        }
    }

    private void formatLearnedMoves(){
        LevelComparator comparator = new LevelComparator();
        sortedMoves.sort(comparator); // We think the IDE is wrong
        this.formattedLearnedMoves.addAll(sortedMoves);
    }

    public String format(Move move) {
        String moveName = move.getName().replace('-', ' ');
        String formattedName = moveName.substring(0, 1).toUpperCase() + moveName.substring(1);
        if (move.getLevel() > 0) {
            return move.getLevel() + ", " + formattedName;
        }else {
            return formattedName;
        }
    }
}
