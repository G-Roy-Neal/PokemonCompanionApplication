package edu.bsu.cs.basicinfo;

import java.util.List;
import java.util.Objects;

public class BasicInfo {
    private final String name;
    private final int height;
    private final int weight;
    private final List<String> types;

    private BasicInfo(BasicInfo.Builder builder) {
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
        BasicInfo that = (BasicInfo) o;
        return height == that.height && weight == that.weight && Objects.equals(name, that.name) && Objects.equals(types, that.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, weight, types);
    }

    public static final class Builder {
        public String name;
        public int height;
        public int weight;
        public List<String> types;

        public BasicInfo.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public BasicInfo.Builder withHeight(int height) {
            this.height = height;
            return this;
        }

        public BasicInfo.Builder withWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public BasicInfo.Builder withTypes(List<String> types) {
            this.types = types;
            return this;
        }

        public BasicInfo build() {
            return new BasicInfo(this);
        }
    }
}
