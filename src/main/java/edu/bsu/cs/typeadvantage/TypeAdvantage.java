package edu.bsu.cs.typeadvantage;

import java.util.List;

public class TypeAdvantage {
    private List<String> fourTimesEffective;
    private List<String> twoTimesEffective;
    private List<String> regularEffective;
    private List<String> halfEffective;
    private List<String> notEffective;

    public List<String> getFourTimesEffective() {
        return List.copyOf(this.fourTimesEffective);
    }

    public void setFourTimesEffective(List<String> fourTimesEffective) {
        this.fourTimesEffective = List.copyOf(fourTimesEffective);
    }

    public List<String> getTwoTimesEffective() {
        return List.copyOf(this.twoTimesEffective);
    }

    public void setTwoTimesEffective(List<String> twoTimesEffective) {
        this.twoTimesEffective = List.copyOf(twoTimesEffective);
    }

    public List<String> getRegularEffective() {
        return List.copyOf(this.regularEffective);
    }

    public void setRegularEffective(List<String> regularEffective) {
        this.regularEffective = List.copyOf(regularEffective);
    }

    public List<String> getHalfEffective() {
        return List.copyOf(this.halfEffective);
    }

    public void setHalfEffective(List<String> halfEffective) {
        this.halfEffective = List.copyOf(halfEffective);
    }

    public List<String> getNotEffective() {
        return List.copyOf(this.notEffective);
    }

    public void setNotEffective(List<String> notEffective) {
        this.notEffective = List.copyOf(notEffective);
    }
}
