package edu.bsu.cs.moves;

import java.util.Objects;

public class Move {

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

        public Move build(){
            return new Move(this);
        }
    }

    private final String name;
    private final Integer level;

    private Move(Builder builder){
        this.name = builder.name;
        this.level = builder.level;
    }

    public String getName() {return name;}
    public Integer getLevel() {return level;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move that = (Move) o;
        return Objects.equals(name, that.name) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }
}
