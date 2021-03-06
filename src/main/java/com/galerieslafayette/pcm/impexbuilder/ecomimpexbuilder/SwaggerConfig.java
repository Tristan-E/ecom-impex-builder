package com.galerieslafayette.pcm.impexbuilder.ecomimpexbuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author teyma
 * @since 11/03/2018
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Ecom-impex-builder REST API",
                "This is an awesome API made by me.",
                "API 0.0.1",
                "Do whatever you want :)",
                new Contact("RPU", "www.example.com", "rpu@galerieslafayette.com"),
                "License of API", "API license URL", Collections.emptyList()
        );
    }
}