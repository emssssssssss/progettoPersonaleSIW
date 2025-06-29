package it.uniroma3.authentication;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.addConnectorCustomizers(connector -> {
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?> protocolHandler) {
                protocolHandler.setMaxSwallowSize(100 * 1024 * 1024); // 100MB
            }

            // 🔥 ECCO IL PARAMETRO CHE SERVE
            connector.setProperty("maxFileCount", "1000");
            connector.setProperty("maxPostSize", String.valueOf(100 * 1024 * 1024)); // 100MB
        });
    }
}
