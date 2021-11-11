package edu.bsu.cs.moves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoveFormatter {
    private final List<String> formattedLearnedMoves;
    private final List<String> formattedTaughtMoves;
    private final List<Move> sortedMoves;

    public MoveFormatter(){
        this.formattedTaughtMoves = new ArrayList<>();
        this.formattedLearnedMoves = new ArrayList<>();
        this.sortedMoves = new ArrayList<>();
    }

    public String buildFormattedMoves(List<Move> moveList){
        StringBuilder formattedString = new StringBuilder("Moves that can be Learned:\n");
        seperateMoves(moveList);
        formatLearnedMoves();
        for (String learned: this.formattedLearnedMoves){
            formattedString.append(learned).append("\n");
        }
        formattedString.append("\nMoves that can be Taught:\n");
        for (String taught: this.formattedTaughtMoves){
            formattedString.append(taught).append("\n");
        }
        return formattedString.toString();
    }

    private void seperateMoves(List<Move> movesList){
        for(Move move: movesList){
            if (move.getLevel() > 0){
                this.sortedMoves.add(move);
            }
            else{
                this.formattedTaughtMoves.add(format(move));
            }
        }
    }

    private void formatLearnedMoves(){
        LevelComparator comparator = new LevelComparator();
        sortedMoves.sort(comparator); // We think the IDE is wrong
        for (Move move: sortedMoves){
            this.formattedLearnedMoves.add(format(move));
        }
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
