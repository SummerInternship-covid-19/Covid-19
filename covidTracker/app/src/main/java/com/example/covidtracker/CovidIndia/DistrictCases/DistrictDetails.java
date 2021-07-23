package com.example.covidtracker.CovidIndia.DistrictCases;

public class DistrictDetails {


    private String districtName;

    public DistrictDetails(String districtName, String districtCase) {
        this.districtName = districtName;
        this.districtCase = districtCase;
    }



    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCase() {
        return districtCase;
    }

    public void setDistrictCase(String districtCase) {
        this.districtCase = districtCase;
    }

    private String districtCase;


}
