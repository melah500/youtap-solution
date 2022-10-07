package com.youtap.configuration;

import com.youtap.service.UserFacadeService;
import com.youtap.service.UserFacadeServiceImpl;
import com.youtap.service.UserService;
import com.youtap.service.UserServiceImpl;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfiguration {

    private final TypiCodeProperties typiCodeProperties;
    private final OkHttpClient okHttpClient = new OkHttpClient();

    public BusinessConfiguration(final TypiCodeProperties typiCodeProperties) {
        this.typiCodeProperties = typiCodeProperties;
    }

    @Bean
    public UserFacadeService userFacadeService() {
        return UserFacadeServiceImpl.builder()
                .typiCodeProperties(typiCodeProperties)
                .okHttpClient(okHttpClient)
                .build();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public UserService userService(final UserFacadeService userFacadeService) {
        return UserServiceImpl.builder()
                .userFacadeService(userFacadeService)
                .build();
    }
}
