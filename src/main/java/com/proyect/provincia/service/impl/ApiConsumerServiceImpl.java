package com.proyect.provincia.service.impl;

import com.proyect.provincia.dto.City;
import com.proyect.provincia.dto.WeatherResponse;
import com.proyect.provincia.service.ApiConsumerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ApiConsumerServiceImpl implements ApiConsumerService {

    @Value("${accuweather.host.cities}")
    String citiesPath;
    @Value("${accuweather.host.weather}")
    String weatherPath;

    @Override
    public WeatherResponse getWeather(City city, String apikey) {
        WebClient webClient = WebClient.create();

        WebClient.ResponseSpec response = webClient.get()
                .uri(String.format(weatherPath, city.getKey(), apikey)).retrieve();
        WeatherResponse weatherResponse = response.bodyToFlux(WeatherResponse.class).blockFirst();
        weatherResponse.setCity(city.getLocalizedName());
        weatherResponse.setProvince(city.getAdministrativeArea().getLocalizedName());
        weatherResponse.setCountry(city.getCountry().getLocalizedName());
        weatherResponse.setRegion(city.getRegion().getLocalizedName());
        return weatherResponse;
    }

    @Override
    public List<City> getCities(String param, String apikey) {
        WebClient webClient = WebClient.create();

        WebClient.ResponseSpec response = webClient.get()
                .uri(String.format(citiesPath, apikey, param)).retrieve();
        return response.bodyToFlux(City.class).collectList().block();
    }
}
