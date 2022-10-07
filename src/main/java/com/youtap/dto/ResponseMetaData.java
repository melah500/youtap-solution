package com.youtap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMetaData implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
}