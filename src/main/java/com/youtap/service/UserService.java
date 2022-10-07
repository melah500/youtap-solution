package com.youtap.service;

import com.youtap.dto.ServiceResponse;
import com.youtap.dto.User;

import java.io.IOException;

public interface UserService {

    /**
     * Request to get user details.
     *
     * @param userId   User ID
     * @param username Username
     * @return {@link ServiceResponse<User>}
     */
    ServiceResponse<User> retrieveUserDetails(final String userId, final String username) throws IOException;
}
