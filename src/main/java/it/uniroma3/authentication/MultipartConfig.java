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

        factory.setMaxFileSize(DataSize.ofMegabytes(100)); // Max singolo file
        factory.setMaxRequestSize(DataSize.ofMegabytes(100)); // Max totale richiesta
        factory.setFileSizeThreshold(DataSize.ofKilobytes(512)); // Buffer in memoria

        // ⚠️ NON esiste direttamente maxFileCount, ma limitare a 1 file è possibile nel
        // form HTML
        return factory.createMultipartConfig();
    }
}
