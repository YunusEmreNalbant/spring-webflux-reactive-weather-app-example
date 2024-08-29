package com.yunusemrenalbant.weather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherLocation(
        String name,
        String country,
        @JsonProperty("localtime")
        String localTime
) {
}
