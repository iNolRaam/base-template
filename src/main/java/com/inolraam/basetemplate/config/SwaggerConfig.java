package com.inolraam.basetemplate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

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
        description = "Acces Token For Swagger",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
