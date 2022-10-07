package com.youtap.service;

import com.youtap.dto.UsersServiceResponse;

import java.io.IOException;

public interface UserFacadeService {

    /**
     * Get user details.
     *
     * @return {@link UsersServiceResponse}
     */
    UsersServiceResponse getUserDetails() throws IOException;
}
