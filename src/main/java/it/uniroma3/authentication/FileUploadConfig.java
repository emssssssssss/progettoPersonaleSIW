package it.uniroma3.authentication;


import org.apache.catalina.Context;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfig {



    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                context.setAllowCasualMultipartParsing(true); // forza il parsing multipart
                context.getServletContext()
                       .setAttribute("org.apache.tomcat.uploadFileCountMax", 100); // override limite file
            }
        };
    }
}
