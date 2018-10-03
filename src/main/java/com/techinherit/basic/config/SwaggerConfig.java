package com.techinherit.basic.config;

import com.google.common.collect.Lists;
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
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${app.header}")
    String header;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build().apiInfo(apiInfo())
                .globalOperationParameters(
                        Lists.newArrayList(new ParameterBuilder()
                                .name(header)
                                .description("Description of header")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(false)
                                .build(),
                                new ParameterBuilder()
                                        .name("Accept-Language")
                                        .description("Accept-Language header")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .defaultValue("en")
                                        .required(true)
                                        .build()
                        )) //                .securityContexts(Lists.newArrayList(securityContext()))
                //                .securitySchemes(Lists.newArrayList(apiKey()))
                ;
//        globalOperationParameters(
//                newArrayList(new ParameterBuilder()
//                        .name("header")
//                        .description("Description of header")
//                        .modelRef(new ModelRef("string"))
//                        .parameterType("header")
//                        .required(true)
//                        .build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Tracking 0.1",
                "If you are seeing this page you are part of Route.",
                "0.1",
                "Terms of service",
                new Contact("Suroj Dangol", "http://www.surojd.com.np", "surojsmiling@gmail.com"),
                "Api License", "none available", Collections.emptyList());
    }
//
//    private ApiKey apiKey() {
//        return new ApiKey(header, "api_key", "header");
//    }
//
//    @Bean
//    SecurityConfiguration security() {
//        return new SecurityConfiguration(
//                null,
//                null,
//                null, // realm Needed for authenticate button to work
//                null, // appName Needed for authenticate button to work
//                "",// apiKeyValue
//                ApiKeyVehicle.HEADER,
//                header, //apiKeyName
//                null);
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/anyPath.*"))
//                .build();
//    }
//
//    List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope
//                = new AuthorizationScope("global", "accessEverything");
//        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//        authorizationScopes[0] = authorizationScope;
//        return Lists.newArrayList(
//                new SecurityReference(header, authorizationScopes));
//    }
}
