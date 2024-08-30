package com.yunusemrenalbant.weather.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherCurrent(
        @JsonProperty("observation_time")
        String observationTime,

        @JsonProperty("temperature")
        Integer temperature,

        @JsonProperty("weather_code")
        Integer weatherCode,

        @JsonProperty("weather_icons")
        String[] weatherIcons,

        @JsonProperty("weather_descriptions")
        String[] weatherDescriptions,

        @JsonProperty("wind_speed")
        Integer windSpeed,

        @JsonProperty("wind_degree")
        Integer windDegree,

        @JsonProperty("wind_dir")
        String windDir,

        @JsonProperty("pressure")
        Integer pressure,

        @JsonProperty("precip")
        Integer precip,

        @JsonProperty("humidity")
        Integer humidity,

        @JsonProperty("cloudcover")
        Integer cloudcover,

        @JsonProperty("feelslike")
        Integer feelslike,

        @JsonProperty("uv_index")
        Integer uvIndex,

        @JsonProperty("visibility")
        Integer visibility,

        @JsonProperty("is_day")
        String isDay
) {
}