package com.youtap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;
    private String suite;
    private String city;
    @JsonProperty("zipcode")
    private String zipCode;
    private Geo geo;
}
