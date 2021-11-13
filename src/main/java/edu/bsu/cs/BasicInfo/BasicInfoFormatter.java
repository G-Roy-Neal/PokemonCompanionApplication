package edu.bsu.cs.BasicInfo;

import java.util.ArrayList;
import java.util.List;

public class BasicInfoFormatter {
    private final BasicInfo basicInfo;

    public BasicInfoFormatter(BasicInfo basicInfo){
        this.basicInfo = basicInfo;
    }

    public String formatName() {
        return basicInfo.getName().substring(0, 1).toUpperCase() + basicInfo.getName().substring(1);
    }

    public String formatTypes() {
        List<String> typesList = basicInfo.getTypes();
        StringBuilder formattedTypeString = new StringBuilder();
        String formattedType = typesList.get(0).substring(0, 1).toUpperCase() + typesList.get(0).substring(1);
        if (typesList.size() > 1){
            formattedTypeString.append(formattedType).append(", ");
            formattedType = typesList.get(1).substring(0, 1).toUpperCase() + typesList.get(1).substring(1);
        }
        formattedTypeString.append(formattedType);
        return formattedTypeString.toString();
    }

    public String formatHeight() {
        double adjustedHeight = (double) basicInfo.getHeight() / 10;
        return (adjustedHeight) + " m";
    }

    public String formatWeight() {
        double adjustedWeight = (double) basicInfo.getWeight() / 10;
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
