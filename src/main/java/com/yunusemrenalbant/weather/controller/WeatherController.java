package com.yunusemrenalbant.weather.controller;

import com.yunusemrenalbant.weather.dto.weather.WeatherDto;
import com.yunusemrenalbant.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/weathers")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/{cityName}")
    public Mono<WeatherDto> getWeatherByCityName(@PathVariable String cityName) {
        return weatherService.getWeatherByCityName(cityName);
    }
}
