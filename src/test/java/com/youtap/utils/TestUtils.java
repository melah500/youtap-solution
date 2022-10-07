package com.youtap.utils;

import com.youtap.dto.*;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static final String USER_ID = "1";
    public static final String USERNAME = "Samson";

    public static Response buildUserApiResponse() {
        return Response.builder()
                .metaData(ResponseMetaData.builder()
                        .code(200)
                        .message("Success")
                        .build())
                .user(User.builder()
                        .id(1)
                        .email("samson@email.com")
                        .phone("+263-775-245-888"))
                .build();
    }

    public static List<UserResponse> buildTypiCodeUsersResponse() {
        List<UserResponse> typiCodeUsers = new ArrayList<>();
        UserResponse userResponse = UserResponse.builder()
                .id(1)
                .username("Samson")
                .name("Samson Nyabanga")
                .email("samson@email.com")
                .address(Address.builder()
                        .street("Warren View")
                        .suite("2")
                        .build())
                .phoneNumber("+263-775-245-888")
                .website("https://samson.co.zw")
                .company(Company.builder()
                        .name("Samson Pvt Ltd.")
                        .catchPhrase("Creative Solutions")
                        .bs("transition cutting-edge web services")
                        .build())
                .build();
        typiCodeUsers.add(userResponse);
        return typiCodeUsers;
    }

    public static List<UserResponse> buildEmptyTypiCodeUsersResponse() {
        return new ArrayList<>();
    }
}
