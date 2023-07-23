package com.proyect.provincia.service.impl;

import com.proyect.provincia.dto.City;
import com.proyect.provincia.dto.WeatherResponse;
import com.proyect.provincia.mapper.WeatherMapper;
import com.proyect.provincia.model.Weather;
import com.proyect.provincia.service.ApiConsumerService;
import com.proyect.provincia.service.ResolverService;
import com.proyect.provincia.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResolverServiceImpl implements ResolverService {

    private final ApiConsumerService apiConsumerService;
    private final WeatherService weatherService;

    @Override
    public List<WeatherResponse> getWeatherList(String city, String country, String apikey) {
        String searchParam = String.format("%s %s", city, Objects.isNull(country) ? "" : country );
        List<City> cities = apiConsumerService.getCities(searchParam, apikey);
        List<WeatherResponse> weatherResponses = cities.stream().map(cityElement -> apiConsumerService.getWeather(cityElement, apikey)).collect(Collectors.toList());
        persistWeatherList(weatherResponses);

        return weatherResponses;
    }

    @Async
    void persistWeatherList(List<WeatherResponse> weatherResponses) {
        List<Weather> weathers = weatherResponses.stream().map(WeatherMapper::mapResponseToDomain).collect(Collectors.toList());
        weatherService.saveWeathers(weathers);
    }
}
