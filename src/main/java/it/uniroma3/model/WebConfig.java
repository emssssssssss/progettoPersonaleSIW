package it.uniroma3.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve file da /uploads (quindi anche /uploads/images)
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/Users/182935/Documents/workspace-spring-tool-suite-4-4.28.1.RELEASE/progettoPersonaleSIW-1/uploads/");

        // Serve le risorse classiche da src/main/resources/static
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
