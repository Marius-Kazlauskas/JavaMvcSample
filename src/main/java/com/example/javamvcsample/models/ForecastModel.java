package com.example.javamvcsample.models;

public class ForecastModel {
    public String dateTime;
    public Double temperature;

    public ForecastModel(String dateTime, Double temperature) {
        this.dateTime = dateTime;
        this.temperature = temperature;
    }
}
