package com.proyect.provincia.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class WeatherLocalResponse {

    private Long id;
    private String city;
    private String province;
    private String country;
    private String region;
    private Date date;
    private String minimumTemperature;
    private String maximumTemperature;
    private String dayStatus;
    private String dayPrecipitation;
    private String dayPrecipitationIntensity;
    private String nightStatus;
    private String nightPrecipitation;
    private String nightPrecipitationIntensity;
}
