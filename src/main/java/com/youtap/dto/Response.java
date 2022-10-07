package com.youtap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Schema(name = "metaData", description = "Additional Information about the response")
    private ResponseMetaData metaData;
    @Schema(name = "user", description = "User Details")
    private T user;

    public Response(ResponseMetaData metaData) {
        this.metaData = metaData;
    }

    public Response(ResponseMetaData metaData, T user) {
        this.metaData = metaData;
        this.user = user;
    }

    public Response(T user) {
        this.user = user;
    }
}
