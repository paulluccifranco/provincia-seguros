package com.proyect.provincia.mapper;

import com.proyect.provincia.dto.DailyForecasts;
import com.proyect.provincia.dto.WeatherLocalResponse;
import com.proyect.provincia.dto.WeatherResponse;
import com.proyect.provincia.model.Weather;

public class WeatherMapper {

    private WeatherMapper(){}

    public static Weather mapResponseToDomain(WeatherResponse weatherResponse) {
        Weather weather = new Weather();
        weather.setRegion(weatherResponse.getRegion());
        weather.setProvince(weatherResponse.getProvince());
        weather.setCountry(weatherResponse.getCountry());
        weather.setCity(weatherResponse.getCity());
        DailyForecasts dailyForecasts = weatherResponse.getDailyForecasts().stream().findFirst().get();
        weather.setDate(dailyForecasts.getDate());
        weather.setMinimumTemperature(dailyForecasts.getTemperature().getMinimum().getValue() + " " + dailyForecasts.getTemperature().getMinimum().getUnit());
        weather.setMaximumTemperature(dailyForecasts.getTemperature().getMaximum().getValue() + " " + dailyForecasts.getTemperature().getMaximum().getUnit());
        weather.setDayStatus(dailyForecasts.getDay().getIconPhrase());
        weather.setDayPrecipitation(dailyForecasts.getDay().getPrecipitationType());
        weather.setDayPrecipitationIntensity(dailyForecasts.getDay().getPrecipitationIntensity());
        weather.setNightStatus(dailyForecasts.getNight().getIconPhrase());
        weather.setNightPrecipitation(dailyForecasts.getNight().getPrecipitationType());
        weather.setNightPrecipitationIntensity(dailyForecasts.getNight().getPrecipitationIntensity());
        return weather;
    }

    public static WeatherLocalResponse mapDomainToResponse(Weather weather){
        WeatherLocalResponse weatherLocalResponse = new WeatherLocalResponse();
        weatherLocalResponse.setRegion(weather.getRegion());
        weatherLocalResponse.setProvince(weather.getProvince());
        weatherLocalResponse.setCountry(weather.getCountry());
        weatherLocalResponse.setCity(weather.getCity());
        weatherLocalResponse.setDate(weather.getDate());
        weatherLocalResponse.setMinimumTemperature(weather.getMinimumTemperature());
        weatherLocalResponse.setMaximumTemperature(weather.getMaximumTemperature());
        weatherLocalResponse.setDayStatus(weather.getDayStatus());
        weatherLocalResponse.setDayPrecipitation(weather.getDayPrecipitation());
        weatherLocalResponse.setDayPrecipitationIntensity(weather.getDayPrecipitationIntensity());
        weatherLocalResponse.setNightStatus(weather.getNightStatus());
        weatherLocalResponse.setNightPrecipitation(weather.getNightPrecipitation());
        weatherLocalResponse.setNightPrecipitationIntensity(weather.getNightPrecipitationIntensity());
        return weatherLocalResponse;
    }
}
