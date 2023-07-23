package com.proyect.provincia.service;

import com.proyect.provincia.dto.WeatherLocalResponse;
import com.proyect.provincia.model.Weather;

import java.util.List;

public interface WeatherService {

    List<WeatherLocalResponse> getPersistedWeather();
    void saveWeathers(List<Weather> weathers);
}
