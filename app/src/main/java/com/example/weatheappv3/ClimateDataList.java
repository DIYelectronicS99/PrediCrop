package com.example.weatheappv3;

public class ClimateDataList {

    String month, temp, rain, pressure;

    public ClimateDataList() {
    }

    public ClimateDataList(String month, String temp, String rain, String pressure) {
        this.month = month;
        this.temp = temp;
        this.rain = rain;
        this.pressure = pressure;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
}
