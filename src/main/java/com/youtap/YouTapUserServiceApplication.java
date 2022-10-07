package com.youtap;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "YouTap User Service API",
                version = "1.0",
                description = "API Documentation for YouTap User Service"
        ), servers = {
        @Server(description = "development",
                url = "http://localhost:8090")
}
)
public class YouTapUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(YouTapUserServiceApplication.class, args);
    }

}
