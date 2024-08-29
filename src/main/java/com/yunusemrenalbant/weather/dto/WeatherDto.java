package com.yunusemrenalbant.weather.dto;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature
) {
}
