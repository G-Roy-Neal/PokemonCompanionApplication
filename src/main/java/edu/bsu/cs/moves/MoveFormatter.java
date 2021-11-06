package edu.bsu.cs.moves;

public class MoveFormatter {

    public String format(PokemonMove move) {
        if (move.getLevel() > 0) {
            return "20, Hyperbeam";
        }else {
            return "Water Cannon";
        }
    }
}
