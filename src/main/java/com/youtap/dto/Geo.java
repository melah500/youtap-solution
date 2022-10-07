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
public class Geo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lng")
    private String longitude;
}
