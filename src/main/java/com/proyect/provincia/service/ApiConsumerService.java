package com.proyect.provincia.service;

import com.proyect.provincia.dto.City;
import com.proyect.provincia.dto.WeatherResponse;

import java.util.List;

public interface ApiConsumerService {

    WeatherResponse getWeather(City city, String apikey);
    List<City> getCities(String param, String apikey);
}
