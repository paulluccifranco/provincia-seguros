package com.proyect.provincia.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
