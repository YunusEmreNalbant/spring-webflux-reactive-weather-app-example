package com.yunusemrenalbant.weather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${weather-stack.api-url}")
    private String WEATHER_API_BASE_URL;

    @Bean("defaultWebClientBuilder")
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean("weatherServiceWebClient")
    public WebClient weatherServiceWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder.baseUrl(WEATHER_API_BASE_URL).build();
    }

}
