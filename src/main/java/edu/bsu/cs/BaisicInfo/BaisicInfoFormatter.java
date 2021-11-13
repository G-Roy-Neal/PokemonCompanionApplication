package edu.bsu.cs.BaisicInfo;

import java.util.ArrayList;
import java.util.List;

public class BaisicInfoFormatter {
    private final BaisicInfo baisicInfo;

    public BaisicInfoFormatter(BaisicInfo baisicInfo){
        this.baisicInfo = baisicInfo;
    }

    public String formatName() {
        return baisicInfo.getName().substring(0, 1).toUpperCase() + baisicInfo.getName().substring(1);
    }

    public String formatTypes() {
        List<String> typesList = baisicInfo.getTypes();
        StringBuilder formattedTypeString = new StringBuilder();
        if (typesList.size() > 1){
            String formattedType = typesList.get(0).substring(0, 1).toUpperCase() + typesList.get(0).substring(1);
            formattedTypeString.append(formattedType).append(", ");
            formattedType = typesList.get(1).substring(0, 1).toUpperCase() + typesList.get(1).substring(1);
            formattedTypeString.append(formattedType);
        }
        else{
            String formattedType = typesList.get(0).substring(0, 1).toUpperCase() + typesList.get(0).substring(1);
            formattedTypeString.append(formattedType);
        }
        return formattedTypeString.toString();
    }

    public String formatHeight() {
        double adjustedHeight = (double) baisicInfo.getHeight() / 10;
        return (adjustedHeight) + " m";
    }

    public String formatWeight() {
        double adjustedWeight = (double) baisicInfo.getWeight() / 10;
        return (adjustedWeight) + " Kg";
    }

    public List<String> formatInfo() {
        List<String> formattedInfo = new ArrayList<>();
        formattedInfo.add(formatName());
        formattedInfo.add(formatTypes());
        formattedInfo.add(formatHeight());
        formattedInfo.add(formatWeight());
        return formattedInfo;
    }
}
