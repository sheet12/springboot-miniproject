package com.miniproject.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo() {
       return new ApiInfo(
                "Spring boot mini project for RESTful Web Service documentation",
                "This pages documents Photo app RESTful Web Service endpoints",
                "1.0",
                "Terms of service",
                 new Contact("Sheetal", "theSheetal","sheetal@gmail.com"),
                "License of API",
                "API License URL",
                 Collections.emptyList()
       );
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
