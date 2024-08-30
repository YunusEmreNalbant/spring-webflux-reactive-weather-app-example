package com.yunusemrenalbant.weather.service;

import com.yunusemrenalbant.weather.client.WeatherClient;
import com.yunusemrenalbant.weather.dto.weather.WeatherDto;
import com.yunusemrenalbant.weather.model.Weather;
import com.yunusemrenalbant.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherClient weatherClient;

    public WeatherService(
            WeatherRepository weatherRepository,
            WeatherClient weatherClient
    ) {
        this.weatherRepository = weatherRepository;
        this.weatherClient = weatherClient;
    }

    public Mono<WeatherDto> getWeatherByCityName(String cityName) {
        return weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(cityName)
                .flatMap(weatherDto -> {

                    if (weatherDto.updatedTime().isBefore(LocalDateTime.now().minusSeconds(30))) {
                        return getWeatherFromWeatherStack(cityName);
                    }
                    return Mono.just(weatherDto);
                })
                .switchIfEmpty(getWeatherFromWeatherStack(cityName))
                .onErrorResume(e -> Mono.error(new RuntimeException("Error processing weather data", e)));
    }

    public Mono<WeatherDto> getWeatherFromWeatherStack(String cityName) {
        return weatherClient.getWeatherByCityName(cityName)
                .flatMap(response -> {
                    try {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                        Weather weather = new Weather(
                                cityName,
                                response.location().name(),
                                response.location().country(),
                                response.current().temperature(),
                                LocalDateTime.now(),
                                LocalDateTime.parse(response.location().localtime(), dateTimeFormatter)
                        );

                        return weatherRepository.save(weather)
                                .map(WeatherDto::convertToDto);
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("Error converting response to Weather", e));
                    }
                });
    }

}
