package com.yunusemrenalbant.weather.client;

import com.yunusemrenalbant.weather.dto.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class WeatherClient {

    @Value("${weather-stack.api-key}")
    private String ACCESS_KEY;

    private final WebClient webClient;

    public WeatherClient(@Qualifier("weatherServiceWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<WeatherResponse> getWeatherByCityName(String cityName) {
        return webClient.get()
                .uri("?access_key=" + ACCESS_KEY + "&query=" + cityName)
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .log("getWeatherByCityName")
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.empty());
    }
}
