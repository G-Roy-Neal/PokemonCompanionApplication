package edu.bsu.cs.typeadvantage;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TypeAdvantageEngine {
    private final Font labelFont = Font.loadFont("File:src/main/resources/fonts/november.ttf", 20);
    private final Font dataFont = Font.loadFont("File:src/main/resources/fonts/pokemon_pixel_font.ttf", 24);

    public List<HBox> generateTypeAdvantageList(InputStream inputData) throws IOException {
        List<HBox> damageRelationsList = new ArrayList<>();
        TypeBuilder typeBuilder = new TypeBuilder(inputData);
        List<Type> typeList = typeBuilder.buildTypes();
        TypeAdvantageBuilder advantageBuilder = new TypeAdvantageBuilder(typeList);
        TypeAdvantage typeAdvantage = advantageBuilder.buildTypeAdvantage();
        if (typeAdvantage.getFourTimesEffective().size() > 0){
            Label effectiveness = new Label("Four Times Effective");
            effectiveness.setFont(labelFont);
            damageRelationsList.add(new HBox(effectiveness));
            for (String type: typeAdvantage.getFourTimesEffective()){
                Label typeLabel = new Label(type);
                typeLabel.setFont(dataFont);
                damageRelationsList.add(new HBox(typeLabel));
            }
        }
        if (typeAdvantage.getTwoTimesEffective().size() > 0){
            Label effectiveness = new Label("Two Times Effective");
            effectiveness.setFont(labelFont);
            damageRelationsList.add(new HBox(effectiveness));
            for (String type: typeAdvantage.getTwoTimesEffective()){
                Label typeLabel = new Label(type);
                typeLabel.setFont(dataFont);
                damageRelationsList.add(new HBox(typeLabel));
            }
        }
        if (typeAdvantage.getRegularEffective().size() > 0){
            Label effectiveness = new Label("Normally Effective");
            effectiveness.setFont(labelFont);
            damageRelationsList.add(new HBox(effectiveness));
            for (String type: typeAdvantage.getRegularEffective()){
                Label typeLabel = new Label(type);
                typeLabel.setFont(dataFont);
                damageRelationsList.add(new HBox(typeLabel));
            }
        }
        if (typeAdvantage.getHalfEffective().size() > 0){
            Label effectiveness = new Label("Half Effective");
            effectiveness.setFont(labelFont);
            damageRelationsList.add(new HBox(effectiveness));
            for (String type: typeAdvantage.getRegularEffective()){
                Label typeLabel = new Label(type);
                typeLabel.setFont(dataFont);
                damageRelationsList.add(new HBox(typeLabel));
            }
        }
        if (typeAdvantage.getNotEffective().size() > 0){
            Label effectiveness = new Label("Not Effective");
            effectiveness.setFont(labelFont);
            damageRelationsList.add(new HBox(effectiveness));
            for (String type: typeAdvantage.getNotEffective()){
                Label typeLabel = new Label(type);
                typeLabel.setFont(dataFont);
                damageRelationsList.add(new HBox(typeLabel));
            }
        }
        return damageRelationsList;
    }
}
