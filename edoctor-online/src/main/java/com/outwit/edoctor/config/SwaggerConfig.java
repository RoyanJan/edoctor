package com.outwit.edoctor.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.outwit.edoctor.web")
public class SwaggerConfig {

    @Bean
    public Docket applicationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("edoctor-online-api")
                .apiInfo(apiInfo())
                .select()
                .paths(applicationPaths())
                .build();
    }

    @Bean
    Docket managementApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("management-api")
                .apiInfo(apiInfo())
                .select()
                .paths(managementPaths())
                .build();
    }


    private Predicate<String> applicationPaths() {
        return not(regex("/manage.*"));
    }

    private Predicate<String> managementPaths() {
        return regex("/manage.*");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("E-Doctor API")
                .description("Project API orient mobile and web app ,also trigger the test .")
                .termsOfServiceUrl("mailto:royan_jan@126.com")
                .license("Apache License Version 2.0")
                .version("1.0-SNAPSHOT")
                .build();
    }

}
