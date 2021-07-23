
package com.example.covidtracker.models;

public class CovidCountryDetail {


    private String mCovidCountry, mCase, mTodayCases,mDeaths,mTodayDeaths,mRecovered,mCritical;


    public CovidCountryDetail(String mCovidCountry, String mCase) {
        this.mCovidCountry = mCovidCountry;
        this.mCase = mCase;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mCritical = mCritical;
    }

    public CovidCountryDetail(String country) {
        this.mCovidCountry=country;
    }

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public void setmCovidCountry(String mCovidCountry) {
        this.mCovidCountry = mCovidCountry;
    }

    public String getmCase() {
        return mCase;
    }

    public void setmCase(String mCase) {
        this.mCase = mCase;
    }

    public String getmTodayCases() {
        return mTodayCases;
    }

    public void setmTodayCases(String mTodayCases) {
        this.mTodayCases = mTodayCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public void setmDeaths(String mDeaths) {
        this.mDeaths = mDeaths;
    }

    public String getmTodayDeaths() {
        return mTodayDeaths;
    }

    public void setmTodayDeaths(String mTodayDeaths) {
        this.mTodayDeaths = mTodayDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public void setmRecovered(String mRecovered) {
        this.mRecovered = mRecovered;
    }

    public String getmCritical() {
        return mCritical;
    }

    public void setmCritical(String mCritical) {
        this.mCritical = mCritical;
    }
}
