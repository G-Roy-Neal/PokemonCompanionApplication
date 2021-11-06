package edu.bsu.cs.moves;

import java.io.IOException;
import java.io.InputStream;

public class OnlineMoveEngine implements MoveEngine{
    @Override
    public String getMoves(InputStream inputData) throws IOException {
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(inputData);
        MoveFormatter moveFormatter = new MoveFormatter();
        return moveFormatter.buildFormattedMoves(moveBuilder.buildMoves());
    }
}
