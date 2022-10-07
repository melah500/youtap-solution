package com.youtap.mapper;

import com.youtap.dto.User;
import com.youtap.dto.UserResponse;

public class UserMapper {

    public static User mapToUser(UserResponse userResponse) {
        if (userResponse != null) {
            return User.builder()
                    .id(userResponse.getId())
                    .email(userResponse.getEmail())
                    .phone(userResponse.getPhoneNumber())
                    .build();
        }
        return null;
    }
}
