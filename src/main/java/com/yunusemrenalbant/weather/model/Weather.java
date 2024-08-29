package com.yunusemrenalbant.weather.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "weathers")
public class Weather {

    @Id
    private String id;

    private String requestedCityName;

    private String cityName;

    private String country;

    private Integer temperature;

    private LocalDateTime updatedTime;

    private LocalDateTime responseLocalTime;


    public Weather(String requestedCityName, String cityName, String country, Integer temperature, LocalDateTime updatedTime, LocalDateTime responseLocalTime) {
        this.requestedCityName = requestedCityName;
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.updatedTime = updatedTime;
        this.responseLocalTime = responseLocalTime;
    }

    public Weather() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequestedCityName() {
        return requestedCityName;
    }

    public void setRequestedCityName(String requestedCityName) {
        this.requestedCityName = requestedCityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public LocalDateTime getResponseLocalTime() {
        return responseLocalTime;
    }

    public void setResponseLocalTime(LocalDateTime responseLocalTime) {
        this.responseLocalTime = responseLocalTime;
    }
}
