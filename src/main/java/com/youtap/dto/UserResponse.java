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
public class UserResponse implements Serializable {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private Address address;
    @JsonProperty("phone")
    private String phoneNumber;
    private String website;
    private Company company;
}
