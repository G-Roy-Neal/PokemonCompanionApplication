package edu.bsu.cs;

public class PokemonLocationFormatter {


     public String transformLocationDescription(String rawLocation) {
         if (checkIfLocationInGame(rawLocation).equals(true)) {
             return formatLocationDescription(rawLocation);
         }
         return null;
    }

    private String formatLocationDescription(String rawLocation) {
         rawLocation = rawLocation.replace('-',' ');
         return rawLocation.replace("sinnoh r", "R");
    }

    private Object checkIfLocationInGame(String rawLocation) {
        return rawLocation.contains("sinnoh");
    }

}
