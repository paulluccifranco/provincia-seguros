package com.proyect.provincia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String province;
    private String country;
    private String region;
    @JsonProperty("DailyForecasts")
    private List<DailyForecasts> dailyForecasts;
}
