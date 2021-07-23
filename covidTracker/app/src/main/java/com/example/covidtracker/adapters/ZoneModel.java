package com.example.covidtracker.adapters;

public class ZoneModel {

    private String zone;
    private String stName;
    private String lastUpdate;
    private String disName;

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public ZoneModel(String zone, String stName,String disName) {
        this.zone = zone;
        this.stName = stName;
        this.disName=disName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
