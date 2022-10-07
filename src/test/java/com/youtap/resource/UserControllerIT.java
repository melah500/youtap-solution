package com.youtap.resource;

import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpStatusCode;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.Matchers.equalTo;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@MockServerTest({"swapi.baseUrl=http://localhost:${mockServerPort}"})
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private MockServerClient mockServerClient;

    final ClassPathResource noContentResource = new ClassPathResource("mappings/users_200_OK_with_no_content.json");
    final ClassPathResource resourceWithContent = new ClassPathResource("mappings/users_200_OK_with_content.json");

    @Test
    void requestingUserDetails_ValidUserId_Returns200() throws Exception {

        final var mockedResponse = Files.readString(Path.of(resourceWithContent.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?userId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email", equalTo("Sincere@april.biz")));
    }

    @Test
    void requestingUserDetails_ValidUsername_Returns200() throws Exception {

        final var mockedResponse = Files.readString(Path.of(resourceWithContent.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?username=Bret")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email", equalTo("Sincere@april.biz")));
    }

    @Test
    void requestingUserDetails_BothParametersValid_Returns200() throws Exception {

        final var mockedResponse = Files.readString(Path.of(resourceWithContent.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?userId=1&username=Bret")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.email", equalTo("Sincere@april.biz")));
    }

    @Test
    void requestingUserDetails_InvalidUserId_Returns404() throws Exception {

        final var mockedResponse = Files.readString(Path.of(resourceWithContent.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?userId=1000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metaData.code", equalTo(404)))
                .andExpect(jsonPath("$.user.id", equalTo(-1)))
                .andExpect(jsonPath("$.metaData.message", equalTo("User not found")));
    }

    @Test
    void requestingUserDetails_InvalidUsername_Returns404() throws Exception {

        final var mockedResponse = Files.readString(Path.of(resourceWithContent.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?username=Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metaData.code", equalTo(404)))
                .andExpect(jsonPath("$.user.id", equalTo(-1)))
                .andExpect(jsonPath("$.metaData.message", equalTo("User not found")));
    }

    @Test
    void requestingUserDetails_InvalidRequestParam_Returns200_With400MetadataCode() throws Exception {

        final var mockedResponse = Files.readString(Path.of(resourceWithContent.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?wrong_param=Doe")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metaData.code", equalTo(400)))
                .andExpect(jsonPath("$.metaData.message", equalTo("Both UserID and Username cannot be null")));
    }

    @Test
    void requestingUserDetails_EmptyResponseFromTypiCode_Returns200_WithEmptyList() throws Exception {

        final var mockedResponse = Files.readString(Path.of(noContentResource.getURI()));

        mockServerClient
                .when(request().withPath("/users"))
                .respond(response()
                        .withStatusCode(HttpStatusCode.OK_200.code())
                        .withContentType(org.mockserver.model.MediaType.APPLICATION_JSON)
                        .withBody(mockedResponse));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getusercontacts?userId=2000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.metaData.code", equalTo(404)))
                .andExpect(jsonPath("$.user.id", equalTo(-1)))
                .andExpect(jsonPath("$.metaData.message", equalTo("User not found")));
    }
}
