
package com.example.covidtracker.CovidIndia.stateListIndia;

public class StateModel {


    private String stateName;
    private String stateData;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateData() {
        return stateData;
    }

    public void setStateData(String stateData) {
        this.stateData = stateData;
    }

    public StateModel(String stateName, String stateData) {
        this.stateName = stateName;
        this.stateData = stateData;
    }
}
