package com.yunusemrenalbant.weather.repository;

import com.yunusemrenalbant.weather.model.Weather;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WeatherRepository extends ReactiveMongoRepository<Weather, String> {
}
