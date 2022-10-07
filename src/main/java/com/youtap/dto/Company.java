package com.youtap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String catchPhrase;
    private String bs;
}
