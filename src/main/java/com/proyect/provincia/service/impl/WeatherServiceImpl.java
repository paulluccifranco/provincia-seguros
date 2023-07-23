package com.proyect.provincia.service.impl;

import com.proyect.provincia.dto.WeatherLocalResponse;
import com.proyect.provincia.mapper.WeatherMapper;
import com.proyect.provincia.model.Weather;
import com.proyect.provincia.repository.WeatherRepository;
import com.proyect.provincia.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;

    @Override
    public List<WeatherLocalResponse> getPersistedWeather() {
        return weatherRepository.findAll().stream().map(WeatherMapper::mapDomainToResponse).collect(Collectors.toList());
    }

    @Override
    public void saveWeathers(List<Weather> weathers) {
        weatherRepository.saveAll(weathers);
    }
}
