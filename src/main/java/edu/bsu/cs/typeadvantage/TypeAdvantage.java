package edu.bsu.cs.typeadvantage;

import java.util.List;

public class TypeAdvantage {
    private List<String> fourTimesEffective;
    private List<String> twoTimesEffective;
    private List<String> regularEffective;
    private List<String> halfEffective;
    private List<String> notEffective;

    public List<String> getFourTimesEffective() {
        return fourTimesEffective;
    }

    public List<String> getTwoTimesEffective() {
        return twoTimesEffective;
    }

    public List<String> getRegularEffective() {
        return regularEffective;
    }

    public List<String> getHalfEffective() {
        return halfEffective;
    }

    public List<String> getNotEffective() {
        return notEffective;
    }

    public void setFourTimesEffective(List<String> fourTimesEffective) {
        this.fourTimesEffective = fourTimesEffective;
    }

    public void setTwoTimesEffective(List<String> twoTimesEffective) {
        this.twoTimesEffective = twoTimesEffective;
    }

    public void setRegularEffective(List<String> regularEffective) {
        this.regularEffective = regularEffective;
    }

    public void setHalfEffective(List<String> halfEffective) {
        this.halfEffective = halfEffective;
    }

    public void setNotEffective(List<String> notEffective) {
        this.notEffective = notEffective;
    }
}
