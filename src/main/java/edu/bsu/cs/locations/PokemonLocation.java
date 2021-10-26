package edu.bsu.cs.locations;

import java.util.Objects;

public class PokemonLocation {
    public final String location;

    public PokemonLocation(String location){
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonLocation that = (PokemonLocation) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
