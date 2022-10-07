package com.youtap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.youtap.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceResponse<T> {

    private ResponseCode responseCode;
    private boolean success;
    private String narrative;
    private T data;

    public ServiceResponse(final ResponseCode responseCode, final String narrative) {
        this.responseCode = responseCode;
        this.narrative = narrative;
        assignSuccess(responseCode);
    }

    public ServiceResponse(final ResponseCode responseCode, final String narrative, final T data) {
        this.responseCode = responseCode;
        this.narrative = narrative;
        this.data = data;
        assignSuccess(responseCode);
    }

    private void assignSuccess(final ResponseCode responseCode) {
        this.success = ResponseCode.SUCCESS.equals(responseCode);
    }
}