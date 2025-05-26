package com.example.sunrisesunset.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SunriseSunsetConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

//для обраюотки запросов, RestTemplate выолняет запросы и хранит ответы