package com.yunusemrenalbant.weather.dto.weather;

public record WeatherResponse(
        WeatherRequest request,
        WeatherLocation location,
        WeatherCurrent current
) {


}
