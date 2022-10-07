package com.youtap.service;

import com.youtap.constants.AppConstants;
import com.youtap.dto.ServiceResponse;
import com.youtap.dto.User;
import com.youtap.dto.UserResponse;
import com.youtap.dto.UsersServiceResponse;
import com.youtap.enums.ResponseCode;
import com.youtap.mapper.UserMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Builder
public class UserServiceImpl implements UserService {

    private UserFacadeService userFacadeService;

    public void setUserFacadeService(UserFacadeService userFacadeService) {
        this.userFacadeService = userFacadeService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ServiceResponse<User> retrieveUserDetails(String userId, String username) throws IOException {
        if (StringUtils.isAllBlank(userId, username)) {
            return new ServiceResponse<>(ResponseCode.BAD_REQUEST, AppConstants.INVALID_REQUEST);
        }
        UsersServiceResponse usersServiceResponse = userFacadeService.getUserDetails();
        if (!usersServiceResponse.isSuccess() || usersServiceResponse.getTypiCodeUsers() == null) {
            log.error("Could not retrieve users for request|> UserID : [{}], Username : [{}]", userId, username);
            return new ServiceResponse<>(ResponseCode.INTERNAL_ERROR, AppConstants.FAILED);
        }

        Optional<UserResponse> optionalUserResponse = Optional.empty();
        if (!StringUtils.isBlank(userId)) {
            optionalUserResponse = usersServiceResponse.getTypiCodeUsers()
                    .stream()
                    .filter(userResponse -> (userResponse.getId().equals(Integer.parseInt(userId))))
                    .findFirst();
        } else if (!StringUtils.isBlank(username)) {
            optionalUserResponse = usersServiceResponse.getTypiCodeUsers()
                    .stream()
                    .filter(userResponse -> (userResponse.getUsername().equals(username)))
                    .findFirst();
        }
        log.info("Users response from typicode|> {}", optionalUserResponse);

        if (optionalUserResponse.isPresent()) {
            return new ServiceResponse<>(ResponseCode.SUCCESS, true, AppConstants.SUCCESS,
                    UserMapper.mapToUser(optionalUserResponse.get()));
        }
        return new ServiceResponse<>(ResponseCode.NOT_FOUND, AppConstants.USER_NOT_FOUND, User.builder().id(-1).build());
    }
}
