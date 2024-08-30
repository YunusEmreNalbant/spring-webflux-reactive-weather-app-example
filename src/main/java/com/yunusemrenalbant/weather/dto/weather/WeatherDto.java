package com.yunusemrenalbant.weather.dto.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunusemrenalbant.weather.model.Weather;

import java.time.LocalDateTime;

public record WeatherDto(
        String cityName,
        String country,
        Integer temperature,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime updatedTime
) {
    public static WeatherDto convertToDto(Weather weather) {
        return new WeatherDto(weather.getCityName(), weather.getCountry(), weather.getTemperature(), weather.getUpdatedTime());
    }
}
