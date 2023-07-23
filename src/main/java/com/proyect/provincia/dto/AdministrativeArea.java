package com.proyect.provincia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdministrativeArea {

    @JsonProperty("LocalizedName")
    private String localizedName;
}
