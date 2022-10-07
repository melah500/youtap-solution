package com.youtap.service;

import com.youtap.dto.ServiceResponse;
import com.youtap.dto.User;
import com.youtap.dto.UsersServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static com.youtap.utils.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserFacadeService userFacadeService;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userFacadeService);

    @Test
    @DisplayName("Get User Details Returns Success When Valid User ID Is Supplied")
    void givenCorrectUserRequestWithId_ThenReturnUserDetails() throws IOException {

        when(userFacadeService.getUserDetails()).thenReturn(UsersServiceResponse.builder()
                .success(true)
                .typiCodeUsers(buildTypiCodeUsersResponse())
                .build());

        ServiceResponse<User> userServiceResponse = userService.retrieveUserDetails(USER_ID, null);
        assertNotNull(userServiceResponse);
        assertEquals(1, userServiceResponse.getData().getId());
        assertEquals("samson@email.com", userServiceResponse.getData().getEmail());
        assertEquals("+263-775-245-888", userServiceResponse.getData().getPhone());

        verify(userFacadeService, times(1)).getUserDetails();
    }

    @Test
    @DisplayName("Get User Details Returns Success When Valid Username Is Supplied")
    void givenCorrectUserRequestWithUsername_ThenReturnUserDetails() throws IOException {

        when(userFacadeService.getUserDetails()).thenReturn(UsersServiceResponse.builder()
                .success(true)
                .typiCodeUsers(buildTypiCodeUsersResponse())
                .build());

        ServiceResponse<User> userServiceResponse = userService.retrieveUserDetails(null, USERNAME);
        assertNotNull(userServiceResponse);
        assertEquals(1, userServiceResponse.getData().getId());
        assertEquals("samson@email.com", userServiceResponse.getData().getEmail());
        assertEquals("+263-775-245-888", userServiceResponse.getData().getPhone());

        verify(userFacadeService, times(1)).getUserDetails();
    }

    @Test
    @DisplayName("Get User Details Returns Success When Valid Valid User Request With Both Parameters Is Supplied")
    void givenValidUserRequestWithBothParameters_ThenReturnUserDetails() throws IOException {

        when(userFacadeService.getUserDetails()).thenReturn(UsersServiceResponse.builder()
                .success(true)
                .typiCodeUsers(buildTypiCodeUsersResponse())
                .build());

        ServiceResponse<User> userServiceResponse = userService.retrieveUserDetails(USER_ID, USERNAME);
        assertNotNull(userServiceResponse);
        assertEquals(1, userServiceResponse.getData().getId());
        assertEquals("samson@email.com", userServiceResponse.getData().getEmail());
        assertEquals("+263-775-245-888", userServiceResponse.getData().getPhone());

        verify(userFacadeService, times(1)).getUserDetails();
    }

    @Test
    @DisplayName("Get User Details Returns 404 and userID -1 When Typicode Returns Empty List")
    void givenValidUserRequest_AndTypicodeReturnsEmptyList_ThenReturnEmptyList() throws IOException {

        when(userFacadeService.getUserDetails()).thenReturn(UsersServiceResponse.builder()
                .success(true)
                .typiCodeUsers(buildEmptyTypiCodeUsersResponse())
                .build());

        ServiceResponse<User> userServiceResponse = userService.retrieveUserDetails(USER_ID, USERNAME);
        assertNotNull(userServiceResponse);
        assertFalse(userServiceResponse.isSuccess());
        assertEquals(userServiceResponse.getData().getId(), -1);
        assertEquals(userServiceResponse.getResponseCode().getCode(), 404);
        assertEquals(userServiceResponse.getNarrative(), "User not found");

        verify(userFacadeService, times(1)).getUserDetails();
    }
}
