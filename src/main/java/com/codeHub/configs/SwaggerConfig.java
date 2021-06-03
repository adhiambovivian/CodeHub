/* Copyright (C)2021  Vivian */
package com.codeHub.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket documentation() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInformation());
    }

    private ApiInfo apiInformation() {
        return new ApiInfoBuilder()
                .title("CodeHub")
                .description("Code that inspires doc")
                .version("1.0")
                .contact(new Contact("Adhiambo Vivian", "https://medium.com/@adhiambovivian", ""))
                .license("")
                .licenseUrl("")
                .termsOfServiceUrl("Terms & Conditions URL")
                .version("1.0")
                .build();
    }
}
