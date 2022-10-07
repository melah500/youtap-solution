package com.youtap.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {

    @Schema(name = "id", description = "User ID", example = "4")
    private int id;
    @Schema(name = "email", description = "User Email Address", example = "user@email.com")
    private String email;
    @Schema(name = "phone", description = "User Phone Number", example = "010-692-6593 x09125")
    private String phone;
}
