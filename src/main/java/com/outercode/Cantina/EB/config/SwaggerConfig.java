package com.outercode.Cantina.EB.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cantina system manager")
                        .version("1.0.0")
                        .description("API RESTFUL from manage one Cantina.")
                        .contact(new Contact().name("Outer Code Software Solutions")
                                .email("outercode.software@gmail.com"))
                );

    }
}
