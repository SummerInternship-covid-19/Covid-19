package com.example.covidtracker.CovidIndia.helplineindiaState;

public class HelplineDetails {


    private String no;
    private String stateName;

    HelplineDetails(String no, String stateName) {
        this.no = no;
        this.stateName = stateName;
    }

    String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
