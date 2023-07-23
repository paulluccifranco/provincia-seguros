package com.proyect.provincia.service;

import com.proyect.provincia.dto.WeatherResponse;

import java.util.List;

public interface ResolverService {

    List<WeatherResponse> getWeatherList(String city, String country, String apikey);
}
