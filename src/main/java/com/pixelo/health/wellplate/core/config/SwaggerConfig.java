package com.pixelo.health.wellplate.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// Well-Plate/swagger-ui/index.html
@OpenAPIDefinition(info = @Info(title = "Well-Plate API", description = "건강한 식단관리 API Document"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private static final String TITLE = "Well-Plate";
    private static final String DESCRIPTION = "식단관리 시스템";
    private static final String VERSION = "3.0.0";
    private static final String URL = "";

    @Bean
    public OpenAPI openAPI() {
        var securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("Bearer 를 제외한 토큰")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        var securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title(TITLE)
                        .description(DESCRIPTION)
                        .version(VERSION)
                        .license(new License().name("Apache 2.0").url(URL)))
                .externalDocs(new ExternalDocumentation()
                        .description(DESCRIPTION)
                        .url(URL))
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(List.of(securityRequirement));
    }
}
