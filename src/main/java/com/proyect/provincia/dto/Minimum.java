package com.proyect.provincia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class Minimum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("Value")
    private BigDecimal value;
    @JsonProperty("Unit")
    private String unit;
    @JsonProperty("UnitType")
    private BigDecimal unitType;

}
