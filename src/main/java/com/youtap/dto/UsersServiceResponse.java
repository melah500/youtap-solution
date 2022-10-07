package com.youtap.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class UsersServiceResponse implements Serializable {

    private boolean success;
    private String responseCode;
    private String message;
    private List<UserResponse> typiCodeUsers;

    public UsersServiceResponse(boolean success, String responseCode, String message,
                                List<UserResponse> typiCodeUsers) {
        this.success = success;
        this.responseCode = responseCode;
        this.message = message;
        this.typiCodeUsers = typiCodeUsers;
    }

    public UsersServiceResponse(boolean success, String responseCode, String message) {
        this.success = success;
        this.responseCode = responseCode;
        this.message = message;
    }

    public UsersServiceResponse(boolean success, List<UserResponse> typiCodeUsers) {
        this.success = success;
        this.typiCodeUsers = typiCodeUsers;
    }
}
