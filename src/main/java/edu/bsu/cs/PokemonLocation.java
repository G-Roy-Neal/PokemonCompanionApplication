package edu.bsu.cs;

public class PokemonLocation {

    private String locationDescription;
    private int encounterChance;
    private String requiredAction;

    public PokemonLocation() {}

    public void setLocationDescription(String locationDescription){
        this.locationDescription = locationDescription;
    }
    public void setEncounterChance(int encounterChance){
        this.encounterChance = encounterChance;
    }
    public void setMethod(String requiredAction){
        this.requiredAction = requiredAction;
    }
}
