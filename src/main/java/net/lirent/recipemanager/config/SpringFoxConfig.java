package net.lirent.recipemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Optional;

/**
 * <p>Swagger-UI API configuration</p>
 *
 * @author Lirent
 */
@Configuration
public class SpringFoxConfig {

    private static final String VERSION = Optional
            .ofNullable(SpringFoxConfig.class.getPackage().getImplementationVersion()).orElse("unknown/IDE");

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.lirent.recipemanager.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        return apiInfoBuilder
                .title("Recipe Manager API")
                .description("Recipe manager with Springboot")
                .version(VERSION)
                .build();
    }

}
