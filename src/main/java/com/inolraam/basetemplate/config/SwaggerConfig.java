package com.inolraam.basetemplate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

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
                        email = "aac.marlondv@gmail.com"
                )

        )
)
public class SwaggerConfig {
}
