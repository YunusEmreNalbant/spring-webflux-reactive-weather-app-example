package com.yunusemrenalbant.weather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherCurrent(
        Integer temperature
) {
}
