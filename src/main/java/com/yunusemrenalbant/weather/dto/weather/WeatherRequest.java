package com.yunusemrenalbant.weather.dto.weather;

public record WeatherRequest(
        String type,
        String query,
        String language,
        String unit
) {
}
