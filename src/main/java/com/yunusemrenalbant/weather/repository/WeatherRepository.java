package com.yunusemrenalbant.weather.repository;

import com.yunusemrenalbant.weather.dto.weather.WeatherDto;
import com.yunusemrenalbant.weather.model.Weather;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface WeatherRepository extends ReactiveMongoRepository<Weather, String> {

    Mono<WeatherDto> findFirstByRequestedCityNameOrderByUpdatedTimeDesc(String city);
}
