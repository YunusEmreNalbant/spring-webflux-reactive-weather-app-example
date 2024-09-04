package com.yunusemrenalbant.weather.service;

import com.yunusemrenalbant.weather.client.WeatherClient;
import com.yunusemrenalbant.weather.dto.weather.WeatherDto;
import com.yunusemrenalbant.weather.model.Weather;
import com.yunusemrenalbant.weather.repository.WeatherRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@CacheConfig(cacheNames = {"weathers"})
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherRepository weatherRepository;
    private final WeatherClient weatherClient;

    public WeatherService(
            WeatherRepository weatherRepository,
            WeatherClient weatherClient
    ) {
        this.weatherRepository = weatherRepository;
        this.weatherClient = weatherClient;
    }

    @Cacheable(key = "#cityName")
    public Mono<WeatherDto> getWeatherByCityName(String cityName) {
        return weatherRepository.findFirstByRequestedCityNameOrderByUpdatedTimeDesc(cityName)
                .flatMap(weatherDto -> {
                    if (weatherDto != null && weatherDto.updatedTime().isBefore(LocalDateTime.now().minusMinutes(60))) {
                        return getWeatherFromWeatherStack(cityName);
                    }
                    logger.info("Getting weather from database for {} due to it is already up-to-date", cityName);

                    return Mono.justOrEmpty(weatherDto);
                })
                .switchIfEmpty(getWeatherFromWeatherStack(cityName))
                .onErrorResume(e -> Mono.error(new RuntimeException(e.getMessage())));
    }

    @CachePut(key = "#cityName")
    public Mono<WeatherDto> getWeatherFromWeatherStack(String cityName) {
        logger.info("Requesting weather stack api for city: {}", cityName);

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

    @CacheEvict(allEntries = true)
    @PostConstruct
    @Scheduled(fixedRateString = "${weather-stack.cache-ttl}")
    public void clearCache(){
        logger.info("Caches are cleared");
    }
}
