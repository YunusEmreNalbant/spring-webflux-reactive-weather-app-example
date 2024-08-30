package com.yunusemrenalbant.weather.dto.weather;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherLocation(
        String name,
        String country,
        String region,
        Double lat,
        Double lan,
        @JsonProperty("timezone_id")
        String timezoneId,
        String localtime,
        @JsonProperty("localtime_epoch")
        String localtimeEpoch,
        @JsonProperty("utc_offset")
        String utfOffset
) {
}
