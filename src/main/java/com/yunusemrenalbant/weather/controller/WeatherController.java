package com.yunusemrenalbant.weather.controller;

import com.yunusemrenalbant.weather.dto.weather.WeatherDto;
import com.yunusemrenalbant.weather.service.WeatherService;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.reactor.ratelimiter.operator.RateLimiterOperator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import io.github.resilience4j.ratelimiter.RateLimiter;

@RestController
@RequestMapping("/api/weathers")
public class WeatherController {

    private final WeatherService weatherService;
    private final RateLimiter rateLimiter;

    public WeatherController(WeatherService weatherService, RateLimiterRegistry rateLimiterRegistry) {
        this.weatherService = weatherService;
        this.rateLimiter = rateLimiterRegistry.rateLimiter("basic");
    }

    @GetMapping("/{cityName}")
    public Mono<WeatherDto> getWeatherByCityName(@PathVariable String cityName) {
        return weatherService.getWeatherByCityName(cityName)
                .transformDeferred(RateLimiterOperator.of(rateLimiter));
    }
}
