package edu.bsu.cs;

import java.util.List;
import java.util.Locale;

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
         String replacedLocation =  rawLocation.replace("sinnoh ", "");
         String formattedLocation = replacedLocation.substring(0, 1).toUpperCase() + replacedLocation.substring(1);
         return formattedLocation;
    }

    private static Object checkIfLocationInGame(String rawLocation) {
        return rawLocation.contains("sinnoh");
    }
}
