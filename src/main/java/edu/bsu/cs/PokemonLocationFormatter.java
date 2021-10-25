package edu.bsu.cs;

import java.util.List;

public class PokemonLocationFormatter {


    public static String formatLocationList(List<PokemonLocation> locationsList) {
        StringBuilder locationsString = new StringBuilder();
        for (PokemonLocation location: locationsList){
            String formattedLocation = transformLocationDescription(location.getLocation());
            if (!formattedLocation.equals("")){
                locationsString.append(formattedLocation).append("\n");
            }
        }
        if (locationsString.toString().isBlank()){
            locationsString = new StringBuilder("This Pokemon can not be captured in the wild in the Sinnoh region");
        }
        return locationsString.toString();
    }

    public static String transformLocationDescription(String rawLocation) {
         if (checkIfLocationInGame(rawLocation).equals(true)) {
             return formatLocationDescription(rawLocation);
         }
         return "";
    }

    private static String formatLocationDescription(String rawLocation) {
         rawLocation = rawLocation.replace('-',' ');
         return rawLocation.replace("sinnoh r", "R");
    }

    private static Object checkIfLocationInGame(String rawLocation) {
        return rawLocation.contains("sinnoh");
    }
}
