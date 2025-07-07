package it.uniroma3.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir}")
    private String uploadDir;  // es. C:/.../uploads/images


    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mappiamo /uploads/images/** sulla cartella fisica
        registry.addResourceHandler("/uploads/images/**")
                .addResourceLocations("file:" + uploadDir.replace("\\", "/") + "/");

        // Risorse statiche di default
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
