package edu.bsu.cs.typeadvantage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TypeAdvantageBuilder {

    private final List<Type> typesList;

    public TypeAdvantageBuilder(List<Type> types) {
        this.typesList = types;
    }

    public TypeAdvantage buildTypeAdvantage() throws IOException {
        List<Double> allDamageRelations = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0));
        for (Type type : this.typesList) {
            DamageRelations damageRelations = new DamageRelations(type);
            damageRelations.formatDamage();
            List<Double> typeDamageRelations = damageRelations.getDamageRelations();
            for (int i = 0; i < 18; i++) {
                Double newDamage = allDamageRelations.get(i) * typeDamageRelations.get(i);
                allDamageRelations.set(i, newDamage);
            }
        }
        return setEffectiveness(allDamageRelations);
    }

    public List<String> getFourTimesEffective(List<Double> damageRelations) {
        List<String> fourTimesEffective = new ArrayList<>();
        for (int i = 0; i < damageRelations.size(); i++) {
            if (damageRelations.get(i) == 4.0) {
                fourTimesEffective.add(convertToType(i));
            }
        }
        return fourTimesEffective;
    }

    public List<String> getTwoTimesEffective(List<Double> damageRelations) {
        List<String> twoTimesEffective = new ArrayList<>();
        for (int i = 0; i < damageRelations.size(); i++) {
            if (damageRelations.get(i) == 2.0) {
                twoTimesEffective.add(convertToType(i));
            }
        }
        return twoTimesEffective;
    }

    public List<String> getNormalEffective(List<Double> damageRelations) {
        List<String> normalEffective = new ArrayList<>();
        for (int i = 0; i < damageRelations.size(); i++) {
            if (damageRelations.get(i) == 1.0) {
                normalEffective.add(convertToType(i));
            }
        }
        return normalEffective;
    }

    public List<String> getHalfEffective(List<Double> damageRelations) {
        List<String> halfEffective = new ArrayList<>();
        for (int i = 0; i < damageRelations.size(); i++) {
            if (damageRelations.get(i) == 0.5) {
                halfEffective.add(convertToType(i));
            }
        }
        return halfEffective;
    }

    public List<String> getNotEffective(List<Double> damageRelations) {
        List<String> notEffective = new ArrayList<>();
        for (int i = 0; i < damageRelations.size(); i++) {
            if (damageRelations.get(i) == 0.0) {
                notEffective.add(convertToType(i));
            }
        }
        return notEffective;
    }

    public TypeAdvantage setEffectiveness(List<Double> allDamageRelations) {
        TypeAdvantage typeAdvantage = new TypeAdvantage();
        typeAdvantage.setFourTimesEffective(getFourTimesEffective(allDamageRelations));
        typeAdvantage.setTwoTimesEffective(getTwoTimesEffective(allDamageRelations));
        typeAdvantage.setRegularEffective(getNormalEffective(allDamageRelations));
        typeAdvantage.setHalfEffective(getHalfEffective(allDamageRelations));
        typeAdvantage.setNotEffective(getNotEffective(allDamageRelations));
        return typeAdvantage;
    }

    public String convertToType(int index) {
        HashMap<Integer, String> typeMap = new HashMap<>();
        typeMap.put(0, "Normal");
        typeMap.put(1, "Fighting");
        typeMap.put(2, "Flying");
        typeMap.put(3, "Poison");
        typeMap.put(4, "Ground");
        typeMap.put(5, "Rock");
        typeMap.put(6, "Bug");
        typeMap.put(7, "Ghost");
        typeMap.put(8, "Steel");
        typeMap.put(9, "Fire");
        typeMap.put(10, "Water");
        typeMap.put(11, "Grass");
        typeMap.put(12, "Electric");
        typeMap.put(13, "Psychic");
        typeMap.put(14, "Ice");
        typeMap.put(15, "Dragon");
        typeMap.put(16, "Dark");
        typeMap.put(17, "Fairy");
        return typeMap.get(index);
    }
}
