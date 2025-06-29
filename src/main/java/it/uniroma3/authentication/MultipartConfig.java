package it.uniroma3.authentication;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // ✅ Imposta i limiti file/upload
        factory.setMaxFileSize(DataSize.ofMegabytes(100));       // 100MB per file
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));    // 100MB per richiesta
        return factory.createMultipartConfig();
    }
}
