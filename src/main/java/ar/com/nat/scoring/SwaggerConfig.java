package ar.com.nat.scoring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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
	public class SwaggerConfig extends WebMvcConfigurationSupport {
	    @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("ar.com.nat.scoring.controllers"))
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(metaData());
	    }
	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Rest Guay servicios")
	                .description("\"Rest solicitudes Guay\"")
	                .version("1.0.2-SNAPSHOT")
	                .license("Apache License Version 2.0")
	                .licenseUrl("natconsultores.com.ar\"")
	                .contact(new Contact("Nat consultores", "natconsultores.com.ar", "natconsultores@nat.com.ar"))
	                .build();
	    }
	    @Override
	    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");

	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
	}