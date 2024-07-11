package com.example.bughunteraz.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    // Eğer Swagger UI yapılandırmasını özelleştirmek istiyorsanız, ek konfigürasyonlar ekleyebilirsiniz
//     @Bean
//     public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//         return new SwaggerUiConfigParameters();
//     }
}
