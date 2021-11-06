package edu.bsu.cs.moves;

import java.util.ArrayList;
import java.util.List;

public class MoveFormatter {
    private final List<PokemonMove> taughtMoves;
    private final List<PokemonMove> movesThatWillBeLearned;

    public MoveFormatter() {
        taughtMoves = new ArrayList<>();
        movesThatWillBeLearned = new ArrayList<>();
    }

    public StringBuilder formatTaughtMoves() {
        StringBuilder taughtMovesString = new StringBuilder("These are the moves it can be taught:\n");
        for (PokemonMove move: taughtMoves){
            String formattedMove = String.format("%s\n", move.getName());
            taughtMovesString.append(formattedMove);
        }
        return taughtMovesString;
    }

    private void classifyMoves(PokemonMove move){
        if (discernIfTaught(move)) {
            taughtMoves.add(move);
        } else {
            movesThatWillBeLearned.add(move);
        }
    }

    private boolean discernIfTaught(PokemonMove move) {
        return move.getLevel() > 0;
    }


}
