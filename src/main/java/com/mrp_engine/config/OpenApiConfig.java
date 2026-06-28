package com.mrp_engine.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI manufacturingMRPOpenAPI() {

        return new OpenAPI()

                .info(new Info()

                        .title("Manufacturing Enterprise MRP Engine API")

                        .description("""
                                Manufacturing Enterprise Resource Planning System

                                Features:

                            • User Authentication
                            • Item Management
                            • Inventory Management
                            • Bill of Materials (BOM)
                            • MRP Explosion
                            • Auto Purchase Order Generation
                            • Purchase Order Approval/Rejection
                            • Global Exception Handling
                                """)

                        .version("1.0.0")

                        .contact(new Contact()

                                .name("Harshitha")

                                .email("vungaharshitha@gmail.com")

                                .url("https://github.com/gvbh85/Manufacturing-Enterprise-MRP-Engine"))

                        .license(new License()

                                .name("MIT License")

                                .url("https://opensource.org/licenses/MIT")))

                .externalDocs(

                        new ExternalDocumentation()

                                .description("Project Documentation")

                                .url("https://github.com/gvbh85/Manufacturing-Enterprise-MRP-Engine"));
    }

}