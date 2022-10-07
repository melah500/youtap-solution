package com.youtap.resource;

import com.youtap.constants.AppConstants;
import com.youtap.dto.Response;
import com.youtap.dto.ServiceResponse;
import com.youtap.dto.User;
import com.youtap.service.UserService;
import com.youtap.utils.RestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.youtap.constants.AppConstants.SwaggerConstants.PUBLIC_RELEASED;

@RestController
@RequestMapping(AppConstants.RETRIEVE_USERS_URI)
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Retrieve user details.
     *
     * @param userId   User ID
     * @param username Username
     * @return {@link ResponseEntity<Response<User>>} User Details.
     */
    @GetMapping(produces = {"application/json"})
    @Operation(description = "Get User Details")
    @ApiResponse(responseCode = "200", description = "User details successfully retrieved",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @Tags(@Tag(name = PUBLIC_RELEASED))
    public ResponseEntity<Response<User>> retrieveUserDetails(
            @Parameter(description = "User ID. If this parameter is not supplied, the username will be used to " +
                    "search for the user if supplied.")
            @RequestParam(value = "userId", required = false) String userId,
            @Parameter(description = "Username. If this parameter is not supplied, the userId will be used to " +
                    "search for the user if supplied.")
            @RequestParam(value = "username", required = false) String username) throws IOException {
        log.info("Received a request to retrieve user details. UserID : {}, Username : {}", userId, username);
        ServiceResponse<User> serviceResponse = userService.retrieveUserDetails(userId, username);
        if (serviceResponse.isSuccess()) {
            return ResponseEntity.ok(new Response<>(serviceResponse.getData()));
        }
        return ResponseEntity.ok(new Response<>(RestUtils.getMetaData(serviceResponse), serviceResponse.getData()));
    }
}
