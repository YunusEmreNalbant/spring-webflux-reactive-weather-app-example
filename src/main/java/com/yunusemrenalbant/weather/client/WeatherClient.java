package com.yunusemrenalbant.weather.client;

import com.yunusemrenalbant.weather.dto.weather.WeatherDto;
import com.yunusemrenalbant.weather.dto.weather.WeatherResponse;
import com.yunusemrenalbant.weather.model.Weather;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class WeatherClient {

    private final WebClient webClient;

    public WeatherClient(@Qualifier("weatherServiceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<WeatherResponse> getWeatherByCityName(String cityName) {
        return webClient.get()
                .uri("?access_key=7529d1784940d22d1d97bf05a6e968c0&query=" + cityName)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .log("getWeatherByCityName")
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.empty());
    }
}
