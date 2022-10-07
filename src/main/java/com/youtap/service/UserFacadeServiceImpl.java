package com.youtap.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtap.configuration.TypiCodeProperties;
import com.youtap.constants.AppConstants;
import com.youtap.dto.UserResponse;
import com.youtap.dto.UsersServiceResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@Service
public class UserFacadeServiceImpl implements UserFacadeService {

    private OkHttpClient okHttpClient;

    private TypiCodeProperties typiCodeProperties;

    public void setTypiCodeProperties(TypiCodeProperties typiCodeProperties) {
        this.typiCodeProperties = typiCodeProperties;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    /**
     * {@inheritDoc}
     */
    public UsersServiceResponse getUserDetails() throws IOException {

        String typicodeApiUrl = typiCodeProperties.getUrl();
        HttpUrl url =
                HttpUrl.parse(typicodeApiUrl).newBuilder().build();

        Request request =
                new Request.Builder()
                        .url(url)
                        .get()
                        .build();

        try (okhttp3.Response response = okHttpClient.newCall(request).execute()) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            List<UserResponse> typiCodeUsers = objectMapper.readValue(response.body().string(), new TypeReference<>() {
            });

            log.info("Typicode users : {}", typiCodeUsers);
            if (response.isSuccessful() && typiCodeUsers != null && !typiCodeUsers.isEmpty()) {
                return new UsersServiceResponse(true, typiCodeUsers);
            } else if (response.isSuccessful() && typiCodeUsers != null && typiCodeUsers.isEmpty()) {
                return new UsersServiceResponse(true, new ArrayList<>());
            }
            return new UsersServiceResponse(false, String.valueOf(response.code()),
                    AppConstants.GENERIC_FAILURE, null);
        } catch (Exception e) {
            log.error("Unknown error on {} -> Exception : {} ", this.getClass().getName(), e);
            throw e;
        }
    }
}
