package edu.bsu.cs.moves;

public class PokemonMove {

    public static final class Builder{
        public String name;
        public Integer level;

        public Builder withName(String name){
            this.name = name;
            return this;
        }
        public Builder withLevel(Integer level){
            this.level = level;
            return this;
        }

        public PokemonMove build(){
            return new PokemonMove(this);
        }
    }

    private final String name;
    private final Integer level;

    private PokemonMove(Builder builder){
        this.name = builder.name;
        this.level = builder.level;
    }

    public String getName() {return name;}
    public Integer getLevel() {return level;}
}
