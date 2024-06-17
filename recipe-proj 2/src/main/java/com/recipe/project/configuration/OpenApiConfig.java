package com.recipe.project.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Recipe API").description(
                        "This project basically allows users to manage their favorite recipes. It should allow adding, \n" +
                                "updating, removing, and fetching recipes. Additionally, users should be able to filter available recipes \n" +
                                "based on specific criteria,\n" +
                                "Insert new details, Delete the existing ones (CRUD operations)"));
    }
}
