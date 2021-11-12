package edu.bsu.cs.BaisicInfo;

import java.util.List;
import java.util.Objects;

public class BaisicInfo {
    public static final class Builder{
        public String name;
        public int height;
        public int weight;
        public List<String> types;

        public BaisicInfo.Builder withName(String name){
            this.name = name;
            return this;
        }
        public BaisicInfo.Builder withHeight(int height){
            this.height = height;
            return this;
        }
        public BaisicInfo.Builder withWeight(int weight){
            this.weight = weight;
            return this;
        }
        public BaisicInfo.Builder withTypes(List<String> types){
            this.types = types;
            return this;
        }

        public BaisicInfo build(){
            return new BaisicInfo(this);
        }
    }

    private final String name;
    private final int height;
    private final int weight;
    private final List<String> types;

    private BaisicInfo(BaisicInfo.Builder builder){
        this.name = builder.name;
        this.height = builder.height;
        this.weight = builder.weight;
        this.types = builder.types;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getTypes() {
        return types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaisicInfo that = (BaisicInfo) o;
        return height == that.height && weight == that.weight && Objects.equals(name, that.name) && Objects.equals(types, that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight, types);
    }
}
