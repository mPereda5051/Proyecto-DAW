package com.jinbu.jinbu.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI getOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Jinbu API Documentación")
                        .version("0.5")
                        .description("Documentación para la API de la aplicación web de fotografía Jinbu")
                );

    }
}
