package com.yunusemrenalbant.weather.dto.weather;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature
) {
}
