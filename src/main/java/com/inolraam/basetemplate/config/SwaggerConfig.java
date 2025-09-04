package com.inolraam.basetemplate.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.inolraam.basetemplate.config.swagger.CommonJson;
import com.inolraam.basetemplate.config.swagger.CommonResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@OpenAPIDefinition(
        info = @Info(
                title = "Base-Template",
                description = "A breve description about base-template",
                termsOfService = "www.inolraam.com/terms-and-service",
                version = "1.0.0",
                license = @License(
                        name = "Standard Software",
                        url = "www.inolraam.com/license"
                ),
                contact = @Contact(
                        name = "Marlon Castillo",
                        email = "aac.marlondv@gmail.com",
                        url = "www.inolraam.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Dev environment"
                ),
                @Server(
                        url = "http://www.inolraam.com",
                        description = "Production environment"
                )
        },
        security = {
                @SecurityRequirement(name = "Security Token")
        }
)
@SecurityScheme(
        name = "Security Token",
        description = "Access Token For Swagger",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
@Configuration
public class SwaggerConfig {

    // Bean OpenAPI to centralize responses
    @Bean
    public OpenAPI customOpenAPI() throws JsonProcessingException {
        Components components = new Components();

        for (CommonResponse resp : CommonResponse.values()) {
            ApiResponse apiResponse = new ApiResponse().description(resp.getDescription());
            if (resp.equals(CommonResponse.DEFAULT_400)) {
                apiResponse.content(
                        new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().example(getJsonNode(CommonJson.RESPONSE_400_EXAMPLE)))
                );
            }

            components.addResponses(resp.getCode(), apiResponse);
        }

        return new OpenAPI().components(components);
    }

    //Method to correctly format the json
    private ObjectNode getJsonNode(String jsonString) throws JsonProcessingException {
        return (ObjectNode) new ObjectMapper().readTree(jsonString);
    }
}
