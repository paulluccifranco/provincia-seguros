package com.proyect.provincia.controller;

import com.proyect.provincia.dto.WeatherLocalResponse;
import com.proyect.provincia.dto.WeatherResponse;
import com.proyect.provincia.service.ResolverService;
import com.proyect.provincia.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final ResolverService resolverService;
    private final WeatherService weatherService;

    @GetMapping("")
    public ResponseEntity<List<WeatherResponse>> getWeather(@RequestParam(required = false) String city,
                                                            @RequestParam(required = false) String country,
                                                            @RequestHeader("apikey") String apikey) {
        if (Objects.isNull(city) || Objects.isNull(country))
            throw new IllegalArgumentException("Debe proporcionar los valores de city y country");
        List<WeatherResponse> weatherResponseList = resolverService.getWeatherList(city, country, apikey);
        return ResponseEntity.ok(weatherResponseList);
    }

    @GetMapping("/local")
    public ResponseEntity<List<WeatherLocalResponse>> getPersistedWeather(){
        List<WeatherLocalResponse> weathers = weatherService.getPersistedWeather();
        return ResponseEntity.ok(weathers);
    }
}
