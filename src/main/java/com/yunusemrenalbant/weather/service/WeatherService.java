package com.yunusemrenalbant.weather.service;

import com.yunusemrenalbant.weather.dto.WeatherDto;
import com.yunusemrenalbant.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public WeatherDto getWeatherByCityName(String cityName) {

    }
}
