package com.api.sub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * spring doc - swagger3
 */
@Configuration
public class Swagger3Config {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/api/**")  //localhost:8080/api~ 로 시작은 다 매칭시킨다는의
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Bstagram API")
                        .description("BMW 프로젝트 API 명세서입니다.")
                        .version("v0.0.1"));
    }
}